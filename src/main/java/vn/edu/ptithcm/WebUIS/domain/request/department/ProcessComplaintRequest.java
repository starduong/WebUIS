package vn.edu.ptithcm.WebUIS.domain.request.department;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessComplaintRequest {
    @JsonProperty("response")
    @NotBlank(message = "Vui lòng nhập phản hồi")
    private String response;
}
