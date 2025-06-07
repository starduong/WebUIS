package vn.edu.ptithcm.WebUIS.domain.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {
    private String studentId;

    @NotNull(message = "Họ không được để trống")
    private String lastName;

    @NotNull(message = "Tên không được để trống")
    private String firstName;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dateOfBirth;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    private String phoneNumber;

    @NotNull(message = "Số CCCD không được để trống")
    private String citizenId;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ")
    private String personalEmail;

    private String imagePath;

    private String hometown;

    private String address;

    private String permanentAddress;

    private String ethnicity;
}
