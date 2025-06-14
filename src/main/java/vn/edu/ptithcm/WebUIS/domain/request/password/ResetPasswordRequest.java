package vn.edu.ptithcm.WebUIS.domain.request.password;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "Token không được để trống")
    @JsonProperty("token")
    private String token;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @JsonProperty("new_password")
    private String newPassword;

    @NotBlank(message = "Mật khẩu xác nhận không được để trống")
    @JsonProperty("confirm_password")
    private String confirmPassword;
}