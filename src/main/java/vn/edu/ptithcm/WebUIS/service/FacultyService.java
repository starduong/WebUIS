package vn.edu.ptithcm.WebUIS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetailPK;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.DepartmentRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreDetailRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final DepartmentRepository departmentRepository;
    private final TrainingScoreRepository trainingScoreRepository;
    private final EvaluationContentRepository evaluationContentRepository;
    private final TrainingScoreDetailRepository trainingScoreDetailRepository;
    private final TrainingScoreMapper trainingScoreMapper;

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }

    // kiểm tra lớp có thuộc khoa không
    public boolean isClassOfFaculty(String classId, String facultyId) {
        Department faculty = getDepartmentById(facultyId);
        return faculty.getClasses().stream()
                .anyMatch(classEntity -> classEntity.getClassId().equals(classId));
    }

    // kiểm tra điểm rèn luyện khoa có quyền sửa không
    public boolean isFacultyCanEvaluateTrainingScore(Integer trainingScoreId) throws IdInValidException {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            throw new IdInValidException("Điểm rèn luyện không tồn tại");
        }
        return trainingScore.getStatus() == TrainingScoreStatus.WAIT_FACULTY;
    }

    /**
     * Khoa chỉnh sửa điểm rèn luyện
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
        if (!isFacultyCanEvaluateTrainingScore(trainingScoreId)) {
            throw new BadRequestException("Bạn không có quyền chỉnh sửa điểm rèn luyện này");
        }
        // tính tổng điểm của khoa
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

        trainingScore.setStatus(TrainingScoreStatus.WAIT_DEPARTMENT);
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
            if (trainingScore.getStatus() == TrainingScoreStatus.WAIT_FACULTY) {
                trainingScore.setStatus(TrainingScoreStatus.WAIT_DEPARTMENT);
                trainingScoreRepository.save(trainingScore);
                responses.add(trainingScoreMapper.convertTrainingScoreToFCSDTO(trainingScore));
            }
        }
        return responses;
    }
}
