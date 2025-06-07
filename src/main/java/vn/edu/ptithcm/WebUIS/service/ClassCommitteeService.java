package vn.edu.ptithcm.WebUIS.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassCommittee;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetailPK;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.AccountRepository;
import vn.edu.ptithcm.WebUIS.repository.ClassCommitteeRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentRepository;
import vn.edu.ptithcm.WebUIS.repository.StudentRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreDetailRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;
import vn.edu.ptithcm.WebUIS.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class ClassCommitteeService {
    private final ClassCommitteeRepository classCommitteeRepository;
    private final StudentRepository studentRepository;
    private final AccountRepository accountRepository;
    private final TrainingScoreRepository trainingScoreRepository;
    private final EvaluationContentRepository evaluationContentRepository;
    private final TrainingScoreDetailRepository trainingScoreDetailRepository;
    private final TrainingScoreMapper trainingScoreMapper;

    /**
     * Lấy thông tin sinh viên đang đăng nhập
     * 
     * @return student
     */
    public Student getCurrentStudentLogin() {
        String username = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get()
                : null;
        return studentRepository.findByAccount(accountRepository.findByUsername(username).orElse(null));
    }

    /**
     * Kiểm tra sinh viên đang login có phải ban cán sự của lớp không
     * 
     * @param classId
     * @return true nếu sinh viên đang login là ban cán sự của lớp, ngược lại false
     */
    public boolean isClassCommittee(String classId) {
        Student student = getCurrentStudentLogin();
        ClassCommittee classCommittee = classCommitteeRepository.findByStudent(student).orElse(null);
        return classCommittee != null;
    }

    // Kiểm tra sinh viên có quyền đánh giá điểm rèn luyện không
    public boolean isStudentCanEvaluateTrainingScore(Integer trainingScoreId) {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        return trainingScore != null && trainingScore.getStatus() == TrainingScoreStatus.WAIT_CLASS_COMMITTEE;
    }

    /**
     * Ban cán sự đánh giá điểm rèn luyện
     * 
     * @param trainingScoreId            id điểm rèn luyện
     * @param submitTrainingScoreRequest request đánh giá điểm rèn luyện
     * @return
     * @throws IdInValidException
     */
    @Transactional(rollbackOn = { IdInValidException.class, BadRequestException.class })
    public FormTrainingScoreResponse submitTrainingScore(Integer trainingScoreId,
            SubmitTrainingScoreRequest submitTrainingScoreRequest)
            throws IdInValidException {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            throw new IdInValidException("Điểm rèn luyện không tồn tại");
        }

        if (!isStudentCanEvaluateTrainingScore(trainingScoreId)) {
            throw new BadRequestException("Bạn không có quyền đánh giá điểm rèn luyện này");
        }

        // tạo trainingScoreDetail
        List<SubmitTrainingScoreRequest.TrainingScoreDetailRequest> trainingScoreDetailRequests = submitTrainingScoreRequest
                .getTrainingScoreDetails();
        for (SubmitTrainingScoreRequest.TrainingScoreDetailRequest evaluationContentDetailRequest : trainingScoreDetailRequests) {
            EvaluationContent evaluationContent = evaluationContentRepository
                    .findById(evaluationContentDetailRequest.getEvaluationContentId()).orElse(null);
            TrainingScoreDetail trainingScoreDetail = trainingScoreDetailRepository
                    .findByTrainingScoreIdAndEvaluationContentId(trainingScoreId,
                            evaluationContentDetailRequest.getEvaluationContentId());
            if (trainingScoreDetail == null) {
                trainingScoreDetail = new TrainingScoreDetail();
            }
            trainingScoreDetail.setId(new TrainingScoreDetailPK(trainingScoreId,
                    evaluationContentDetailRequest.getEvaluationContentId()));
            trainingScoreDetail.setTrainingScore(trainingScore);
            trainingScoreDetail.setEvaluationContent(evaluationContent);
            trainingScoreDetail.setClassCommitteeScore(evaluationContentDetailRequest.getScore());
            trainingScoreDetailRepository.save(trainingScoreDetail);
        }

        trainingScore.setStatus(TrainingScoreStatus.WAIT_ADVISOR);
        trainingScore.setClassCommitteeAssessmentDate(LocalDate.now());
        trainingScoreRepository.save(trainingScore);
        return trainingScoreMapper.convertTrainingScoreToFormTrainingScoreResponse(trainingScore);
    }
}
