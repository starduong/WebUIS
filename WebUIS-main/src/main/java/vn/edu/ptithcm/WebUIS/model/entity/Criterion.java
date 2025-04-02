package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TieuChi", schema = "HocTap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTC", nullable = false)
    private Integer criterionId;

    @Column(name = "TenTC", length = 100, nullable = false)
    private String criterionName;

    @Column(name = "NoiDung", length = 500)
    private String content;

    @Column(name = "DiemQuyDinh", nullable = false)
    private Integer regulatedScore;

    @OneToMany(mappedBy = "criterion", fetch = FetchType.LAZY)
    private List<CriterionAssessment> criterionAssessments;
}