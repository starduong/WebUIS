package vn.edu.ptithcm.WebUIS.controller.faculty;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.response.TrainingScoreStatisticsResponse;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.service.FacultyService;
import vn.edu.ptithcm.WebUIS.service.TrainingScoreService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController(value = "facultyTrainingScoreController")
@RequestMapping("/api/v1/faculty/training-scores")
@RequiredArgsConstructor
public class TrainingScoreController {
    private final TrainingScoreService trainingScoreService;
    private final FacultyService facultyService;
    private final EmployeeService employeeService;

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
    public ResponseEntity<List<TrainingScoreByFCSResponse>> getTrainingScoresByClass(
            @RequestParam("classId") String classId,
            @RequestParam("semesterId") Integer semesterId) throws Exception {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        if (!facultyService.isClassOfFaculty(classId, employee.getDepartment().getDepartmentId())) {
            throw new Exception("Lớp không thuộc khoa");
        }
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
     * Khoa chỉnh sửa điểm rèn luyện
     * 
     * @param trainingScoreId
     * @param submitTrainingScoreRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/{trainingScoreId}")
    @ApiMessage("Khoa chỉnh sửa điểm rèn luyện")
    public ResponseEntity<FormTrainingScoreResponse> evaluateTrainingScore(
            @PathVariable("trainingScoreId") Integer trainingScoreId,
            @RequestBody SubmitTrainingScoreRequest submitTrainingScoreRequest) throws Exception {
        return ResponseEntity
                .ok(facultyService.updateTrainingScore(trainingScoreId,
                        submitTrainingScoreRequest));
    }

    /**
     * Khoa duyệt tất cả điểm rèn luyện của sinh viên trong lớp theo học kỳ
     */
    @PutMapping("/approve/{classId}/{semesterId}")
    @ApiMessage("Khoa duyệt tất cả điểm rèn luyện của sinh viên trong lớp theo học kỳ")
    public ResponseEntity<List<TrainingScoreByFCSResponse>> approveTrainingScore(
            @PathVariable("classId") String classId,
            @PathVariable("semesterId") Integer semesterId) {
        return ResponseEntity.ok(facultyService.approveTrainingScore(classId, semesterId));
    }

    /**
     * Thống kê điểm rèn luyện của sinh viên theo lớp và học kỳ
     * 
     * @param classId
     * @param semesterId
     * @return
     * @throws Exception
     */
    @GetMapping("/statistics")
    @ApiMessage("Thống kê điểm rèn luyện của sinh viên theo lớp và học kỳ")
    public ResponseEntity<TrainingScoreStatisticsResponse> getTrainingScoreStatistics(
            @RequestParam("classId") String classId,
            @RequestParam("semesterId") Integer semesterId) throws Exception {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        RoleEnum role = RoleEnum.valueOf(employee.getAccount().getRole().getName());
        if (!role.equals(RoleEnum.EMPLOYEE_FACULTY)) {
            throw new Exception("Bạn không có quyền truy cập");
        }
        return ResponseEntity.ok(trainingScoreService.getTrainingScoreStatistics(classId, semesterId));
    }
}
