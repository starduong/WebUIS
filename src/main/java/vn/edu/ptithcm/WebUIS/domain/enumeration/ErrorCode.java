package vn.edu.ptithcm.WebUIS.domain.enumeration;

/**
 * Định nghĩa các mã lỗi sử dụng trong hệ thống
 */
public enum ErrorCode {
    // General errors
    INTERNAL_SERVER_ERROR("ERR-SYS-001", "Lỗi hệ thống"),
    BAD_REQUEST("ERR-REQ-001", "Yêu cầu không hợp lệ"),
    VALIDATION_ERROR("ERR-VAL-001", "Lỗi xác thực dữ liệu"),
    RESOURCE_NOT_FOUND("ERR-RES-001", "Tài nguyên không tìm thấy"),
    DUPLICATE_RESOURCE("ERR-RES-002", "Tài nguyên đã tồn tại"),

    // Authentication errors
    AUTHENTICATION_ERROR("ERR-AUTH-001", "Lỗi xác thực"),
    INVALID_CREDENTIALS("ERR-AUTH-002", "Thông tin đăng nhập không hợp lệ"),
    USER_NOT_FOUND("ERR-AUTH-003", "Không tìm thấy người dùng"),
    ACCOUNT_DISABLED("ERR-AUTH-004", "Tài khoản đã bị vô hiệu hóa"),
    TOKEN_EXPIRED("ERR-AUTH-005", "Token đã hết hạn"),
    INVALID_TOKEN("ERR-AUTH-006", "Token không hợp lệ"),

    // Permission errors
    ACCESS_DENIED("ERR-PER-001", "Quyền truy cập bị từ chối"),
    INSUFFICIENT_PRIVILEGES("ERR-PER-002", "Không đủ quyền thực hiện hành động này"),

    // File errors
    FILE_UPLOAD_ERROR("ERR-FILE-001", "Lỗi tải lên tệp"),
    FILE_TOO_LARGE("ERR-FILE-002", "Kích thước tệp quá lớn"),
    INVALID_FILE_TYPE("ERR-FILE-003", "Loại tệp không hợp lệ"),

    // Database errors
    DATABASE_ERROR("ERR-DB-001", "Lỗi cơ sở dữ liệu"),
    DATA_INTEGRITY_VIOLATION("ERR-DB-002", "Vi phạm tính toàn vẹn dữ liệu"),

    // Business logic errors
    INVALID_OPERATION("ERR-BIZ-001", "Thao tác không hợp lệ"),
    OPERATION_NOT_ALLOWED("ERR-BIZ-002", "Thao tác không được phép");

    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}