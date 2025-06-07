package vn.edu.ptithcm.WebUIS.util;

/**
 * Lớp chứa các hằng số sử dụng trong ứng dụng
 */
public final class Constants {

    /**
     * Các hằng số liên quan đến bảo mật
     */
    public static final class Security {
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final String AUTHORITIES_KEY = "role";
        public static final String USER_KEY = "user";
        public static final String REFRESH_TOKEN_COOKIE = "refresh_token";

        // Các đường dẫn không cần xác thực
        public static final String[] AUTH_WHITELIST = {
                "/",
                "/actuator/**",
                "/login",
                "/api/v1/auth/login",
                "/api/v1/auth/refresh-token",
                "/swagger-ui/**",
                "/v3/api-docs/**"
        };

        private Security() {
            // Private constructor to prevent instantiation
        }
    }

    /**
     * Các hằng số liên quan đến thông báo lỗi
     */
    public static final class ErrorMessage {
        public static final String USER_NOT_FOUND = "Người dùng không tồn tại";
        public static final String INVALID_CREDENTIALS = "Tên đăng nhập hoặc mật khẩu không đúng";
        public static final String INVALID_TOKEN = "Token không hợp lệ hoặc đã hết hạn";
        public static final String ACCOUNT_DISABLED = "Tài khoản đã bị khóa";
        public static final String REFRESH_TOKEN_MISSING = "Bạn không có refresh token ở cookie";
        public static final String REFRESH_TOKEN_INVALID = "Refresh token không hợp lệ";
        public static final String ACCESS_DENIED = "Bạn không có quyền truy cập tài nguyên này";

        private ErrorMessage() {
            // Private constructor to prevent instantiation
        }
    }

    private Constants() {
        // Private constructor to prevent instantiation
    }
}