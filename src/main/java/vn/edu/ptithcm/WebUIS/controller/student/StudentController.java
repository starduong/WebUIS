package vn.edu.ptithcm.WebUIS.controller.student;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateStudentRequest;
import vn.edu.ptithcm.WebUIS.domain.request.password.ChangePasswordRequest;
import vn.edu.ptithcm.WebUIS.domain.response.MessageResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.AcademicResultResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.StudentResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AccountService;
import vn.edu.ptithcm.WebUIS.service.StudentService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

/**
 * Controller cho sinh viên
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final AccountService accountService;

    /**
     * Lấy thông tin sinh viên
     */
    @GetMapping("/profile")
    @ApiMessage("Lấy thông tin sinh viên")
    public ResponseEntity<StudentResponse> getStudentProfile() {
        Student student = studentService.getCurrentStudentLogin();
        return ResponseEntity.ok(studentService.getStudentById(student.getStudentId()));
    }

    /**
     * Lấy kết quả học tập của sinh viên
     */
    @GetMapping("/academic-results")
    @ApiMessage("Lấy kết quả học tập của sinh viên")
    public ResponseEntity<List<AcademicResultResponse>> getAcademicResultsByStudentId() {
        Student student = studentService.getCurrentStudentLogin();
        return ResponseEntity.ok(studentService.getAcademicResultDTOByStudentId(student.getStudentId()));
    }

    /**
     * Lấy điểm trung bình GPA của sinh viên
     */
    @GetMapping("/average-gpa")
    @ApiMessage("Lấy điểm trung bình GPA của sinh viên")
    public ResponseEntity<Double> getAverageGpaByStudentId() {
        Student student = studentService.getCurrentStudentLogin();
        return ResponseEntity.ok(studentService.getAverageGpaByStudentId(student.getStudentId()));
    }

    /**
     * Cập nhật thông tin sinh viên
     */
    @PutMapping("/profile")
    @ApiMessage("Cập nhật thông tin sinh viên")
    public ResponseEntity<StudentResponse> updateStudentProfile(@Valid @RequestBody UpdateStudentRequest studentRequest,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar)
            throws IdInValidException, IOException {
        Student student = studentService.getCurrentStudentLogin();
        return ResponseEntity.ok(studentService.updateStudent(student.getStudentId(), studentRequest, avatar));
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
        Student student = studentService.getCurrentStudentLogin();
        request.setAccountId(student.getAccount().getId());
        accountService.changePassword(request);
        return ResponseEntity.ok(MessageResponse.of("Mật khẩu đã được đổi thành công"));
    }

}
