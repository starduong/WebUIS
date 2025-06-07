package vn.edu.ptithcm.WebUIS.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.service.AccountService;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.service.AcademicAdvisorService;
import vn.edu.ptithcm.WebUIS.service.StudentService;

@Component("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class UserDetailsCustom implements UserDetailsService {
    private final AccountService accountService;
    private final StudentService studentService;
    private final AcademicAdvisorService lecturerService;
    private final EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);

        Account account = accountService.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(Constants.ErrorMessage.USER_NOT_FOUND);
        }

        // Kiểm tra trạng thái của tài khoản dựa vào vai trò
        boolean accountEnabled = true;
        String roleName = account.getRole().getName();

        if (roleName.equals("STUDENT")) {
            Student student = studentService.getStudentByAccount(account);
            if (student == null || !student.getStatus()) {
                accountEnabled = false;
            }
        } else if (roleName.equals("LECTURER")) {
            Lecturer lecturer = lecturerService.getLecturerByAccount(account);
            if (lecturer == null || !lecturer.getStatus()) {
                accountEnabled = false;
            }
        } else if (roleName.equals("EMPLOYEE_DEPARTMENT") || roleName.equals("EMPLOYEE_FACULTY")) {
            Employee employee = employeeService.getEmployeeByAccount(account);
            if (employee == null || !employee.getStatus()) {
                accountEnabled = false;
            }
        }

        if (!accountEnabled) {
            throw new UsernameNotFoundException(Constants.ErrorMessage.ACCOUNT_DISABLED);
        }

        // Tạo danh sách quyền
        Collection<GrantedAuthority> authorities = buildUserAuthority(account);

        // Trả về đối tượng User với tên đăng nhập, mật khẩu và danh sách quyền
        return new User(
                account.getUsername(),
                account.getPassword(),
                true, // enabled
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                authorities);
    }

    /**
     * Xây dựng danh sách quyền của người dùng
     * 
     * @param account Tài khoản người dùng
     * @return Danh sách quyền
     */
    private Collection<GrantedAuthority> buildUserAuthority(Account account) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Thêm quyền chính từ bảng Role
        authorities.add(new SimpleGrantedAuthority(account.getRole().getName()));

        // Có thể thêm các quyền bổ sung dựa trên logic nghiệp vụ
        String roleName = account.getRole().getName();
        if (roleName.equals("STUDENT")) {
            Student student = studentService.getStudentByAccount(account);
            if (student != null) {
                // Kiểm tra xem sinh viên có phải là ban cán sự lớp không
                // Thêm quyền tương ứng nếu cần
            }
        }

        return authorities;
    }
}
