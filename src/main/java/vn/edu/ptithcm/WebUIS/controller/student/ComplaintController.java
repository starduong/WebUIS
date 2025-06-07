package vn.edu.ptithcm.WebUIS.controller.student;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.FormTemplate;
import vn.edu.ptithcm.WebUIS.service.ComplaintService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    /**
     * Lấy danh sách all form template
     * 
     * @return all form template
     */
    @GetMapping("/all-form-template")
    @ApiMessage("Lấy danh sách all form template")
    public ResponseEntity<List<FormTemplate>> getAllFormTemplate() {
        return ResponseEntity.ok(complaintService.getAllFormTemplate());
    }
}
