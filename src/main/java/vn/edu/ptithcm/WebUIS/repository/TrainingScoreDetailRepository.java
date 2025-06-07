package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetailPK;

@Repository
public interface TrainingScoreDetailRepository extends JpaRepository<TrainingScoreDetail, TrainingScoreDetailPK> {
    TrainingScoreDetail findByTrainingScoreIdAndEvaluationContentId(Integer trainingScoreId,
            Integer evaluationContentId);
}
