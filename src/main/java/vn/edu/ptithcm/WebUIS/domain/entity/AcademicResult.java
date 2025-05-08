package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KetQuaHocTap")
public class AcademicResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKQHT")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MaSV")
    @NotNull(message = "Mã sinh viên không được để trống")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "IdHK")
    @NotNull(message = "Học kỳ không được để trống")
    private Semester semester;

    @Column(name = "GPA")
    @NotNull(message = "GPA không được để trống")
    private Double gpa;

}