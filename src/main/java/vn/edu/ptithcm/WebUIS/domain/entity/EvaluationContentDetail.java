package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ChiTietNoiDungDanhGia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationContentDetail {

    @EmbeddedId
    private EvaluationContentDetailPK id;

    @ManyToOne
    @JoinColumn(name = "IdND", insertable = false, updatable = false)
    private EvaluationContent evaluationContent;

    @ManyToOne
    @JoinColumn(name = "IdCTND", insertable = false, updatable = false)
    private EvaluationContent evaluationContentDetail;

}