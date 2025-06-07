package vn.edu.ptithcm.WebUIS.domain.response.lecturer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerResponse {
    @JsonProperty("lecturer_id")
    private String lecturerId;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("gender")
    private Boolean gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("citizen_id")
    private String citizenId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("academic_rank")
    private String academicRank;
    @JsonProperty("academic_title")
    private String academicTitle;
    @JsonProperty("specialization")
    private String specialization;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("lecturer_account")
    private LecturerAccount lecturerAccount;
    @JsonProperty("lecturer_department")
    private LecturerDepartment lecturerDepartment;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LecturerAccount {
        @JsonProperty("account_id")
        private Integer accountId;
        @JsonProperty("username")
        private String username;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LecturerDepartment {
        @JsonProperty("department_id")
        private String departmentId;
        @JsonProperty("department_name")
        private String departmentName;
    }
}
