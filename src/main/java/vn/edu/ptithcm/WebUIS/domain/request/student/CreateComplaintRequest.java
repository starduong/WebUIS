package vn.edu.ptithcm.WebUIS.domain.request.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplaintRequest {
    @JsonProperty("title")
    @NotBlank(message = "Vui lòng nhập tiêu đề")
    private String title;
    @JsonProperty("content")
    @NotBlank(message = "Vui lòng nhập nội dung")
    private String content;
}
