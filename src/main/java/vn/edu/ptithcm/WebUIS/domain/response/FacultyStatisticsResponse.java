package vn.edu.ptithcm.WebUIS.domain.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response trả về thông tin thống kê điểm rèn luyện cho phòng CTSV
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyStatisticsResponse {

    private String semesterName;
    private String academicYear;

    // Tổng số sinh viên tham gia đánh giá
    private Integer totalStudents;

    // Số lượng sinh viên đã hoàn thành đánh giá
    private Integer completedCount;

    // Số lượng sinh viên đang thực hiện đánh giá
    private Integer inProgressCount;

    // Số lượng sinh viên chưa bắt đầu đánh giá
    private Integer notStartedCount;

    // Thống kê theo xếp loại
    private Map<String, Integer> classificationStatistics;

    // Thống kê theo khoa
    private List<DepartmentStatistics> departmentStatistics;

    // Thống kê theo lớp
    private List<ClassStatistics> classStatistics;

    /**
     * Thống kê theo khoa
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentStatistics {
        private String departmentId;
        private String departmentName;

        private Integer totalStudents;
        private Integer completedCount;
        private Integer inProgressCount;
        private Integer notStartedCount;

        private Map<String, Integer> classificationStatistics;
    }

    /**
     * Thống kê theo lớp
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClassStatistics {
        private String classId;
        private String className;
        private String departmentName;

        private Integer totalStudents;
        private Integer completedCount;
        private Integer inProgressCount;
        private Integer notStartedCount;

        private Map<String, Integer> classificationStatistics;
    }
}