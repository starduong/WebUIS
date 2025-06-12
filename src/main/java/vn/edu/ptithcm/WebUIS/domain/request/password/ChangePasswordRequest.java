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
public class ChangePasswordRequest {
    @JsonProperty("account_id")
    private Integer accountId;

    @JsonProperty("old_password")
    @NotBlank(message = "Vui lòng nhập mật khẩu cũ")
    private String oldPassword;

    @JsonProperty("new_password")
    @NotBlank(message = "Vui lòng nhập mật khẩu mới")
    private String newPassword;

    @JsonProperty("confirm_password")
    @NotBlank(message = "Vui lòng xác nhận mật khẩu mới")
    private String confirmPassword;
}
