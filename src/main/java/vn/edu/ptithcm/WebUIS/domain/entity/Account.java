package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TaiKhoan")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaQuyen")
    @NotNull(message = "Vui lòng chọn quyền")
    private Role role;

    // @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    // private Student student;

    // @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    // private Lecturer lecturer;

    // @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    // private Employee employee;
}
