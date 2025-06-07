package vn.edu.ptithcm.WebUIS.controller.faculty;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
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
}
