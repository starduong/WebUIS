package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietDiemRenLuyen")
public class TrainingScoreDetail {

    @EmbeddedId
    private TrainingScoreDetailPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("trainingScoreId") 
    @JoinColumn(name = "IdDRL", insertable = false, updatable = false)
    private TrainingScore trainingScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("evaluationContentId")
    @JoinColumn(name = "IdND", insertable = false, updatable = false)
    private EvaluationContent evaluationContent;

    @Column(name = "DiemSV")
    private Integer studentScore;

    @Column(name = "DiemBCS")
    private Integer classCommitteeScore;

    @Column(name = "DiemCVHT")
    private Integer advisorScore;
}