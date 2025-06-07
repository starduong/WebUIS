package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ChiTietDiemRenLuyen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScoreDetail {

    @EmbeddedId
    private TrainingScoreDetailPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("trainingScoreId")
    @JoinColumn(name = "IdDRL")
    private TrainingScore trainingScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("evaluationContentId")
    @JoinColumn(name = "IdND")
    private EvaluationContent evaluationContent;

    @Column(name = "DiemSV")
    private Integer studentScore;

    @Column(name = "DiemBCS")
    private Integer classCommitteeScore;

    @Column(name = "DiemCVHT")
    private Integer advisorScore;
}