package vn.edu.ptithcm.WebUIS.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntityResponse {
    @JsonProperty("class_id")
    private String classId;
    @JsonProperty("major")
    private String major;
    @JsonProperty("education_level")
    private String educationLevel;
    @JsonProperty("academic_year")
    private String academicYear;
    @JsonProperty("quantity_student")
    private Integer quantityStudent;
}
