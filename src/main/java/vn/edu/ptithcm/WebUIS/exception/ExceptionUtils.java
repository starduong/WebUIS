package vn.edu.ptithcm.WebUIS.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Lớp tiện ích cung cấp các phương thức hỗ trợ xử lý ngoại lệ
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Kiểm tra điều kiện và ném ngoại lệ ResourceNotFoundException nếu điều kiện
     * không thoả mãn
     * 
     * @param condition    Điều kiện cần kiểm tra
     * @param resourceName Tên tài nguyên
     * @param fieldName    Tên trường
     * @param fieldValue   Giá trị trường
     */
    public static void checkResourceFound(boolean condition, String resourceName, String fieldName, Object fieldValue) {
        if (!condition) {
            throw new ResourceNotFoundException(resourceName, fieldName, fieldValue);
        }
    }

    /**
     * Kiểm tra điều kiện và ném ngoại lệ DuplicateResourceException nếu điều kiện
     * không thoả mãn
     * 
     * @param condition    Điều kiện cần kiểm tra (thường là điều kiện để đảm bảo
     *                     không trùng lặp)
     * @param resourceName Tên tài nguyên
     * @param fieldName    Tên trường
     * @param fieldValue   Giá trị trường
     */
    public static void checkDuplicate(boolean condition, String resourceName, String fieldName, Object fieldValue) {
        if (!condition) {
            throw new DuplicateResourceException(resourceName, fieldName, fieldValue);
        }
    }

    /**
     * Kiểm tra điều kiện và ném ngoại lệ BadRequestException nếu điều kiện không
     * thoả mãn
     * 
     * @param condition Điều kiện cần kiểm tra
     * @param message   Thông báo lỗi
     */
    public static void checkBadRequest(boolean condition, String message) {
        if (!condition) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Kiểm tra điều kiện và ném ngoại lệ PermissionException nếu điều kiện không
     * thoả mãn
     * 
     * @param hasPermission Điều kiện quyền truy cập
     * @param message       Thông báo lỗi
     */
    public static void checkPermission(boolean hasPermission, String message) {
        if (!hasPermission) {
            throw new PermissionException(message);
        }
    }

    /**
     * Kiểm tra người dùng tồn tại
     * 
     * @param condition Điều kiện người dùng tồn tại
     * @param username  Tên đăng nhập
     */
    public static void checkUserExists(boolean condition, String username) {
        if (!condition) {
            throw new UsernameNotFoundException("Người dùng '" + username + "' không tồn tại");
        }
    }

    /**
     * Kiểm tra thông tin đăng nhập
     * 
     * @param condition Điều kiện thông tin đăng nhập hợp lệ
     * @param message   Thông báo lỗi
     */
    public static void checkCredentials(boolean condition, String message) {
        if (!condition) {
            throw new BadCredentialsException(message);
        }
    }
}