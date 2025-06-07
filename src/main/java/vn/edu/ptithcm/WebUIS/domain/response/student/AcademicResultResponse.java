package vn.edu.ptithcm.WebUIS.domain.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResultResponse {
    private String studentId;
    private int semesterOrder;
    private String semesterYear;
    private Double gpa;
}
