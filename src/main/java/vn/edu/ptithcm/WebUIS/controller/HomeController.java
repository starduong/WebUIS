package vn.edu.ptithcm.WebUIS.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Announcement;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.response.ResultPaginationDTO;
import vn.edu.ptithcm.WebUIS.service.AnnouncementService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {
    private final AnnouncementService announcementService;
    private final SemesterService semesterService;

    /**
     * Lấy danh sách thông báo
     * 
     * @param pageable
     * @return
     */
    @GetMapping("/announcements")
    @ApiMessage("Lấy danh sách thông báo")
    public ResponseEntity<ResultPaginationDTO> getAnnouncements(Pageable pageable) {
        return ResponseEntity.ok(announcementService.getAnnouncements(pageable));
    }

    /**
     * Lấy thông báo theo id
     * 
     * @param id
     * @return
     */
    @GetMapping("/announcements/{id}")
    @ApiMessage("Lấy thông báo theo id")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Integer id) {
        return ResponseEntity.ok(announcementService.getAnnouncementById(id));
    }

    /**
     * Lấy học kỳ hiện tại
     * 
     * @return
     */
    @GetMapping("/current-semester")
    @ApiMessage("Học kỳ hiện tại")
    public ResponseEntity<Semester> getCurrentSemester() {
        return ResponseEntity.ok(semesterService.getCurrentSemester());
    }

}
