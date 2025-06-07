package vn.edu.ptithcm.WebUIS.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountResponse {
    private Integer userId;
    private String username;
    private AccountRole accountRole;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountRole {
        private Integer roleId;
        private String roleName;
    }

}
