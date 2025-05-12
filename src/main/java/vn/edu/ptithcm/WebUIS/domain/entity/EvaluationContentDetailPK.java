package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationContentDetailPK implements Serializable {

    @Column(name = "IdND", nullable = false)
    private Integer evaluationContent;

    @Column(name = "IdCTND", nullable = false)
    private Integer evaluationContentDetail;
}
