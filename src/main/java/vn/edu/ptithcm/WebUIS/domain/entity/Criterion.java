package vn.edu.ptithcm.WebUIS.domain.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TieuChi")
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTC", nullable = false)
    private Integer id;

    @Column(name = "TenTC", length = 500, nullable = false)
    private String name;

    @OneToMany(mappedBy = "criterion", fetch = FetchType.LAZY)
    private List<EvaluationContent> evaluationContents;
}