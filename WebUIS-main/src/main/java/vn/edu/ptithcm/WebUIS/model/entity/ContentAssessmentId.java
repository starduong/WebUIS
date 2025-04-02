package vn.edu.ptithcm.WebUIS.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentAssessmentId implements Serializable {
    private Integer criterionAssessmentId; // Tương ứng với IdCTC
    private Integer evaluationContent; // Tương ứng với IdND (khóa chính của EvaluationContent)
}