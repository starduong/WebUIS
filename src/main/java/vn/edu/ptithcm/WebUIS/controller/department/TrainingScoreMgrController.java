package vn.edu.ptithcm.WebUIS.controller.department;

import vn.edu.ptithcm.WebUIS.service.DepartmentService;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.service.TrainingScoreService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.request.department.AdjustTimeTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.request.department.CreateTrainingScoreByClassAndSemesterRequest;
import vn.edu.ptithcm.WebUIS.domain.response.MessageResponse;
import vn.edu.ptithcm.WebUIS.domain.response.TrainingScoreStatisticsResponse;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreTimeResponse;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;

@RestController
@RequestMapping("/api/v1/department/training-scores")
@RequiredArgsConstructor
public class TrainingScoreMgrController {
    private final DepartmentService departmentService;
    private final TrainingScoreService trainingScoreService;
    private final EmployeeService employeeService;
    private final SemesterService semesterService;

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
     * Phòng CTSV tạo điểm rèn luyện mới
     * =========================================================================================================
     * 
     */
    @PostMapping("/create")
    @ApiMessage("Tạo điểm rèn luyện mới cho tất cả sinh viên trong lớp và học kỳ")
    public ResponseEntity<MessageResponse> createNewTrainingScoreForAllStudent(
            @Valid @RequestBody CreateTrainingScoreByClassAndSemesterRequest request) throws IdInValidException {
        Semester semester = semesterService.getSemesterById(request.getSemesterId());
        if (request.getClassId() == null) {
            departmentService.createTrainingScoreForAllClassInSemester(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse
                    .of("Tạo điểm rèn luyện mới cho tất cả lớp trong học kỳ " + semester.getOrder() + " "
                            + semester.getAcademicYear()));
        }
        departmentService.createNewTrainingScoreForAllStudentInClassAndSemester(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MessageResponse.of("Tạo điểm rèn luyện mới cho tất cả sinh viên trong lớp " + request.getClassId()
                        + " trong học kỳ " + semester.getOrder() + " " + semester.getAcademicYear()));
    }

    /**
     * lấy thông tin thời gian bắt đầu và kết thúc điểm rèn luyện theo lớp hoặc tất
     * cả ở kỳ hiện tại
     * 
     * @throws IdInValidException
     */
    @GetMapping("/time")
    @ApiMessage("Lấy thông tin thời gian bắt đầu và kết thúc điểm rèn luyện theo lớp hoặc tất cả ở kỳ hiện tại")
    public ResponseEntity<TrainingScoreTimeResponse> getTrainingScoreTime(
            @RequestParam(value = "classId", required = false) String classId,
            @RequestParam("semesterId") Integer semesterId) throws IdInValidException {
        return ResponseEntity.ok(trainingScoreService.getTrainingScoreTime(classId, semesterId));
    }

    /**
     * Phòng CTSV điều chỉnh thời gian đánh giá điểm rèn luyện
     * =========================================================================================================
     */
    @PutMapping("/adjust-time")
    @ApiMessage("Phòng CTSV điều chỉnh thời gian đánh giá điểm rèn luyện của tất cả sinh viên trong lớp và học kỳ")
    public ResponseEntity<MessageResponse> adjustTimeTrainingScoreOfAllStudentInClassAndSemester(
            @RequestBody AdjustTimeTrainingScoreRequest request) throws IdInValidException {
        Semester semester = semesterService.getSemesterById(request.getSemesterId());
        if (request.getClassId() == null) {
            departmentService.adjustTimeTrainingScoreOfAllClassInSemester(request);
            return ResponseEntity.ok()
                    .body(MessageResponse.of("Thời gian đánh giá điểm rèn luyện của tất cả lớp trong học kỳ "
                            + semester.getOrder() + " " + semester.getAcademicYear() + " đã được điều chỉnh"));
        }
        departmentService.adjustTimeTrainingScoreOfAllStudentInClassAndSemester(request);
        return ResponseEntity.ok().body(MessageResponse.of("Thời gian đánh giá điểm rèn luyện của lớp "
                + request.getClassId() + " trong học kỳ "
                + semester.getOrder() + " " + semester.getAcademicYear() + " đã được điều chỉnh"));
    }

    /**
     * Phòng CTSV chỉnh sửa điểm rèn luyện
     * 
     * @param trainingScoreId id điểm rèn luyện
     * @param request         request đánh giá điểm rèn luyện
     * @return
     * @throws IdInValidException
     */
    @PutMapping("/{trainingScoreId}")
    @ApiMessage("Phòng CTSV chỉnh sửa điểm rèn luyện")
    public ResponseEntity<FormTrainingScoreResponse> updateTrainingScore(
            @PathVariable Integer trainingScoreId, @RequestBody SubmitTrainingScoreRequest request)
            throws IdInValidException {
        return ResponseEntity.ok(departmentService.updateTrainingScore(trainingScoreId, request));
    }

    /**
     * Phòng CTSV duyệt tất cả điểm rèn luyện của sinh viên trong lớp theo học kỳ
     */
    @PutMapping("/approve")
    @ApiMessage("Phòng CTSV duyệt tất cả điểm rèn luyện của sinh viên trong lớp theo học kỳ")
    public ResponseEntity<List<TrainingScoreByFCSResponse>> approveTrainingScore(
            @RequestParam("classId") String classId,
            @RequestParam("semesterId") Integer semesterId) {
        return ResponseEntity.ok(departmentService.approveTrainingScore(classId, semesterId));
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
        if (!role.equals(RoleEnum.EMPLOYEE_DEPARTMENT)) {
            throw new Exception("Bạn không có quyền truy cập");
        }
        return ResponseEntity.ok(trainingScoreService.getTrainingScoreStatistics(classId, semesterId));
    }
}
