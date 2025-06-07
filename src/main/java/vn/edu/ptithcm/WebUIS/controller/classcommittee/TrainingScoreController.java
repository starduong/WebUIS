package vn.edu.ptithcm.WebUIS.controller.classcommittee;

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
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreByClassResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.service.ClassCommitteeService;
import vn.edu.ptithcm.WebUIS.service.TrainingScoreService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RequiredArgsConstructor
@RestController(value = "classCommitteeTrainingScoreController")
@RequestMapping("/api/v1/class-committee/training-scores")
public class TrainingScoreController {
    private final TrainingScoreService trainingScoreService;
    private final ClassCommitteeService classCommitteeService;

    /**
     * Lấy danh sách điểm rèn luyện theo lớp và học kỳ
     * 
     * @param semesterId
     * @return
     * @throws Exception
     */
    @GetMapping
    @ApiMessage("Lấy danh sách điểm rèn luyện theo lớp và học kỳ")
    public ResponseEntity<List<TrainingScoreByClassResponse>> getTrainingScoresByClass(
            @RequestParam("semesterId") Integer semesterId) throws Exception {
        Student student = classCommitteeService.getCurrentStudentLogin();
        String classId = student.getClassEntity().getClassId();
        if (!classCommitteeService.isClassCommittee(classId)) {
            throw new Exception("Bạn không có quyền truy cập");
        }
        return ResponseEntity.ok(trainingScoreService.getTrainingScoresByClass(classId, semesterId));
    }

    /**
     * Lấy chi tiết form đánh giá điểm rèn luyện của sinh viên theo ID
     */
    @GetMapping("/{trainingScoreId}")
    @ApiMessage("Lấy chi tiết form đánh giá điểm rèn luyện của sinh viên")
    public ResponseEntity<FormTrainingScoreResponse> getFormTrainingScore(@PathVariable Integer trainingScoreId) {
        return ResponseEntity.ok(trainingScoreService.getFormTrainingScore(trainingScoreId));
    }

    /**
     * Ban cán sự đánh giá điểm rèn luyện
     * 
     * @param trainingScoreId
     * @param submitTrainingScoreRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/{trainingScoreId}")
    @ApiMessage("Ban cán sự đánh giá điểm rèn luyện")
    public ResponseEntity<FormTrainingScoreResponse> evaluateTrainingScore(
            @PathVariable("trainingScoreId") Integer trainingScoreId,
            @RequestBody SubmitTrainingScoreRequest submitTrainingScoreRequest) throws Exception {
        return ResponseEntity
                .ok(classCommitteeService.submitTrainingScore(trainingScoreId, submitTrainingScoreRequest));
    }
}