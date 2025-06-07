package vn.edu.ptithcm.WebUIS.domain.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "HocKy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "semester")
    private List<TrainingScore> trainingScores;

    @JsonIgnore
    @OneToMany(mappedBy = "semester")
    private List<AcademicResult> academicResults;
}