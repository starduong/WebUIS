package vn.edu.ptithcm.WebUIS.domain.enumeration;

/**
 * Enum định nghĩa các vai trò trong hệ thống
 */
public enum RoleEnum {
    STUDENT("STUDENT", "Sinh viên"),
    LECTURER("LECTURER", "Giảng viên"),
    ACADEMIC_ADVISOR("ACADEMIC_ADVISOR", "Cố vấn học tập"),
    CLASS_COMMITTEE("CLASS_COMMITTEE", "Ban cán sự lớp"),
    EMPLOYEE_FACULTY("EMPLOYEE_FACULTY", "Nhân viên khoa"),
    EMPLOYEE_DEPARTMENT("EMPLOYEE_DEPARTMENT", "Nhân viên phòng ban");

    private final String code;
    private final String displayName;

    RoleEnum(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Chuyển đổi từ chuỗi sang enum
     * 
     * @param code Mã vai trò
     * @return Đối tượng RoleEnum tương ứng hoặc null nếu không tìm thấy
     */
    public static RoleEnum fromCode(String code) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.code.equals(code)) {
                return role;
            }
        }
        return null;
    }
}