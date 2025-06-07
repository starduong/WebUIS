package vn.edu.ptithcm.WebUIS.domain.response.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response trả về thông tin chi tiết nội dung đánh giá điểm rèn luyện của tiêu
 * chí con
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationContentDetailResponse {
    private Integer id;
    private String content;
    private Integer score;

    @JsonProperty("student_score")
    private Integer studentScore;

    @JsonProperty("class_committee_score")
    private Integer classCommitteeScore;

    @JsonProperty("academic_advisor_score")
    private Integer academicAdvisorScore;
}
