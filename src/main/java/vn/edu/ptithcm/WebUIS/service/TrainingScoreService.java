package vn.edu.ptithcm.WebUIS.service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.response.TrainingScoreStatisticsResponse;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreByClassResponse;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreTimeResponse;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.TrainingScoreBySemesterResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;

@Service
@RequiredArgsConstructor
public class TrainingScoreService {
    private final TrainingScoreRepository trainingScoreRepository;
    private final TrainingScoreMapper trainingScoreMapper;

    /**
     * Lấy danh sách thống kê điểm rèn luyện theo sinh viên
     * For STUDENTS
     * =========================================================================================================
     * 
     * @param studentId
     * @return
     */
    public List<TrainingScoreBySemesterResponse> getTrainingScoresByStudentId(String studentId) {
        List<TrainingScore> trainingScores = trainingScoreRepository.findByStudentId(studentId);
        return trainingScores.stream()
                .map(trainingScoreMapper::convertTrainingScoreBySemesterToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy danh sách TrainingScoreByFCSResponse theo classId và semesterId
     * For DEPARTMENT AND FACULTY
     * =========================================================================================================
     * 
     * @param classId
     * @param semesterId
     * @return
     */
    public List<TrainingScoreByFCSResponse> getTrainingScoresByClassAndSemester(RoleEnum role, String classId,
            Integer semesterId) {
        List<TrainingScore> trainingScores = trainingScoreRepository.findByClassIdAndSemesterId(classId, semesterId);
        List<TrainingScoreByFCSResponse> responses = new ArrayList<>();
        for (TrainingScore trainingScore : trainingScores) {
            if (role == RoleEnum.EMPLOYEE_FACULTY) {
                if (trainingScore.getStatus() == TrainingScoreStatus.WAIT_FACULTY
                        || trainingScore.getStatus() == TrainingScoreStatus.WAIT_DEPARTMENT
                        || trainingScore.getStatus() == TrainingScoreStatus.COMPLETED) {
                    responses.add(trainingScoreMapper.convertTrainingScoreToFCSDTO(trainingScore));
                }
            } else if (role == RoleEnum.EMPLOYEE_DEPARTMENT) {
                if (trainingScore.getStatus() == TrainingScoreStatus.WAIT_DEPARTMENT
                        || trainingScore.getStatus() == TrainingScoreStatus.COMPLETED) {
                    responses.add(trainingScoreMapper.convertTrainingScoreToFCSDTO(trainingScore));
                }
            }
        }
        return responses;
    }

    /**
     * Lấy danh sách thống kê điểm rèn luyện theo trong lớp theo semesterId
     * For ACADEMIC ADVISOR AND CLASS COMMITTEE
     * =========================================================================================================
     * 
     * @param classId
     * @param semesterId
     * @return
     */
    public List<TrainingScoreByClassResponse> getTrainingScoresByClass(String classId, Integer semesterId) {
        List<TrainingScore> trainingScores = trainingScoreRepository.findByClassIdAndSemesterId(classId, semesterId);
        List<TrainingScoreByClassResponse> responses = new ArrayList<>();
        for (TrainingScore trainingScore : trainingScores) {
            responses.add(trainingScoreMapper.convertTrainingScoreToClassDTO(trainingScore));
        }
        return responses;
    }

    /**
     * lấy form đánh giá điểm rèn luyện
     * For ALL
     * =========================================================================================================
     * 
     * @param trainingScoreId id điểm rèn luyện
     * @return form đánh giá điểm rèn luyện
     */
    public FormTrainingScoreResponse getFormTrainingScore(Integer trainingScoreId) {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId)
                .orElseThrow(() -> new EntityNotFoundException("Điểm rèn luyện không tồn tại"));
        TrainingScoreStatus status = trainingScore.getStatus();
        // Trường hợp chưa tự đánh giá
        boolean isNeedInit = (status == TrainingScoreStatus.EXPIRED && trainingScore.getStudentAssessmentDate() == null)
                || status == TrainingScoreStatus.WAIT_STUDENT;
        if (isNeedInit) {
            return trainingScoreMapper.initFormTrainingScoreResponse(trainingScoreId);
        }
        // Trường hợp còn lại
        return trainingScoreMapper.convertTrainingScoreToFormTrainingScoreResponse(trainingScore);
    }

    /**
     * thống kê điểm rèn luyện của sinh viên theo lớp và học kỳ
     */
    public TrainingScoreStatisticsResponse getTrainingScoreStatistics(String classId, Integer semesterId) {
        List<TrainingScore> trainingScores = trainingScoreRepository.findByClassIdAndSemesterId(classId, semesterId);
        // Lấy điểm rèn luyện đã HOÀN THÀNH
        List<TrainingScore> completedTrainingScores = trainingScores.stream()
                .filter(trainingScore -> trainingScore.getStatus() == TrainingScoreStatus.COMPLETED)
                .collect(Collectors.toList());
        return trainingScoreMapper.convertTrainingScoreToStatisticsResponse(completedTrainingScores);
    }

    @Scheduled(cron = "0 0 * * * *") // Chạy mỗi giờ
    @Transactional
    public void updateExpiredTrainingScores() {
        List<TrainingScore> scores = trainingScoreRepository.findAllByStatusInAndEndDateBefore(
                List.of(
                        TrainingScoreStatus.WAIT_STUDENT,
                        TrainingScoreStatus.WAIT_CLASS_COMMITTEE,
                        TrainingScoreStatus.WAIT_ADVISOR,
                        TrainingScoreStatus.WAIT_FACULTY),
                LocalDateTime.now());

        for (TrainingScore score : scores) {
            score.setStatus(TrainingScoreStatus.EXPIRED);
        }

        trainingScoreRepository.saveAll(scores);
    }

    /**
     * lấy thông tin thời gian bắt đầu và kết thúc điểm rèn luyện theo lớp hoặc tất
     * cả ở kỳ hiện tại
     * 
     * @throws IdInValidException
     */
    public TrainingScoreTimeResponse getTrainingScoreTime(String classId, Integer semesterId)
            throws IdInValidException {
        if (classId == null) {
            List<TrainingScore> trainingScores = trainingScoreRepository.findBySemesterId(semesterId);
            if (trainingScores.isEmpty()) {
                throw new IdInValidException("Không tìm thấy điểm rèn luyện");
            }
            return trainingScoreMapper.convertTrainingScoreToTimeResponse(trainingScores.get(0));
        }
        List<TrainingScore> trainingScores = trainingScoreRepository.findByClassIdAndSemesterId(classId, semesterId);
        if (trainingScores.isEmpty()) {
            throw new IdInValidException("Không tìm thấy điểm rèn luyện");
        }
        return trainingScoreMapper.convertTrainingScoreToTimeResponse(trainingScores.get(0));
    }

}