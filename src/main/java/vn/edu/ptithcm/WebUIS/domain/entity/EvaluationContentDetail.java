package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietNoiDungDanhGia")
public class EvaluationContentDetail {

    @ManyToOne
    @JoinColumn(name = "IdND")
    private EvaluationContent evaluationContent;

    @ManyToOne
    @JoinColumn(name = "IdCTND")
    private EvaluationContent evaluationContentDetail;

}