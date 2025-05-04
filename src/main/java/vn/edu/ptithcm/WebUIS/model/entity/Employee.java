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
@Table(name = "NhanVien")
public class Employee {

    @Id
    @Column(name = "MaNV", length = 10)
    @NotNull(message = "Mã nhân viên không được để trống")
    private String id;

    @Column(name = "Ho", length = 50, nullable = false)
    @NotNull(message = "Họ không được để trống")
    private String lastName;

    @Column(name = "Ten", length = 50, nullable = false)
    @NotNull(message = "Tên không được để trống")
    private String firstName;

    @Column(name = "GioiTinh", nullable = false)
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @Column(name = "SoDT", length = 15)
    @NotNull(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @Column(name = "CCCD", length = 12, nullable = false)
    @NotNull(message = "Số CCCD không được để trống")
    private String citizenId;

    @Column(name = "Email", length = 100)
    @NotNull(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Column(name = "HinhAnh", length = 255)
    @NotNull(message = "Hình ảnh không được để trống")
    private String imageUrl;

    @Column(name = "TrangThai", nullable = false)
    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="MaTK")
    private Account account;

    @ManyToOne
    @JoinColumn(name="MaPBK")
    @NotNull(message = "Phòng ban không được để trống")
    private Department department;
}