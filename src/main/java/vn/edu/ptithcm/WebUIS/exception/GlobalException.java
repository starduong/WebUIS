package vn.edu.ptithcm.WebUIS.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.edu.ptithcm.WebUIS.domain.response.RestResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value={
        UsernameNotFoundException.class,
        BadCredentialsException.class
    })
    public ResponseEntity<Object> handleIdException(Exception ex) {
        RestResponse<Object> response = new RestResponse<Object>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setError(ex.getMessage());
        response.setMessage("Username or password is incorrect");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        RestResponse<Object> response = new RestResponse<Object>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setError(ex.getBody().getDetail());
        List<String> errorMessages = fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        response.setMessage(errorMessages.size() > 1 ? errorMessages: errorMessages.get(0));   
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }  
}
