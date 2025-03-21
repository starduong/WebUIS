package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SinhVien", schema = "SinhVien")
// library lambok
@Data // declare getter/setter
@NoArgsConstructor // declare constructor no parameters
@AllArgsConstructor // declare constructor has all parameters
public class Student {

    @Id
    @Column(name = "MaSV", length = 10, nullable = false)
    private String studentId;

    @Column(name = "Ho", length = 50, nullable = false)
    private String lastName;

    @Column(name = "Ten", length = 50, nullable = false)
    private String firstName;

    @Column(name = "NgaySinh", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "GioiTinh", nullable = false)
    private boolean gender;

    @Column(name = "SoDienThoai", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "CCCD", length = 12, nullable = false, unique = true)
    private String citizenId;

    @Column(name = "Email1", length = 100)
    private String primaryEmail;

    @Column(name = "Email2", length = 100)
    private String secondaryEmail;

    @Column(name = "HinhAnh", length = 255)
    private String imagePath;

    @Column(name = "Password", length = 255)
    private String password;

    @Column(name = "TrangThai", nullable = false)
    private boolean status;

    // Student (N) - Major (1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaCN", nullable = false)
    private Major major;

    // Student (N) - ClassEntity (1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLop", nullable = false)
    private ClassEntity classEntity;

}

// @formatter:off
/*
 * @Column: Dùng cho dữ liệu cơ bản, không liên quan đến quan hệ.
 * @JoinColumn: Dùng để định nghĩa khóa ngoại trong mối quan hệ giữa các entity.
 */

/*
 * FetchType.LAZY: Tải "lười biếng" (lazy loading). Dữ liệu liên quan không tải ngay, chỉ tải khi truy cập (on-demand). 
 * SELECT sv.*, l.* 
 * FROM SinhVien sv
 * INNER JOIN Lop l ON sv.MaLop = l.MaLop WHERE sv.MaSV = 'SV001';
 * 
 * FetchType.EAGER: Tải "háo hức" (eager loading). Dữ liệu liên quan tải ngay cùng entity chính.
 * SELECT * FROM SinhVien WHERE MaSV = 'SV001';
 * khi gọi getClassEntity(), mới thực hiện
 * SELECT * FROM Lop WHERE MaLop = 'LOP001';
 */
// @formatter:on