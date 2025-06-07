package vn.edu.ptithcm.WebUIS.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreByClassResponse;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.TrainingScoreBySemesterResponse;
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
                if (trainingScore.getStatus() == TrainingScoreStatus.WAIT_FACULTY) {
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
        if (trainingScore.getStatus() == TrainingScoreStatus.WAIT_STUDENT) {
            return trainingScoreMapper.initFormTrainingScoreResponse(trainingScoreId);
        }
        return trainingScoreMapper.convertTrainingScoreToFormTrainingScoreResponse(trainingScore);
    }

}