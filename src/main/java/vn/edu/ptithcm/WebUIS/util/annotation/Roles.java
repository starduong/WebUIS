package vn.edu.ptithcm.WebUIS.util.annotation;

/**
 * Lớp chứa các hằng số định nghĩa vai trò trong hệ thống
 */
public final class Roles {
    // Vai trò người dùng
    public static final String STUDENT = "STUDENT";
    public static final String CLASS_COMMITTEE = "CLASS_COMMITTEE"; // Ban cán sự
    public static final String ACADEMIC_ADVISOR = "ACADEMIC_ADVISOR"; // Cố vấn học tập
    public static final String EMPLOYEE_FACULTY = "EMPLOYEE_FACULTY";
    public static final String EMPLOYEE_DEPARTMENT = "EMPLOYEE_DEPARTMENT";

    // Nhóm vai trò
    public static final String[] ROLE_STUDENT = { STUDENT };
    public static final String[] ROLE_CLASS_COMMITTEE = { CLASS_COMMITTEE, STUDENT }; // Ban cán sự cũng là sinh viên
    public static final String[] ROLE_ACADEMIC_ADVISOR = { ACADEMIC_ADVISOR };
    public static final String[] ROLE_EMPLOYEE_FACULTY = { EMPLOYEE_FACULTY };
    public static final String[] ROLE_EMPLOYEE_DEPARTMENT = { EMPLOYEE_DEPARTMENT };
    public static final String[] ROLE_ALL = { STUDENT, CLASS_COMMITTEE, ACADEMIC_ADVISOR,
            EMPLOYEE_DEPARTMENT, EMPLOYEE_FACULTY };

    private Roles() {
        // Private constructor to prevent instantiation
    }
}