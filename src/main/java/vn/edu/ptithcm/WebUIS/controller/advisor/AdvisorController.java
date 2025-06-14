package vn.edu.ptithcm.WebUIS.controller.advisor;

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
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateLecturerRequest;
import vn.edu.ptithcm.WebUIS.domain.request.password.ChangePasswordRequest;
import vn.edu.ptithcm.WebUIS.domain.response.ClassEntityResponse;
import vn.edu.ptithcm.WebUIS.domain.response.MessageResponse;
import vn.edu.ptithcm.WebUIS.domain.response.lecturer.LecturerResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AcademicAdvisorService;
import vn.edu.ptithcm.WebUIS.service.AccountService;
import vn.edu.ptithcm.WebUIS.service.ClassService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/advisor")
@RequiredArgsConstructor
public class AdvisorController {

    private final AcademicAdvisorService academicAdvisorService;
    private final SemesterService semesterService;
    private final AccountService accountService;
    private final ClassService classService;

    /**
     * Lấy thông tin giảng viên đang đăng nhập
     * 
     * @return
     */
    @GetMapping("/profile")
    @ApiMessage("Lấy thông tin giảng viên đang đăng nhập")
    public ResponseEntity<LecturerResponse> getLecturerProfile() {
        Lecturer lecturer = academicAdvisorService.getCurrentLecturerLogin();
        return ResponseEntity.ok(academicAdvisorService.getLecturerById(lecturer.getLecturerId()));
    }

    /**
     * Cập nhật thông tin giảng viên
     * 
     * @param lecturerId
     * @param updateLecturerRequest
     * @param avatar
     * @return
     * @throws IdInValidException
     * @throws IOException
     */
    @PutMapping("/profile")
    @ApiMessage("Cập nhật thông tin giảng viên")
    public ResponseEntity<LecturerResponse> updateLecturerProfile(
            @Valid @RequestBody UpdateLecturerRequest updateLecturerRequest,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar)
            throws IdInValidException, IOException {
        Lecturer lecturer = academicAdvisorService.getCurrentLecturerLogin();
        return ResponseEntity
                .ok(academicAdvisorService.updateLecturer(lecturer.getLecturerId(), updateLecturerRequest, avatar));
    }

    /**
     * Lấy ra tên lớp đang cố vấn
     * 
     * @return
     * @throws IdInValidException
     */
    @GetMapping("/class-name")
    @ApiMessage("Lấy ra tên lớp đang cố vấn")
    public ResponseEntity<ClassEntityResponse> getClassByLecturer() throws IdInValidException {
        Lecturer lecturer = academicAdvisorService.getCurrentLecturerLogin();
        return ResponseEntity.ok(classService.getClassByLecturer(lecturer));
    }

    /**
     * Lấy danh sách học kỳ của lớp
     * 
     * @return
     * @throws IdInValidException
     */
    @GetMapping("/semesters")
    @ApiMessage("Danh sách học kỳ của lớp")
    public ResponseEntity<List<Semester>> getSemesterByClass() throws IdInValidException {
        Lecturer lecturer = academicAdvisorService.getCurrentLecturerLogin();
        ClassEntity classEntity = academicAdvisorService.getClassByLecturer(lecturer);
        return ResponseEntity.ok(semesterService.getSemesterByClass(classEntity.getClassId()));
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
        Lecturer lecturer = academicAdvisorService.getCurrentLecturerLogin();
        request.setAccountId(lecturer.getAccount().getId());
        accountService.changePassword(request);
        return ResponseEntity.ok(MessageResponse.of("Mật khẩu đã được đổi thành công"));
    }

}
