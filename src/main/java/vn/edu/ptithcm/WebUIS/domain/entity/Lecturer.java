package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "GiangVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {
    @Id
    @Column(name = "MaGV", length = 10)
    @NotNull(message = "Mã giảng viên không được để trống")
    private String lecturerId;

    @Column(name = "Ho", nullable = false, length = 50)
    @NotNull(message = "Họ không được để trống")
    private String lastName;

    @Column(name = "Ten", nullable = false, length = 50)
    @NotNull(message = "Tên không được để trống")
    private String firstName;

    @Column(name = "GioiTinh", nullable = false)
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @Column(name = "SoDienThoai", nullable = false, length = 15)
    @NotNull(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @Column(name = "CCCD", length = 12)
    @NotNull(message = "CCCD không được để trống")
    private String citizenId;

    @Column(name = "Email", length = 100)
    @NotNull(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Column(name = "HocVi", length = 50)
    @NotNull(message = "Học vị không được để trống")
    private String academicRank;

    @Column(name = "HocHam", length = 50)
    private String academicTitle;

    @Column(name = "ChuyenMon", length = 100)
    @NotNull(message = "Chuyên môn không được để trống")
    private String specialization;

    @Column(name = "HinhAnh", length = 255)
    @NotNull(message = "Hình ảnh không được để trống")
    private String imageUrl;

    @Column(name = "TrangThai")
    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "IdTK")
    @NotNull(message = "Tài khoản không được để trống")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "MaKhoa")
    @NotNull(message = "Khoa không được để trống")
    private Department department;
}
