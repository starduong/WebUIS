package vn.edu.ptithcm.WebUIS.domain.response.student;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormTrainingScoreResponse {
    private Integer id;
    private List<CriterionResponse> criterions;

    @JsonProperty("total_score")
    private Integer totalScore;
    private TrainingScoreStatus status;
}
