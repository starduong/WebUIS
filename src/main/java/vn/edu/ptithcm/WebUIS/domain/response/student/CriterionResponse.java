package vn.edu.ptithcm.WebUIS.domain.response.student;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response trả về thông tin tiêu chí chấm điểm rèn luyện
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterionResponse {
    private Integer id;
    @JsonProperty("criterion_name")
    private String criterionName;
    @JsonProperty("max_score")
    private Integer maxScore; // tổng tất cả điểm tối đa của EvaluationContentResponse
    private Integer score; // tổng tất cả điểm của EvaluationContentResponse
    @JsonProperty("evaluation_contents")
    private List<EvaluationContentResponse> evaluationContents;
}