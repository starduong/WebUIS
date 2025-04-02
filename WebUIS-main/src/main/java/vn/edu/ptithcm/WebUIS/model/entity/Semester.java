package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer semesterId;

    @Column(name = "Thu", nullable = false)
    private Integer order;

    @Column(name = "NamHoc", length = 9, nullable = false)
    private String academicYear;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private List<TrainingScore> trainingScores;
}