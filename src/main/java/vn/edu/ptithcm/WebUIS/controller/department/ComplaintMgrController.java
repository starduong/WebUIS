package vn.edu.ptithcm.WebUIS.controller.department;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Complaint;
import vn.edu.ptithcm.WebUIS.domain.entity.Employee;
import vn.edu.ptithcm.WebUIS.domain.request.department.ProcessComplaintRequest;
import vn.edu.ptithcm.WebUIS.domain.response.ComplaintDetailResponse;
import vn.edu.ptithcm.WebUIS.domain.response.ResultPaginationDTO;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.ComplaintService;
import vn.edu.ptithcm.WebUIS.service.DepartmentService;
import vn.edu.ptithcm.WebUIS.service.EmployeeService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/department/complaints")
@RequiredArgsConstructor
public class ComplaintMgrController {
    private final ComplaintService complaintService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    /**
     * Lấy danh sách khiếu nại
     * 
     * @param pageable
     * @return
     */
    @GetMapping
    @ApiMessage("Lấy danh sách khiếu nại")
    public ResponseEntity<ResultPaginationDTO> getComplaints(Pageable pageable) {
        return ResponseEntity.ok(complaintService.getComplaints(pageable));
    }

    /**
     * Lấy chi tiết khiếu nại
     * 
     * @param complaintId
     * @return
     * @throws Exception
     */
    @GetMapping("/{complaintId}")
    @ApiMessage("Lấy chi tiết khiếu nại")
    public ResponseEntity<ComplaintDetailResponse> getComplaintDetail(@PathVariable Integer complaintId)
            throws Exception {
        return ResponseEntity.ok(complaintService.getComplaintDetail(complaintId));
    }

    /**
     * Phòng CTSV phản hồi khiếu nại
     * 
     * @param complaintId
     * @param processComplaintRequest
     * @return
     * @throws IdInValidException
     */
    @PostMapping("/{complaintId}")
    @ApiMessage("Phòng CTSV phản hồi khiếu nại")
    public ResponseEntity<ComplaintDetailResponse> processComplaint(@PathVariable Integer complaintId,
            @RequestBody ProcessComplaintRequest processComplaintRequest) throws IdInValidException {
        Employee employee = employeeService.getCurrentEmployeeLogin();
        Complaint complaint = complaintService.getComplaintById(complaintId);
        complaint.setEmployee(employee);
        return ResponseEntity.ok(departmentService.processComplaint(complaint, processComplaintRequest));
    }

}
