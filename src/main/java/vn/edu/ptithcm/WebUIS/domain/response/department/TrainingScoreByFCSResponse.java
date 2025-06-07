package vn.edu.ptithcm.WebUIS.domain.response.department;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScoreByFCSResponse {
    private Integer id;
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private List<DepartmentCriteriaResponse> departmentCriteriaResponses;
    private Integer totalScore;
    private TrainingScoreStatus status;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentCriteriaResponse {
        private Integer id;
        private String name;
        /**
         * score = Tổng TrainingScoreDetail.getAdvisorScore() của tất cả các
         * TrainingScoreDetail.getEvaluationContent() cùng thuộc 1 Criterion;
         */
        private Integer score;
    }
}
