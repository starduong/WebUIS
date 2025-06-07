package vn.edu.ptithcm.WebUIS.exception;

/**
 * Ngoại lệ được ném ra khi có lỗi xảy ra liên quan đến JWT token
 */
public class JwtException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }
}