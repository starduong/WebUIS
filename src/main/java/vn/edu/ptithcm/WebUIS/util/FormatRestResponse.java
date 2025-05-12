package vn.edu.ptithcm.WebUIS.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;
import vn.edu.ptithcm.WebUIS.domain.response.RestResponse;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object>{

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus();
        RestResponse<Object> restResponse = new RestResponse<Object>();

        if (body instanceof String) {
            return body;
        }

        restResponse.setStatusCode(status);
        if (status >= 400) {
            return body;
        } else {
            restResponse.setData(body);
            restResponse.setMessage("CALL API SUCCESS");
        }
        return restResponse;
    }
    
}
