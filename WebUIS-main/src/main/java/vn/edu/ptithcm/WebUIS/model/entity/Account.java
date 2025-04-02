package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TaiKhoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "IdTK", nullable = false)
    private Integer accountId;

    @Column(name = "MaNguoiDung", length = 50, nullable = false)
    private String username;

    @Column(name = "MatKhau", length = 50, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "MaQuyen", referencedColumnName = "MaQuyen", nullable = false)
    private Permissions role;
}
