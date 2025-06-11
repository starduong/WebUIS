package vn.edu.ptithcm.WebUIS.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Announcement;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetailPK;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.request.department.CreateTrainingScoreByClassAndSemesterRequest;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.AnnouncementRepository;
import vn.edu.ptithcm.WebUIS.repository.ClassRepository;
import vn.edu.ptithcm.WebUIS.repository.DepartmentRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentRepository;
import vn.edu.ptithcm.WebUIS.repository.SemesterRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreDetailRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;
import vn.edu.ptithcm.WebUIS.util.S3UploadFileUtil;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ClassRepository classRepository;
    private final SemesterRepository semesterRepository;
    private final TrainingScoreRepository trainingScoreRepository;
    private final TrainingScoreMapper trainingScoreMapper;
    private final EvaluationContentRepository evaluationContentRepository;
    private final TrainingScoreDetailRepository trainingScoreDetailRepository;
    private final AnnouncementRepository announcementRepository;
    private final S3UploadFileUtil s3UploadFileUtil;

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<Department> getAllFaculties() {
        return departmentRepository.findByType(true);
    }

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    /**
     * Tạo điểm rèn luyện mới cho tất cả sinh viên trong lớp và học kỳ
     * =========================================================================================================
     * 
     * @param request
     * @return danh sách điểm rèn luyện của tất cả sinh viên trong lớp và học kỳ
     */
    @Transactional
    public List<TrainingScoreByFCSResponse> createNewTrainingScoreForAllStudentInClassAndSemester(
            CreateTrainingScoreByClassAndSemesterRequest request) {
        ClassEntity classEntity = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        Semester semester = semesterRepository.findById(request.getSemesterId())
                .orElseThrow(() -> new EntityNotFoundException("Semester not found"));
        List<Student> students = classEntity.getStudents();
        List<TrainingScore> trainingScores = new ArrayList<>();
        for (Student student : students) {
            if (student.getStatus() != null && student.getStatus()) {
                // check if training score exists
                if (checkTrainingScoreExists(student.getStudentId(), semester.getId())) {
                    continue;
                }
                TrainingScore trainingScore = new TrainingScore();
                trainingScore.setSemester(semester);
                trainingScore.setStudent(student);
                trainingScore.setStartDate(request.getStartDate());
                trainingScore.setEndDate(request.getEndDate());
                trainingScores.add(trainingScoreRepository.save(trainingScore));
            }
        }
        List<TrainingScoreByFCSResponse> trainingScoreByFCSResponses = new ArrayList<>();
        for (TrainingScore trainingScore : trainingScores) {
            trainingScoreByFCSResponses.add(trainingScoreMapper.convertTrainingScoreToFCSDTO(trainingScore));
        }
        if (trainingScoreByFCSResponses.isEmpty()) {
            throw new BadRequestException("No training score created");
        }
        return trainingScoreByFCSResponses;
    }

    private boolean checkTrainingScoreExists(String studentId, Integer semesterId) {
        return trainingScoreRepository.existsByStudentIdAndSemesterId(studentId, semesterId);
    }

    // kiểm tra phòng CTSV có quyền sửa điểm rèn luyện không
    public boolean isDepartmentCanEvaluateTrainingScore(Integer trainingScoreId) throws IdInValidException {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            throw new IdInValidException("Điểm rèn luyện không tồn tại");
        }
        return trainingScore.getStatus() == TrainingScoreStatus.WAIT_DEPARTMENT;
    }

    /**
     * Phòng CTSV chỉnh sửa điểm rèn luyện
     * 
     * @param trainingScoreId            id điểm rèn luyện
     * @param submitTrainingScoreRequest request đánh giá điểm rèn luyện
     * @return
     * @throws IdInValidException
     */
    @Transactional(rollbackOn = { IdInValidException.class, BadRequestException.class })
    public FormTrainingScoreResponse updateTrainingScore(Integer trainingScoreId,
            SubmitTrainingScoreRequest submitTrainingScoreRequest)
            throws IdInValidException {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            throw new IdInValidException("Điểm rèn luyện không tồn tại");
        }
        if (!isDepartmentCanEvaluateTrainingScore(trainingScoreId)) {
            throw new BadRequestException("Bạn không có quyền chỉnh sửa điểm rèn luyện này");
        }
        // tính tổng điểm của phòng CTSV
        Integer advisorScore = 0;
        // cập nhật trainingScoreDetail
        List<SubmitTrainingScoreRequest.TrainingScoreDetailRequest> trainingScoreDetailRequests = submitTrainingScoreRequest
                .getTrainingScoreDetails();
        for (SubmitTrainingScoreRequest.TrainingScoreDetailRequest evaluationContentDetailRequest : trainingScoreDetailRequests) {
            EvaluationContent evaluationContent = evaluationContentRepository
                    .findById(evaluationContentDetailRequest.getEvaluationContentId()).orElse(null);
            TrainingScoreDetail trainingScoreDetail = trainingScoreDetailRepository
                    .findByTrainingScoreIdAndEvaluationContentId(trainingScoreId,
                            evaluationContentDetailRequest.getEvaluationContentId());
            if (evaluationContent.getCriterion() != null) {
                advisorScore += evaluationContentDetailRequest.getScore();
            }
            if (trainingScoreDetail == null) {
                trainingScoreDetail = new TrainingScoreDetail();
            }
            trainingScoreDetail.setId(new TrainingScoreDetailPK(trainingScoreId,
                    evaluationContentDetailRequest.getEvaluationContentId()));
            trainingScoreDetail.setTrainingScore(trainingScore);
            trainingScoreDetail.setEvaluationContent(evaluationContent);
            trainingScoreDetail.setAdvisorScore(evaluationContentDetailRequest.getScore());
            trainingScoreDetailRepository.save(trainingScoreDetail);
        }

        trainingScore.setStatus(TrainingScoreStatus.COMPLETED);
        trainingScore.setTotalScore(advisorScore);
        trainingScoreRepository.save(trainingScore);
        return trainingScoreMapper.convertTrainingScoreToFormTrainingScoreResponse(trainingScore);
    }

    /**
     * duyệt tất cả điểm rèn luyện của sinh viên trong lớp theo học kỳ
     * 
     * @param classId
     * @param semesterId
     * @return
     */
    public List<TrainingScoreByFCSResponse> approveTrainingScore(String classId, Integer semesterId) {
        List<TrainingScore> trainingScores = trainingScoreRepository.findByClassIdAndSemesterId(classId, semesterId);
        List<TrainingScoreByFCSResponse> responses = new ArrayList<>();
        for (TrainingScore trainingScore : trainingScores) {
            if (trainingScore.getStatus() == TrainingScoreStatus.WAIT_DEPARTMENT) {
                trainingScore.setStatus(TrainingScoreStatus.COMPLETED);
                trainingScoreRepository.save(trainingScore);
                responses.add(trainingScoreMapper.convertTrainingScoreToFCSDTO(trainingScore));
            }
        }
        return responses;
    }

    /**
     * Phòng CTSV tạo thông báo
     * 
     * @param announcementRequest
     * @return
     * @throws IOException
     */
    public Announcement createAnnouncement(Announcement announcement, MultipartFile attachment) throws IOException {
        if (attachment != null) {
            String attachmentUrl = s3UploadFileUtil.uploadFile(attachment, "temps");
            announcement.setAttachmentUrl(attachmentUrl);
        }
        return announcementRepository.save(announcement);
    }
}
