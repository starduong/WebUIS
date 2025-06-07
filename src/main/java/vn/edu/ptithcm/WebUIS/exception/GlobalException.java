package vn.edu.ptithcm.WebUIS.exception;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptithcm.WebUIS.domain.response.RestResponse;
import vn.edu.ptithcm.WebUIS.util.Constants;

/**
 * Xử lý toàn cục các ngoại lệ có thể xảy ra trong ứng dụng.
 * Đảm bảo phản hồi nhất quán cho người dùng và ghi log chi tiết.
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * Xử lý tất cả các ngoại lệ không được xử lý riêng
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<Object>> handleAllException(Exception ex) {
        log.error("Unhandled exception occurred", ex);

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setMessage("Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau hoặc liên hệ quản trị viên.");
        res.setError("Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến xác thực và ủy quyền
     */
    @ExceptionHandler({
            UsernameNotFoundException.class,
            BadCredentialsException.class,
            DisabledException.class,
            LockedException.class,
            AuthenticationException.class
    })
    public ResponseEntity<RestResponse<Object>> handleAuthenticationException(Exception ex) {
        log.warn("Authentication exception: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        if (ex instanceof BadCredentialsException) {
            res.setMessage(Constants.ErrorMessage.INVALID_CREDENTIALS);
        } else if (ex instanceof DisabledException) {
            res.setMessage(Constants.ErrorMessage.ACCOUNT_DISABLED);
        } else if (ex instanceof UsernameNotFoundException) {
            res.setMessage(ex.getMessage());
        } else {
            res.setMessage("Lỗi xác thực: " + ex.getMessage());
        }

        res.setError("Authentication Error");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến quyền truy cập
     */
    @ExceptionHandler({
            AccessDeniedException.class,
            PermissionException.class
    })
    public ResponseEntity<RestResponse<Object>> handleAccessDeniedException(Exception ex) {
        log.warn("Access denied: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.FORBIDDEN.value());
        res.setMessage(Constants.ErrorMessage.ACCESS_DENIED);
        res.setError("Forbidden");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến ID không hợp lệ
     */
    @ExceptionHandler({
            IdInValidException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<RestResponse<Object>> handleIdException(Exception ex) {
        log.warn("Invalid ID or entity not found: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setMessage(ex.getMessage());
        res.setError("Invalid ID or Not Found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến tài nguyên không tìm thấy
     */
    @ExceptionHandler({
            NoResourceFoundException.class,
            NoHandlerFoundException.class,
            ResourceNotFoundException.class
    })
    public ResponseEntity<RestResponse<Object>> handleNotFoundException(Exception ex) {
        log.warn("Resource not found: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());

        if (ex instanceof ResourceNotFoundException resourceNotFoundException) {
            res.setMessage(ex.getMessage());
            res.setDetails(String.format(
                    "Tài nguyên '%s' không tìm thấy với %s: '%s'",
                    resourceNotFoundException.getResourceName(),
                    resourceNotFoundException.getFieldName(),
                    resourceNotFoundException.getFieldValue()));
        } else {
            res.setMessage("Không tìm thấy tài nguyên yêu cầu");
        }

        res.setError("Resource Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến dữ liệu không hợp lệ từ request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        List<String> errors = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        String errorMessage = errors.size() > 1
                ? "Dữ liệu nhập vào không hợp lệ"
                : errors.get(0);

        log.warn("Validation error: {}", errorMessage);

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Validation Error");
        res.setMessage(errorMessage);
        res.setDetails(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến ràng buộc dữ liệu
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestResponse<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        log.warn("Constraint violation: {}", errors);

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Constraint Violation");
        res.setMessage("Dữ liệu không đáp ứng các ràng buộc");
        res.setDetails(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến trùng lặp dữ liệu
     */
    @ExceptionHandler({
            EntityExistsException.class,
            DataIntegrityViolationException.class,
            DuplicateResourceException.class
    })
    public ResponseEntity<RestResponse<Object>> handleDataIntegrityViolation(Exception ex) {
        log.warn("Data integrity violation: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.CONFLICT.value());
        res.setError("Data Integrity Violation");

        if (ex instanceof DuplicateResourceException duplicateResourceException) {
            res.setMessage(ex.getMessage());
            res.setDetails(String.format(
                    "Tài nguyên '%s' đã tồn tại với %s: '%s'",
                    duplicateResourceException.getResourceName(),
                    duplicateResourceException.getFieldName(),
                    duplicateResourceException.getFieldValue()));
        } else {
            res.setMessage("Dữ liệu đã tồn tại hoặc vi phạm ràng buộc toàn vẹn");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến yêu cầu không hợp lệ
     */
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            BadRequestException.class
    })
    public ResponseEntity<RestResponse<Object>> handleBadRequest(Exception ex) {
        log.warn("Bad request: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Bad Request");

        if (ex instanceof BadRequestException) {
            res.setMessage(ex.getMessage());
        } else {
            res.setMessage("Dữ liệu yêu cầu không hợp lệ hoặc thiếu tham số bắt buộc");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến phương thức HTTP không được hỗ trợ
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestResponse<Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.warn("Method not supported: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        res.setError("Method Not Allowed");
        res.setMessage("Phương thức HTTP không được hỗ trợ");

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến kiểu dữ liệu không được hỗ trợ
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RestResponse<Object>> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        log.warn("Media type not supported: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        res.setError("Unsupported Media Type");
        res.setMessage("Kiểu dữ liệu không được hỗ trợ");

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến tải lên tệp
     */
    @ExceptionHandler({
            MaxUploadSizeExceededException.class,
            MultipartException.class,
            StorageException.class
    })
    public ResponseEntity<RestResponse<Object>> handleFileUploadException(Exception ex) {
        log.warn("File upload error: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("File Upload Error");

        if (ex instanceof MaxUploadSizeExceededException) {
            res.setMessage("Kích thước tệp vượt quá giới hạn cho phép");
        } else {
            res.setMessage("Lỗi khi tải lên tệp: " + ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /**
     * Xử lý các ngoại lệ liên quan đến JWT token
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<RestResponse<Object>> handleJwtException(JwtException ex) {
        log.warn("JWT error: {}", ex.getMessage());

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        res.setError("JWT Error");
        res.setMessage(Constants.ErrorMessage.INVALID_TOKEN);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
}
