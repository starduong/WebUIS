package vn.edu.ptithcm.WebUIS.domain.mapper;

import org.springframework.stereotype.Component;

import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.response.employee.EmployeeResponse;

@Component
public class EmployeeMapper {

    public EmployeeResponse convertEmployeeToDTO(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getGender(),
                employee.getPhoneNumber(),
                employee.getCitizenId(),
                employee.getEmail(),
                employee.getImageUrl(),
                employee.getStatus(),
                new EmployeeResponse.EmployeeAccount(employee.getAccount().getId(),
                        employee.getAccount().getUsername()),
                new EmployeeResponse.EmployeeDepartment(employee.getDepartment().getDepartmentId(),
                        employee.getDepartment().getDepartmentName()),
                new EmployeeResponse.EmployeeRole(employee.getAccount().getRole().getId(),
                        employee.getAccount().getRole().getName()));
    }
}
