package vn.edu.ptithcm.WebUIS.domain.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLecturerRequest {
    @JsonProperty("lecturer_id")
    private String lecturerId;

    @JsonProperty("last_name")
    @NotNull(message = "Họ không được để trống")
    private String lastName;

    @JsonProperty("first_name")
    @NotNull(message = "Tên không được để trống")
    private String firstName;

    @JsonProperty("date_of_birth")
    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dateOfBirth;

    @JsonProperty("gender")
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("citizen_id")
    @NotNull(message = "Số CCCD không được để trống")
    private String citizenId;

    @JsonProperty("email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ")
    private String email;

    @JsonProperty("academic_rank")
    @NotNull(message = "Học vị không được để trống")
    private String academicRank;

    @JsonProperty("academic_title")
    @NotNull(message = "Học hàm không được để trống")
    private String academicTitle;

    @JsonProperty("specialization")
    @NotNull(message = "Chuyên môn không được để trống")
    private String specialization;

    @JsonProperty("image_url")
    private String imageUrl;
}
