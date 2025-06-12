package vn.edu.ptithcm.WebUIS.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.AcademicAdvisor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetailPK;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.LerturerMapper;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateLecturerRequest;
import vn.edu.ptithcm.WebUIS.domain.response.lecturer.LecturerResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.AcademicAdvisorRepository;
import vn.edu.ptithcm.WebUIS.repository.ClassRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentRepository;
import vn.edu.ptithcm.WebUIS.repository.LecturerRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreDetailRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;
import vn.edu.ptithcm.WebUIS.util.S3UploadFileUtil;
import vn.edu.ptithcm.WebUIS.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class AcademicAdvisorService {
    private final LecturerRepository lecturerRepository;
    private final AcademicAdvisorRepository academicAdvisorRepository;
    private final AccountService accountService;
    private final ClassRepository classRepository;
    private final LerturerMapper lerturerMapper;
    private final TrainingScoreRepository trainingScoreRepository;
    private final EvaluationContentRepository evaluationContentRepository;
    private final TrainingScoreDetailRepository trainingScoreDetailRepository;
    private final TrainingScoreMapper trainingScoreMapper;
    private final S3UploadFileUtil s3UploadFileUtil;

    /**
     * Lấy thông tin giảng viên đang đăng nhập
     * 
     * @return
     */
    public Lecturer getCurrentLecturerLogin() {
        String username = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get()
                : null;
        return lecturerRepository.findByAccount(accountService.findByUsername(username));
    }

    public LecturerResponse getLecturerById(String id) {
        return lerturerMapper.convertLecturerToDTO(lecturerRepository.findById(id).orElse(null));
    }

    public Lecturer getLecturerByAccount(Account account) {
        return lecturerRepository.findByAccount(account);
    }

    // lấy ra lớp mà giảng viên đang CVHT
    public ClassEntity getClassByLecturer(Lecturer lecturer) throws IdInValidException {
        AcademicAdvisor academicAdvisor = academicAdvisorRepository.findByLecturer(lecturer);
        if (academicAdvisor == null) {
            throw new IdInValidException("Giảng viên không tồn tại");
        }
        return academicAdvisor.getClassEntity();
    }

    /**
     * Cập nhật thông tin giảng viên
     * 
     * @param lecturerId
     * @param updateLecturerRequest
     * @param avatar
     * @return
     * @throws IdInValidException
     * @throws IOException
     */
    @Transactional(rollbackOn = { IdInValidException.class, IOException.class })
    public LecturerResponse updateLecturer(String lecturerId, UpdateLecturerRequest updateLecturerRequest,
            MultipartFile avatar) throws IdInValidException, IOException {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElse(null);
        if (lecturer == null) {
            throw new IdInValidException("Giảng viên không tồn tại");
        }
        lecturer.setLastName(updateLecturerRequest.getLastName());
        lecturer.setFirstName(updateLecturerRequest.getFirstName());
        lecturer.setGender(updateLecturerRequest.getGender());
        lecturer.setPhoneNumber(updateLecturerRequest.getPhoneNumber());
        if (updateLecturerRequest.getCitizenId() != null) {
            if (lecturerRepository.existsByCitizenId(updateLecturerRequest.getCitizenId())) {
                throw new IdInValidException("Số CCCD đã tồn tại");
            }
            lecturer.setCitizenId(updateLecturerRequest.getCitizenId());
        }
        lecturer.setEmail(updateLecturerRequest.getEmail());
        if (avatar != null) {
            lecturer.setImageUrl(s3UploadFileUtil.uploadFile(avatar, "temps"));
        }
        lecturer.setAcademicRank(updateLecturerRequest.getAcademicRank());
        lecturer.setAcademicTitle(updateLecturerRequest.getAcademicTitle());
        lecturer.setSpecialization(updateLecturerRequest.getSpecialization());
        lecturerRepository.save(lecturer);
        return lerturerMapper.convertLecturerToDTO(lecturer);
    }

    /**
     * Kiểm tra giảng viên có phải là CVHT của lớp X không
     * 
     * @param classId
     * @return
     */
    public boolean isAdvisorOfClass(String classId) {
        Lecturer lecturer = getCurrentLecturerLogin();
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);
        if (classEntity == null) {
            throw new BadRequestException("Lớp không tồn tại");
        }
        AcademicAdvisor academicAdvisor = academicAdvisorRepository.findByLecturerAndClassEntity(lecturer,
                classEntity);
        return academicAdvisor != null;
    }

    // Kiểm tra CVHT có quyền đánh giá điểm rèn luyện không
    public boolean isAdvisorCanEvaluateTrainingScore(Integer trainingScoreId) {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        return trainingScore != null && trainingScore.getStatus() == TrainingScoreStatus.WAIT_ADVISOR;
    }

    /**
     * CVHT đánh giá điểm rèn luyện
     * 
     * @param trainingScoreId id điểm rèn luyện
     * @param request         request đánh giá điểm rèn luyện
     * @return
     * @throws IdInValidException
     */
    @Transactional(rollbackOn = { IdInValidException.class, BadRequestException.class })
    public FormTrainingScoreResponse submitTrainingScore(Integer trainingScoreId, SubmitTrainingScoreRequest request)
            throws IdInValidException {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            throw new IdInValidException("Điểm rèn luyện không tồn tại");
        }

        if (!isAdvisorCanEvaluateTrainingScore(trainingScoreId)) {
            throw new BadRequestException("Bạn không có quyền đánh giá điểm rèn luyện này");
        }

        // tính tổng điểm của CVHT
        Integer advisorScore = 0;
        // tạo trainingScoreDetail
        List<SubmitTrainingScoreRequest.TrainingScoreDetailRequest> trainingScoreDetailRequests = request
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

        trainingScore.setStatus(TrainingScoreStatus.WAIT_FACULTY);
        trainingScore.setAdvisorAssessmentDate(LocalDateTime.now());
        trainingScore.setTotalScore(advisorScore);
        trainingScoreRepository.save(trainingScore);
        return trainingScoreMapper.convertTrainingScoreToFormTrainingScoreResponse(trainingScore);
    }
}