package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Quyen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permissions {

    @Id
    @Column(name = "MaQuyen", nullable = false)
    private Integer roleId;

    @Column(name = "TenQuyen", length = 50)
    private String roleName;
}
