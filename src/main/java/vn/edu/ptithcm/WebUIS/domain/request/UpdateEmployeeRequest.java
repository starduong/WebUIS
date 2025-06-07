package vn.edu.ptithcm.WebUIS.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    @JsonProperty("employee_id")
    private String employeeId;

    @JsonProperty("last_name")
    @NotNull(message = "Họ không được để trống")
    private String lastName;

    @JsonProperty("first_name")
    @NotNull(message = "Tên không được để trống")
    private String firstName;

    @JsonProperty("gender")
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @JsonProperty("phone_number")
    @NotNull(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @JsonProperty("citizen_id")
    @NotNull(message = "Số CCCD không được để trống")
    private String citizenId;

    @JsonProperty("email")
    @NotNull(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ")
    private String email;

    @JsonProperty("image_url")
    private String imageUrl;
}
