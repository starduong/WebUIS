package vn.edu.ptithcm.WebUIS.domain.response.student;

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
public class ComplaintStudentResponse {
    @JsonProperty("complaint_id")
    private Integer complaintId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("send_date")
    private LocalDateTime sendDate;
    @JsonProperty("status")
    private String status;
}
