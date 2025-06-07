package vn.edu.ptithcm.WebUIS.domain.response.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private String id;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("first_name")
    private String firstName;
    private Boolean gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("citizen_id")
    private String citizenId;
    private String email;
    @JsonProperty("image_url")
    private String imageUrl;
    private Boolean status;
    @JsonProperty("employee_account")
    private EmployeeAccount employeeAccount;
    @JsonProperty("employee_department")
    private EmployeeDepartment employeeDepartment;
    @JsonProperty("employee_role")
    private EmployeeRole employeeRole;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeAccount {
        private Integer id;
        private String username;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeDepartment {
        private String id;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeRole {
        private Integer id;
        private String name;
    }
}