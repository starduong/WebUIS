package vn.edu.ptithcm.WebUIS.exception;

/**
 * Ngoại lệ được ném ra khi có lỗi xảy ra liên quan đến lưu trữ tệp
 */
public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}