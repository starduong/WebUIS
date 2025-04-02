package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ChamNoiDung", schema = "HocTap")
@IdClass(ContentAssessmentId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentAssessment {

    @Id
    @Column(name = "IdCTC", nullable = false)
    private Integer criterionAssessmentId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdND", nullable = false)
    private EvaluationContent evaluationContent;

    @Column(name = "DiemSV")
    private Integer studentScore;

    @Column(name = "DiemBCS")
    private Integer classCommitteeScore;

    @Column(name = "DiemCVHT")
    private Integer advisorScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCTC", nullable = false, insertable = false, updatable = false)
    private CriterionAssessment criterionAssessment;
}