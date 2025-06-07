package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PhongBanKhoa")
public class Department {
    @Id
    @Column(name = "MaPBK", length = 10)
    @NotNull(message = "Mã khoa không được để trống")
    private String departmentId;

    @Column(name = "TenPBK", nullable = false, length = 100)
    @NotNull(message = "Tên khoa không được để trống")
    private String departmentName;

    @Column(name = "Loai", nullable = false)
    @NotNull(message = "Loại khoa không được để trống")
    private Boolean type;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<ClassEntity> classes;

}
