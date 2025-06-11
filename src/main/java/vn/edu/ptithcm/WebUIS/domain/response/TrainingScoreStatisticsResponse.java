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
public class TrainingScoreStatisticsResponse {
    @JsonProperty("class_id")
    private String classId;
    @JsonProperty("semester_order")
    private String semesterOrder;
    @JsonProperty("semester_year")
    private String semesterYear;
    @JsonProperty("total_student")
    private int totalStudent;
    @JsonProperty("total_excellent")
    private int totalExcellent;
    @JsonProperty("total_good")
    private int totalGood;
    @JsonProperty("total_fair")
    private int totalFair;
    @JsonProperty("total_average")
    private int totalAverage;
    @JsonProperty("total_weak")
    private int totalWeak;
    @JsonProperty("total_poor")
    private int totalPoor;
}
