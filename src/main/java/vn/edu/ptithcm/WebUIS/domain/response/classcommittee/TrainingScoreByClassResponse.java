package vn.edu.ptithcm.WebUIS.domain.response.classcommittee;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreClassify;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScoreByClassResponse {
    @JsonProperty("training_score_id")
    private Integer trainingScoreId;
    @JsonProperty("student_id")
    private String studentId;
    @JsonProperty("student_first_name")
    private String studentFirstName;
    @JsonProperty("student_last_name")
    private String studentLastName;
    @JsonProperty("total_score")
    private Integer totalScore;
    @JsonProperty("classification")
    private TrainingScoreClassify classification;
    @JsonProperty("status")
    private TrainingScoreStatus status;
}
