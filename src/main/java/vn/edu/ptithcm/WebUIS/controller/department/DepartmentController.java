package vn.edu.ptithcm.WebUIS.controller.department;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Announcement;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateEmployeeRequest;
import vn.edu.ptithcm.WebUIS.domain.request.password.ChangePasswordRequest;
import vn.edu.ptithcm.WebUIS.domain.response.ClassEntityResponse;
import vn.edu.ptithcm.WebUIS.domain.response.MessageResponse;
import vn.edu.ptithcm.WebUIS.domain.response.employee.EmployeeResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AccountService;
import vn.edu.ptithcm.WebUIS.service.ClassService;
import vn.edu.ptithcm.WebUIS.service.DepartmentService;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final ClassService classService;
    private final SemesterService semesterService;
    private final AccountService accountService;

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
        if (!employeeService.isDepartment(employee)) {
            throw new BadRequestException("Bạn không có quyền cập nhật thông tin nhân viên");
        }
        return ResponseEntity.ok(employeeService.updateEmployee(employee.getId(), updateEmployeeRequest, avatar));
    }

    /**
     * Đổi mật khẩu
     * 
     * @param request Request body chứa accountId, oldPassword, newPassword,
     *                confirmPassword
     * @return
     */
    @PutMapping("/change-password")
    @ApiMessage("Đổi mật khẩu")
    public ResponseEntity<MessageResponse> changePassword(@Valid @RequestBody ChangePasswordRequest request)
            throws IdInValidException {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        request.setAccountId(employee.getAccount().getId());
        accountService.changePassword(request);
        return ResponseEntity.ok(MessageResponse.of("Mật khẩu đã được đổi thành công"));
    }

    /**
     * Lấy danh sách khoa
     * 
     * @return
     */
    @GetMapping("/faculties")
    @ApiMessage("Danh sách khoa")
    public ResponseEntity<List<Department>> getAllFaculties() {
        return ResponseEntity.ok(departmentService.getAllFaculties());
    }

    /**
     * Lấy danh sách lớp của khoa
     * 
     * @param facultyId
     * @return
     */
    @GetMapping("/classes")
    @ApiMessage("Danh sách lớp của khoa")
    public ResponseEntity<List<ClassEntityResponse>> getAllClasses(@RequestParam String facultyId) {
        Department faculty = departmentService.getDepartmentById(facultyId);
        return ResponseEntity.ok(classService.getAllClassesOfFaculty(faculty));
    }

    /**
     * Lấy danh sách học kỳ của lớp
     * 
     * @param classId
     * @return
     */
    @GetMapping("/semesters")
    @ApiMessage("Danh sách học kỳ của lớp")
    public ResponseEntity<List<Semester>> getSemesterByClass(@RequestParam String classId) {
        return ResponseEntity.ok(semesterService.getSemesterByClass(classId));
    }

    /**
     * Phòng CTSV tạo thông báo
     * 
     * @param announcement
     * @return
     */
    @PostMapping("/announcements")
    @ApiMessage("Phòng CTSV tạo thông báo")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) throws IOException {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        announcement.setEmployee(employee);
        return ResponseEntity.ok(departmentService.createAnnouncement(announcement, attachment));
    }

    /**
     * xoá thông báo
     * 
     * @param announcementId
     * @return
     * @throws IdInValidException
     */
    @DeleteMapping("/announcements/{announcementId}")
    @ApiMessage("xoá thông báo")
    public ResponseEntity<MessageResponse> deleteAnnouncement(@PathVariable Integer announcementId)
            throws IdInValidException {
        departmentService.deleteAnnouncement(announcementId);
        return ResponseEntity.ok(MessageResponse.of("Thông báo đã được xoá thành công"));
    }

}
