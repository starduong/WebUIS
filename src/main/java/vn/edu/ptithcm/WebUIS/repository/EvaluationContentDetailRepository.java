package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContentDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContentDetailPK;

import java.util.List;

@Repository
public interface EvaluationContentDetailRepository
        extends JpaRepository<EvaluationContentDetail, EvaluationContentDetailPK> {

    /**
     * Find all EvaluationContentDetail by evaluationContent id
     * 
     * @param evaluationContentId the evaluationContent id
     * @return list of EvaluationContentDetail
     */
    List<EvaluationContentDetail> findByIdEvaluationContent(Integer evaluationContentId);
}