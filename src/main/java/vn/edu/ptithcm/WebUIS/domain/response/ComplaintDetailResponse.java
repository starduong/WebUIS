package vn.edu.ptithcm.WebUIS.domain.response;

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
public class ComplaintDetailResponse {
    @JsonProperty("complaint_id")
    private Integer complaintId;
    @JsonProperty("student_id")
    private String studentId;
    @JsonProperty("student_name")
    private String studentName;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("attachment_url")
    private String attachmentUrl;
    @JsonProperty("send_date")
    private LocalDateTime sendDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("employee_id")
    private String employeeId;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("response")
    private String response;
}
