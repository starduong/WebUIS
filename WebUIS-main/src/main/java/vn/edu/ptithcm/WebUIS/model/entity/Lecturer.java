package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "GiangVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {

    @Id
    @Column(name = "MaGV", length = 10, nullable = false)
    private String lecturerCode;

    @Column(name = "Ho", length = 50, nullable = false)
    private String lastName;

    @Column(name = "Ten", length = 50, nullable = false)
    private String firstName;

    @Column(name = "GioiTinh", nullable = false)
    private Boolean gender;

    @Column(name = "SoDienThoai", length = 15, nullable = false)
    private String phoneNumber;

    @Column(name = "CCCD", length = 12)
    private String nationalId;

    @Column(name = "EmailTruong", length = 100)
    private String schoolEmail;

    @Column(name = "EmailCaNhan", length = 100)
    private String personalEmail;

    @Column(name = "HocVi", length = 50)
    private String degree;

    @Column(name = "HocHam", length = 50)
    private String academicTitle;

    @Column(name = "ChuyenMon", length = 100)
    private String specialization;

    @Column(name = "HinhAnh", length = 255)
    private String profileImage;

    @Column(name = "TrangThai")
    private Boolean status;

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    private List<AcademicAdvisor> academicAdvisors;
}
