package vn.edu.ptithcm.WebUIS.domain.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HocKy")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHK", nullable = false)
    private Integer id;

    @Column(name = "Thu", nullable = false)
    @NotNull(message = "Thứ không được để trống")
    private Integer order;

    @Column(name = "NamHoc", length = 9, nullable = false)
    @NotNull(message = "Năm học không được để trống")
    private String academicYear;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private List<TrainingScore> trainingScores;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private List<AcademicResult> academicResults;
}