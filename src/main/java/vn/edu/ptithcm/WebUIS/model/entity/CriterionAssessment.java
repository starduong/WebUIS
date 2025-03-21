package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ChamTieuChi", schema = "HocTap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriterionAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCTC", nullable = false)
    private Integer criterionAssessmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCTDRL")
    private TrainingScoreDetail trainingScoreDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTC")
    private Criterion criterion;

    @Column(name = "DiemSV")
    private Integer studentScore;

    @Column(name = "DiemBCS")
    private Integer classCommitteeScore;

    @Column(name = "DiemCVHT")
    private Integer advisorScore;
}