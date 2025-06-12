package vn.edu.ptithcm.WebUIS.domain.request.department;

import lombok.Getter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdjustTimeTrainingScoreRequest {
    @JsonProperty("class_id")
    private String classId;
    @JsonProperty("semester_id")
    private Integer semesterId;
    @JsonProperty("start_date")
    @FutureOrPresent(message = "Thời gian bắt đầu phải là ngày trong tương lai hoặc hôm nay")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    @Future(message = "Thời gian kết thúc phải là ngày trong tương lai")
    private LocalDateTime endDate;
}
