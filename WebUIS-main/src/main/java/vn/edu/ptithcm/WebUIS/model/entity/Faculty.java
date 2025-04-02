package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Khoa", schema = "QuanLy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {

    @Id
    @Column(name = "MaKhoa", length = 10, nullable = false)
    private String facultyId;

    @Column(name = "TenKhoa", length = 100, nullable = false)
    private String facultyName;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<ClassEntity> classEntitys;

}
