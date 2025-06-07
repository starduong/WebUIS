package vn.edu.ptithcm.WebUIS.domain.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Lop")
public class ClassEntity {
    @Id
    @Column(name = "MaLop", unique = true, nullable = false, length = 10)
    @NotNull(message = "Mã lớp không được để trống")
    private String classId;

    @Column(name = "NganhDaoTao", nullable = false)
    @NotNull(message = "Ngành đào tạo không được để trống")
    private String major;

    @Column(name = "HeDaoTao", nullable = false)
    @NotNull(message = "Hệ đào tạo không được để trống")
    private String educationLevel;

    @Column(name = "NienKhoa", nullable = false)
    @NotNull(message = "Niên khóa không được để trống")
    private String academicYear;

    @ManyToOne
    @JoinColumn(name = "MaKhoa")
    @NotNull(message = "Mã khoa không được để trống")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "classEntity")
    private List<Student> students;

}
