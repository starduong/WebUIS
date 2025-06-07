package vn.edu.ptithcm.WebUIS.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.ptithcm.WebUIS.domain.response.RestResponse;
import vn.edu.ptithcm.WebUIS.util.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

/**
 * Xử lý và trả về thông báo lỗi khi xác thực thất bại
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // Gọi delegate để thiết lập các header HTTP phù hợp
        this.delegate.commence(request, response, authException);

        // Thiết lập response
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Xây dựng thông báo lỗi
        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        // Lấy thông báo lỗi từ exception
        String errorMessage = Optional.ofNullable(authException.getCause())
                .map(Throwable::getMessage)
                .orElse(authException.getMessage());
        res.setError(errorMessage);

        // Đặt thông báo lỗi người dùng thân thiện
        res.setMessage(Constants.ErrorMessage.INVALID_TOKEN);

        // Ghi log lỗi
        log.error("Authentication failed: {}", errorMessage);

        // Ghi response
        mapper.writeValue(response.getWriter(), res);
    }
}