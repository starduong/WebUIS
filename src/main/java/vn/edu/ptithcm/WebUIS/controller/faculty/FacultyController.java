package vn.edu.ptithcm.WebUIS.controller.faculty;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateEmployeeRequest;
import vn.edu.ptithcm.WebUIS.domain.response.ClassEntityResponse;
import vn.edu.ptithcm.WebUIS.domain.response.employee.EmployeeResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.ClassService;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.service.FacultyService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;
    private final EmployeeService employeeService;
    private final ClassService classService;
    private final SemesterService semesterService;

    /**
     * Lấy thông tin nhân viên đang đăng nhập
     * 
     * @return
     */
    @GetMapping("/profile")
    @ApiMessage("Lấy thông tin nhân viên đang đăng nhập")
    public ResponseEntity<EmployeeResponse> getEmployeeProfile() {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        return ResponseEntity.ok(employeeService.getEmployeeById(employee.getId()));
    }

    /**
     * Cập nhật thông tin nhân viên đang đăng nhập
     * 
     * @param updateEmployeeRequest
     * @param avatar
     * @return
     */
    @PutMapping("/profile")
    @ApiMessage("Cập nhật thông tin nhân viên đang đăng nhập")
    public ResponseEntity<EmployeeResponse> updateEmployeeProfile(
            @Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar)
            throws IdInValidException, IOException {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        if (employeeService.isFaculty(employee)) {
            throw new BadRequestException("Bạn không có quyền cập nhật thông tin nhân viên");
        }
        return ResponseEntity.ok(employeeService.updateEmployee(employee.getId(), updateEmployeeRequest, avatar));
    }

    /**
     * Lấy danh sách lớp của khoa
     * 
     * @param facultyId
     * @return
     */
    @GetMapping("/classes")
    @ApiMessage("Danh sách lớp của khoa")
    public ResponseEntity<List<ClassEntityResponse>> getAllClasses() {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        Department faculty = facultyService.getDepartmentById(employee.getDepartment().getDepartmentId());
        return ResponseEntity.ok(classService.getAllClassesOfFaculty(faculty));
    }

    /**
     * Lấy danh sách học kỳ của lớp
     * 
     * @param classId
     * @return
     */
    @GetMapping("/semesters/{classId}")
    @ApiMessage("Danh sách học kỳ của lớp")
    public ResponseEntity<List<Semester>> getSemesterByClass(@PathVariable String classId) {
        return ResponseEntity.ok(semesterService.getSemesterByClass(classId));
    }

}
