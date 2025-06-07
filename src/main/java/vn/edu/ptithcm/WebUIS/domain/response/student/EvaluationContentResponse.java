package vn.edu.ptithcm.WebUIS.domain.response.student;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response trả về thông tin nội dung đánh giá điểm rèn luyện của tiêu chí
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationContentResponse {
    private Integer id;
    private String content;

    @JsonProperty("max_score")
    private Integer maxScore;

    @JsonProperty("total_student_score")
    private Integer totalStudentScore;

    @JsonProperty("total_class_committee_score")
    private Integer totalClassCommitteeScore;

    @JsonProperty("total_academic_advisor_score")
    private Integer totalAcademicAdvisorScore;

    @JsonProperty("evaluation_content_details")
    private List<EvaluationContentDetailResponse> evaluationContentDetails;
}
