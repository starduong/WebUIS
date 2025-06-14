package vn.edu.ptithcm.WebUIS.domain.mapper;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassCommittee;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.response.LoginResponse;
import vn.edu.ptithcm.WebUIS.repository.AcademicAdvisorRepository;
import vn.edu.ptithcm.WebUIS.repository.ClassCommitteeRepository;
import vn.edu.ptithcm.WebUIS.repository.EmployeeRepository;
import vn.edu.ptithcm.WebUIS.repository.LecturerRepository;
import vn.edu.ptithcm.WebUIS.repository.StudentRepository;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final StudentRepository studentRepository;
    private final AcademicAdvisorRepository academicAdvisorRepository;
    private final EmployeeRepository employeeRepository;
    private final LecturerRepository lecturerRepository;
    private final ClassCommitteeRepository classCommitteeRepository;

    public LoginResponse.UserLogin convertAccountLoginToResponse(Account account) {
        String roleName = account.getRole().getName();
        LoginResponse.UserLogin userLogin = new LoginResponse.UserLogin();
        if (roleName.equals("STUDENT")) {
            Student student = studentRepository.findByAccount(account);
            if (student != null && student.getStatus()) {
                userLogin.setUserId(student.getStudentId());
                userLogin.setFullName(student.getLastName() + " " + student.getFirstName());
                userLogin.setEmail(student.getUniversityEmail());
                userLogin.setRoleName(account.getRole().getName());
                userLogin.setPosition("");
            }
        } else if (roleName.equals("CLASS_COMMITTEE")) {
            Student student = studentRepository.findByAccount(account);
            if (student != null && student.getStatus()) {
                Optional<ClassCommittee> classCommittee = classCommitteeRepository.findByStudent(student);
                if (classCommittee.isPresent()) {
                    userLogin.setUserId(classCommittee.get().getStudent().getStudentId());
                    userLogin.setFullName(classCommittee.get().getStudent().getLastName() + " "
                            + classCommittee.get().getStudent().getFirstName());
                    userLogin.setEmail(classCommittee.get().getStudent().getUniversityEmail());
                    userLogin.setRoleName(account.getRole().getName());
                    userLogin.setPosition(classCommittee.get().getPosition());
                }
            }
        } else if (roleName.equals("ACADEMIC_ADVISOR")) {
            Lecturer lecturer = lecturerRepository.findByAccount(account);
            if (lecturer != null && lecturer.getStatus()) {
                if (academicAdvisorRepository.existsByLecturer(lecturer)) {
                    userLogin.setUserId(lecturer.getLecturerId());
                    userLogin.setFullName(lecturer.getLastName() + " " + lecturer.getFirstName());
                    userLogin.setEmail(lecturer.getEmail());
                    userLogin.setRoleName(account.getRole().getName());
                    userLogin.setPosition("");
                }
            }
        } else if (roleName.equals("EMPLOYEE_FACULTY")) {
            Employee employee = employeeRepository.findByAccount(account);
            if (employee != null && employee.getStatus()) {
                userLogin.setUserId(employee.getId());
                userLogin.setFullName(employee.getLastName() + " " + employee.getFirstName());
                userLogin.setEmail(employee.getEmail());
                userLogin.setRoleName(account.getRole().getName());
                userLogin.setPosition(employee.getDepartment().getDepartmentName());
            }
        } else if (roleName.equals("EMPLOYEE_DEPARTMENT")) {
            Employee employee = employeeRepository.findByAccount(account);
            if (employee != null && employee.getStatus()
                    && employee.getDepartment().getDepartmentId().equals("CTSV")) {
                userLogin.setUserId(employee.getId());
                userLogin.setFullName(employee.getLastName() + " " + employee.getFirstName());
                userLogin.setEmail(employee.getEmail());
                userLogin.setRoleName(account.getRole().getName());
                userLogin.setPosition(employee.getDepartment().getDepartmentName());
            }
        } else {
            throw new BadCredentialsException("login failed");
        }
        return userLogin;
    }
}
