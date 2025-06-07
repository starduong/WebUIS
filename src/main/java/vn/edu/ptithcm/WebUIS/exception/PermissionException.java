package vn.edu.ptithcm.WebUIS.exception;

/**
 * Ngoại lệ được ném ra khi người dùng không có quyền truy cập vào tài nguyên
 */
public class PermissionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}