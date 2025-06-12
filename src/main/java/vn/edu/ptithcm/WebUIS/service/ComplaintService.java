package vn.edu.ptithcm.WebUIS.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Complaint;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.mapper.ComplaintMapper;
import vn.edu.ptithcm.WebUIS.domain.response.ComplaintDetailResponse;
import vn.edu.ptithcm.WebUIS.domain.response.ResultPaginationDTO;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.ComplaintRepository;
import vn.edu.ptithcm.WebUIS.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;
    private final StudentRepository studentRepository;

    /**
     * Lấy danh sách khiếu nại có phân trang và sắp xếp với ngày tạo mới nhất
     * 
     * @param pageable
     * @return
     */
    public ResultPaginationDTO getComplaints(Pageable pageable) {
        // Giảm pageIndex đi 1 nếu lớn hơn 0
        int pageIndex = pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : 0;

        Pageable sortedPageable = PageRequest.of(
                pageIndex,
                pageable.getPageSize(),
                Sort.by("sendDate").descending());

        Page<Complaint> complaints = complaintRepository.findAll(sortedPageable);

        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();

        // Trả lại đúng số trang mà người dùng truyền vào
        meta.setCurrentPage(pageable.getPageNumber());
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalPages(complaints.getTotalPages());
        meta.setTotalItems(complaints.getTotalElements());

        result.setMeta(meta);
        result.setData(complaints.getContent().stream().map(complaintMapper::toComplaintDepartmentResponse).toList());

        return result;
    }

    /**
     * Lấy danh sách khiếu nại của sinh viên
     * 
     * @param pageable
     * @return
     */
    public ResultPaginationDTO getComplaintsByStudentId(String studentId, Pageable pageable) {
        // Giảm pageIndex đi 1 nếu lớn hơn 0
        int pageIndex = pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : 0;

        Pageable sortedPageable = PageRequest.of(
                pageIndex,
                pageable.getPageSize(),
                Sort.by("sendDate").descending());

        Student student = studentRepository.findByStudentId(studentId);
        Page<Complaint> complaints = complaintRepository.findByStudent(student, sortedPageable);

        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();

        // Trả lại đúng số trang mà người dùng truyền vào
        meta.setCurrentPage(pageable.getPageNumber());
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalPages(complaints.getTotalPages());
        meta.setTotalItems(complaints.getTotalElements());

        result.setMeta(meta);
        result.setData(complaints.getContent().stream().map(complaintMapper::toComplaintStudentResponse).toList());

        return result;
    }

    /**
     * Lấy khiếu nại theo id
     * 
     * @param complaintId
     * @return
     * @throws IdInValidException
     */
    public Complaint getComplaintById(Integer complaintId) throws IdInValidException {
        return complaintRepository.findById(complaintId)
                .orElseThrow(() -> new IdInValidException("Khiếu nại không tồn tại"));
    }

    /**
     * Lấy chi tiết khiếu nại
     * 
     * @param complaintId
     * @return
     * @throws Exception
     */
    public ComplaintDetailResponse getComplaintDetail(Integer complaintId) throws Exception {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new Exception("Khiếu nại không tồn tại"));
        return complaintMapper.toComplaintDetailResponse(complaint);
    }
}
