package vn.edu.ptithcm.WebUIS.controller.student;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.TrainingScoreBySemesterResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.StudentService;
import vn.edu.ptithcm.WebUIS.service.TrainingScoreService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RequiredArgsConstructor
@RestController(value = "studentTrainingScoreController")
@RequestMapping("/api/v1/student/training-scores")
public class TrainingScoreController {
    private final StudentService studentService;
    private final TrainingScoreService trainingScoreService;

    /**
     * Lấy danh sách điểm rèn luyện của từng sinh viên
     */
    @GetMapping
    @ApiMessage("Lấy danh sách điểm rèn luyện của sinh viên")
    public ResponseEntity<List<TrainingScoreBySemesterResponse>> getTrainingScoresByStudentId() {
        Student student = studentService.getCurrentStudentLogin();
        return ResponseEntity.ok(trainingScoreService.getTrainingScoresByStudentId(student.getStudentId()));
    }

    /**
     * Lấy chi tiết form đánh giá điểm rèn luyện của sinh viên theo ID
     */
    @GetMapping("/{trainingScoreId}")
    @ApiMessage("Lấy chi tiết form đánh giá điểm rèn luyện của sinh viên")
    public ResponseEntity<FormTrainingScoreResponse> getFormTrainingScore(@PathVariable Integer trainingScoreId) {
        if (!studentService.isStudentCanViewTrainingScore(trainingScoreId)) {
            throw new AccessDeniedException("Bạn không có quyền xem điểm rèn luyện này");
        }
        return ResponseEntity.ok(trainingScoreService.getFormTrainingScore(trainingScoreId));
    }

    /**
     * Sinh viên đánh giá điểm rèn luyện của sinh viên
     */
    @PostMapping("/{trainingScoreId}")
    @ApiMessage("Sinh viên đánh giá điểm rèn luyện của sinh viên")
    public ResponseEntity<FormTrainingScoreResponse> evaluateTrainingScore(@PathVariable Integer trainingScoreId,
            @RequestBody SubmitTrainingScoreRequest formTrainingScoreRequest) throws IdInValidException {
        return ResponseEntity.ok(studentService.submitTrainingScore(trainingScoreId, formTrainingScoreRequest));
    }
}
