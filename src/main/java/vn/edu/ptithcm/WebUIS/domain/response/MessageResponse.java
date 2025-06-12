package vn.edu.ptithcm.WebUIS.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    @JsonProperty("response_message")
    private String message;

    public static MessageResponse of(String message) {
        return new MessageResponse(message);
    }
}