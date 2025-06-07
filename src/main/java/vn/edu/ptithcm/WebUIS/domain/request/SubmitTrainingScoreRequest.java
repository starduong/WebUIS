package vn.edu.ptithcm.WebUIS.domain.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request đánh giá điểm rèn luyện của sinh viên - ban cán sự lớp - CVHT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTrainingScoreRequest {
    @JsonProperty("training_score_id")
    private Integer trainingScoreId;
    @JsonProperty("training_score_details")
    private List<TrainingScoreDetailRequest> trainingScoreDetails;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrainingScoreDetailRequest {
        @JsonProperty("evaluation_content_id")
        private Integer evaluationContentId;
        @DecimalMax(value = "15", message = "Điểm không được lớn hơn 15")
        @DecimalMin(value = "-15", message = "Điểm không được nhỏ hơn -15")
        @JsonProperty("score")
        private Integer score;
    }

}
