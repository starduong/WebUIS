package vn.edu.ptithcm.WebUIS.domain.response.student;

import java.time.LocalDateTime;

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
public class TrainingScoreBySemesterResponse {

    private Integer id;
    private SemesterResponse semester;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    @JsonProperty("total_score")
    private Integer totalScore;
    private TrainingScoreClassify classification;
    private TrainingScoreStatus status;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SemesterResponse {
        private Integer id;
        private Integer order;
        @JsonProperty("academic_year")
        private String academicYear;

    }
}