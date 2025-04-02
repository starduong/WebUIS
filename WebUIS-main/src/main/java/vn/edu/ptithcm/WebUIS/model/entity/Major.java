package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ChuyenNganh", schema = "QuanLy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {

    @Id
    @Column(name = "MaCN", length = 10, nullable = false)
    private String majorId;

    @Column(name = "TenCN", length = 100, nullable = false)
    private String majorName;

    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
    private List<Student> students;

}
