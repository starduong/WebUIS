package vn.edu.ptithcm.WebUIS.domain.response.classcommittee;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScoreTimeResponse {
    @JsonProperty("semester_id")
    private Integer semesterId;
    @JsonProperty("semester_order")
    private Integer semesterOrder;
    @JsonProperty("semester_year")
    private String semesterYear;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
}
