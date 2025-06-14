package vn.edu.ptithcm.WebUIS.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.enumeration.RoleEnum;
import vn.edu.ptithcm.WebUIS.domain.mapper.EmployeeMapper;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateEmployeeRequest;
import vn.edu.ptithcm.WebUIS.domain.response.employee.EmployeeResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.EmployeeRepository;
import vn.edu.ptithcm.WebUIS.util.S3UploadFileUtil;
import vn.edu.ptithcm.WebUIS.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AccountService accountService;
    private final EmployeeMapper employeeMapper;
    private final S3UploadFileUtil s3UploadFileUtil;

    /**
     * Lấy thông tin nhân viên đang đăng nhập
     * 
     * @return
     */
    public Employee getCurrentEmployeeLogin() {
        String username = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get()
                : null;
        return employeeRepository.findByAccount(accountService.findByUsername(username));
    }

    // check employee is faculty or department
    public boolean isFaculty(Employee employee) {
        Account account = employee.getAccount();
        if (account.getRole().getName().equals(RoleEnum.EMPLOYEE_FACULTY.name())) {
            return true;
        }
        return false;
    }

    // check employee is department
    public boolean isDepartment(Employee employee) {
        Account account = employee.getAccount();
        if (account.getRole().getName().equals(RoleEnum.EMPLOYEE_DEPARTMENT.name())) {
            return true;
        }
        return false;
    }

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::convertEmployeeToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(String id) {
        return employeeMapper.convertEmployeeToDTO(employeeRepository.findById(id).orElse(null));
    }

    public Employee getEmployeeByAccount(Account account) {
        return employeeRepository.findByAccount(account);
    }

    /**
     * Cập nhật thông tin nhân viên
     * 
     * @param employeeId
     * @param updateEmployeeRequest
     * @return
     * @throws IdInValidException
     * @throws IOException
     */
    @Transactional(rollbackOn = { IdInValidException.class, IOException.class })
    public EmployeeResponse updateEmployee(String employeeId, UpdateEmployeeRequest updateEmployeeRequest,
            MultipartFile avatar) throws IdInValidException, IOException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new IdInValidException("Nhân viên không tồn tại");
        }
        employee.setLastName(updateEmployeeRequest.getLastName());
        employee.setFirstName(updateEmployeeRequest.getFirstName());
        employee.setGender(updateEmployeeRequest.getGender());
        employee.setPhoneNumber(updateEmployeeRequest.getPhoneNumber());
        if (updateEmployeeRequest.getCitizenId() != null
                && !employee.getCitizenId().equals(updateEmployeeRequest.getCitizenId())) {
            if (employeeRepository.existsByCitizenId(updateEmployeeRequest.getCitizenId())) {
                throw new IdInValidException("Số CCCD đã tồn tại");
            }
            employee.setCitizenId(updateEmployeeRequest.getCitizenId());
        }
        employee.setEmail(updateEmployeeRequest.getEmail());
        if (avatar != null) {
            employee.setImageUrl(s3UploadFileUtil.uploadFile(avatar, "temps"));
        }
        employeeRepository.save(employee);
        return employeeMapper.convertEmployeeToDTO(employee);
    }

}
