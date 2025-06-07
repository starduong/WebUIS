package vn.edu.ptithcm.WebUIS.controller.department;

import vn.edu.ptithcm.WebUIS.service.DepartmentService;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.service.TrainingScoreService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.request.department.CreateTrainingScoreByClassAndSemesterRequest;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;

@RestController
@RequestMapping("/api/v1/department/training-scores")
@RequiredArgsConstructor
public class TrainingScoreMgrController {
    private final DepartmentService departmentService;
    private final TrainingScoreService trainingScoreService;
    private final EmployeeService employeeService;

    /**
     * Lấy danh sách điểm rèn luyện theo lớp và học kỳ
     */
    @GetMapping
    @ApiMessage("Danh sách điểm rèn luyện theo lớp và học kỳ")
    public ResponseEntity<List<TrainingScoreByFCSResponse>> getTrainingScoresByClassAndSemester(
            @RequestParam("classId") String classId,
            @RequestParam("semesterId") Integer semesterId) {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        RoleEnum role = RoleEnum.valueOf(employee.getAccount().getRole().getName());
        return ResponseEntity.ok(trainingScoreService.getTrainingScoresByClassAndSemester(role, classId, semesterId));
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
     * Tạo điểm rèn luyện mới cho tất cả sinh viên trong lớp và học kỳ
     */
    @PostMapping
    @ApiMessage("Tạo điểm rèn luyện mới cho tất cả sinh viên trong lớp và học kỳ")
    public ResponseEntity<List<TrainingScoreByFCSResponse>> createNewTrainingScoreForAllStudent(
            @Valid @RequestBody CreateTrainingScoreByClassAndSemesterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                departmentService.createNewTrainingScoreForAllStudentInClassAndSemester(request));
    }
}
