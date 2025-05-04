package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScoreDetailPK implements Serializable {

    @Column(name = "IdDRL", nullable = false)
    private Integer trainingScoreId;

    @Column(name = "IdND", nullable = false)
    private Integer evaluationContentId;
}