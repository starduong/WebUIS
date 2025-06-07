package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NoiDungDanhGia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdND")
    private Integer id;

    @Column(name = "NoiDung")
    private String content;

    @Column(name = "DiemToiDa")
    private Integer maxScore;

    @ManyToOne
    @JoinColumn(name = "IdTC")
    private Criterion criterion;
}