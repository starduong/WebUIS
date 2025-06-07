package vn.edu.ptithcm.WebUIS.domain.response.student;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private String studentId;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private Boolean gender;
    private String phoneNumber;
    private String citizenId;
    private String universityEmail;
    private String personalEmail;
    private String imagePath;
    private String hometown;
    private String address;
    private String permanentAddress;
    private String ethnicity;
    private Boolean status;
    private StudentAccount studentAccount;
    private StudentMajor studentMajor;
    private StudentClass studentClass;
    private StudentFaculty studentFaculty;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentAccount {
        private Integer id;
        private String username;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentMajor {
        private String id;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentClass {
        private String id;
        private String educationLevel;
        private String academicYear;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentFaculty {
        private String id;
        private String name;
    }

}