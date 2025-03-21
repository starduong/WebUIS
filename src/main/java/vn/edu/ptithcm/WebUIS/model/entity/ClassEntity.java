package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Lop", schema = "QuanLy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {

    @Id
    @Column(name = "MaLop", length = 10, nullable = false)
    private String classId;

    @Column(name = "NganhDaoTao", length = 100, nullable = false)
    private String trainingField;

    @Column(name = "HeDaoTao", length = 50, nullable = false)
    private String trainingSystem;

    @Column(name = "NienKhoa", length = 9, nullable = false)
    private String academicYear;

    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhoa", nullable = false)
    private Faculty faculty;

}
