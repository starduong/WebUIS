package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "TaiKhoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTK")
    private Integer id;

    @Column(name = "TenDangNhap", length = 50)
    @NotNull(message = "Vui lòng nhập tên đăng nhập")
    private String username;

    @Column(name = "MatKhau", length = 50)
    @NotNull(message = "Vui lòng nhập mật khẩu")
    private String password;

    @Column(name = "refresh_token", length = 1024) // hoặc 2048 nếu cần
    private String refreshToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaQuyen")
    @NotNull(message = "Vui lòng chọn quyền")
    private Role role;
}
