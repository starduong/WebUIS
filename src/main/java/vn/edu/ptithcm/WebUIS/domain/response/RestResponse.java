package vn.edu.ptithcm.WebUIS.domain.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    private int statusCode;
    private String error;
    private Object message; // message có thể là string, hoặc arrayList
    private T data;
}
