package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GiangVien")
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

    @Column(name = "EmailTruong", length = 100)
    @NotNull(message = "Email trường không được để trống")
    @Email(message = "Email trường không hợp lệ")
    private String schoolEmail;

    @Column(name = "EmailCaNhan", length = 100)
    @NotNull(message = "Email cá nhân không được để trống")
    @Email(message = "Email cá nhân không hợp lệ")
    private String personalEmail;

    @Column(name = "HocVi", length = 50)
    @NotNull(message = "Học vị không được để trống")
    private String academicRank;

    @Column(name = "HocHam", length = 50)
    @NotNull(message = "Học hàm không được để trống")
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
    @JoinColumn(name="MaTK")
    @NotNull(message = "Tài khoản không được để trống")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "MaKhoa")
    @NotNull(message = "Khoa không được để trống")
    private Department department;
}
