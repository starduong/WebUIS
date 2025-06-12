package vn.edu.ptithcm.WebUIS.controller.student;

import java.io.IOException;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.request.student.CreateComplaintRequest;
import vn.edu.ptithcm.WebUIS.domain.response.ComplaintDetailResponse;
import vn.edu.ptithcm.WebUIS.domain.response.ResultPaginationDTO;
import vn.edu.ptithcm.WebUIS.service.ComplaintService;
import vn.edu.ptithcm.WebUIS.service.StudentService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final StudentService studentService;

    /**
     * Lấy danh sách khiếu nại của sinh viên
     * 
     * @param pageable
     * @return
     */
    @GetMapping
    @ApiMessage("Lấy danh sách khiếu nại của sinh viên")
    public ResponseEntity<ResultPaginationDTO> getComplaints(Pageable pageable) {
        Student student = studentService.getCurrentStudentLogin();
        return ResponseEntity.ok(complaintService.getComplaintsByStudentId(student.getStudentId(), pageable));
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
     * Tạo khiếu nại
     * 
     * @param complaintRequest
     * @param attachment
     * @return
     * @throws IOException
     */
    @PostMapping
    @ApiMessage("Tạo khiếu nại")
    public ResponseEntity<ComplaintDetailResponse> createComplaint(
            @Valid @RequestBody CreateComplaintRequest complaintRequest,
            @RequestPart(value = "attachment", required = false) MultipartFile attachment) throws IOException {
        return ResponseEntity.ok(studentService.createComplaint(complaintRequest, attachment));
    }
}
