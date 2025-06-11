package vn.edu.ptithcm.WebUIS.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Announcement;
import vn.edu.ptithcm.WebUIS.domain.response.ResultPaginationDTO;
import vn.edu.ptithcm.WebUIS.repository.AnnouncementRepository;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    /**
     * Lấy danh sách thông báo theo trang với ngày gửi mới nhất
     * 
     * @param pageable
     * @return
     */
    public ResultPaginationDTO getAnnouncements(Pageable pageable) {
        // Giảm pageIndex đi 1 nếu lớn hơn 0
        int pageIndex = pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : 0;

        Pageable sortedPageable = PageRequest.of(
                pageIndex,
                pageable.getPageSize(),
                Sort.by("sendDate").descending());

        Page<Announcement> announcements = announcementRepository.findAll(sortedPageable);

        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();

        // Trả lại đúng số trang mà người dùng truyền vào
        meta.setCurrentPage(pageable.getPageNumber());
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalPages(announcements.getTotalPages());
        meta.setTotalItems(announcements.getTotalElements());

        result.setMeta(meta);
        result.setData(announcements.getContent());

        return result;
    }

}