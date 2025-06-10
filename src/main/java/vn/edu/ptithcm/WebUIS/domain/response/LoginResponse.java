package vn.edu.ptithcm.WebUIS.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user_login")
    private UserLogin userLogin;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLogin {
        @JsonProperty("user_id")
        private String userId;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("email")
        private String email;
        @JsonProperty("role")
        private String roleName;
        @JsonProperty("position")
        private String position;
    }
}
