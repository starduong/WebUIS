package vn.edu.ptithcm.WebUIS.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lớp chuẩn hóa cấu trúc phản hồi API
 * 
 * @param <T> Kiểu dữ liệu của data
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("error")
    private String error;
    @JsonProperty("details")
    private Object details; // Chi tiết bổ sung, thường dùng cho thông báo lỗi
    @JsonProperty("message")
    private Object message; // message có thể là string, hoặc arrayList
    @JsonProperty("data")
    private T data;

}
