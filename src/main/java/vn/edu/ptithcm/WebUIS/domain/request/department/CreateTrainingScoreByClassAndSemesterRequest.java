package vn.edu.ptithcm.WebUIS.domain.request.department;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingScoreByClassAndSemesterRequest {
    @NotBlank(message = "Vui lòng chọn mã lớp")
    @JsonProperty("class_id")
    private String classId;
    @NotNull(message = "Vui lòng chọn học kỳ")
    @JsonProperty("semester_id")
    private Integer semesterId;
    @NotNull(message = "Vui lòng chọn ngày bắt đầu")
    @FutureOrPresent(message = "Ngày bắt đầu phải là ngày hiện tại hoặc trong tương lai")
    @JsonProperty("start_date")
    private LocalDate startDate;
    @NotNull(message = "Vui lòng chọn ngày kết thúc")
    @Future(message = "Ngày kết thúc phải là ngày trong tương lai")
    @JsonProperty("end_date")
    private LocalDate endDate;
}
