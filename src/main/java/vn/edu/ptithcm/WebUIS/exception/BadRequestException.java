package vn.edu.ptithcm.WebUIS.exception;

/**
 * Ngoại lệ được ném ra khi request không hợp lệ
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}