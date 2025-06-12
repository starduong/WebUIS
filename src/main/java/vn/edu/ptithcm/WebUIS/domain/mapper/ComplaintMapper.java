package vn.edu.ptithcm.WebUIS.domain.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Complaint;
import vn.edu.ptithcm.WebUIS.domain.response.ComplaintDetailResponse;
import vn.edu.ptithcm.WebUIS.domain.response.department.ComplaintDepartmentResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.ComplaintStudentResponse;

@RequiredArgsConstructor
@Component
public class ComplaintMapper {
    public ComplaintDepartmentResponse toComplaintDepartmentResponse(Complaint complaint) {
        return new ComplaintDepartmentResponse(
                complaint.getId(),
                complaint.getStudent().getStudentId(),
                complaint.getTitle(),
                complaint.getSendDate(),
                complaint.getStatus());
    }

    public ComplaintStudentResponse toComplaintStudentResponse(Complaint complaint) {
        return new ComplaintStudentResponse(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getSendDate(),
                complaint.getStatus());
    }

    public ComplaintDetailResponse toComplaintDetailResponse(Complaint complaint) {
        return new ComplaintDetailResponse(
                complaint.getId(),
                complaint.getStudent().getStudentId(),
                complaint.getStudent().getFirstName() + " " + complaint.getStudent().getLastName(),
                complaint.getTitle(),
                complaint.getContent(),
                complaint.getAttachmentUrl() == null ? null : complaint.getAttachmentUrl(),
                complaint.getSendDate(),
                complaint.getStatus(),
                complaint.getEmployee() == null ? null : complaint.getEmployee().getId(),
                complaint.getEmployee() == null ? null
                        : complaint.getEmployee().getFirstName() + " "
                                + complaint.getEmployee().getLastName(),
                complaint.getEmployee() == null ? null : complaint.getResponse());
    }
}
