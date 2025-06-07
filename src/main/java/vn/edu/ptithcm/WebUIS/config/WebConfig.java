package vn.edu.ptithcm.WebUIS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * Lớp cấu hình các thành phần web, bao gồm xử lý ngoại lệ
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Cấu hình bộ xử lý ngoại lệ tùy chỉnh
     */
    @Bean
    public HandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setWarnLogCategory(WebConfig.class.getName());
        return resolver;
    }
}