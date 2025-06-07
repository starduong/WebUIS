package vn.edu.ptithcm.WebUIS.controller.advisor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreByClassResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.service.AcademicAdvisorService;
import vn.edu.ptithcm.WebUIS.service.TrainingScoreService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController(value = "advisorTrainingScoreController")
@RequestMapping("/api/v1/advisor/training-scores")
@RequiredArgsConstructor
public class TrainingScoreController {
    private final TrainingScoreService trainingScoreService;
    private final AcademicAdvisorService academicAdvisorService;

    /**
     * Lấy danh sách điểm rèn luyện theo lớp và học kỳ
     * 
     * @param classId
     * @param semesterId
     * @return
     * @throws Exception
     */
    @GetMapping
    @ApiMessage("Lấy danh sách điểm rèn luyện theo lớp và học kỳ")
    public ResponseEntity<List<TrainingScoreByClassResponse>> getTrainingScoresByClass(
            @RequestParam("semesterId") Integer semesterId) throws Exception {
        Lecturer lecturer = academicAdvisorService.getCurrentLecturerLogin();
        ClassEntity classEntity = academicAdvisorService.getClassByLecturer(lecturer);
        if (!academicAdvisorService.isAdvisorOfClass(classEntity.getClassId())) {
            throw new Exception("Bạn không có quyền truy cập");
        }
        return ResponseEntity.ok(trainingScoreService.getTrainingScoresByClass(classEntity.getClassId(), semesterId));
    }

    /**
     * CVHT đánh giá điểm rèn luyện
     * 
     * @param trainingScoreId
     * @param submitTrainingScoreRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/{trainingScoreId}")
    @ApiMessage("CVHT đánh giá điểm rèn luyện")
    public ResponseEntity<FormTrainingScoreResponse> evaluateTrainingScore(
            @PathVariable("trainingScoreId") Integer trainingScoreId,
            @RequestBody SubmitTrainingScoreRequest submitTrainingScoreRequest) throws Exception {
        return ResponseEntity
                .ok(academicAdvisorService.submitTrainingScore(trainingScoreId,
                        submitTrainingScoreRequest));
    }

}
