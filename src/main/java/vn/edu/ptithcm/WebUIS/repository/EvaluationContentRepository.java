package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;

@Repository
public interface EvaluationContentRepository extends JpaRepository<EvaluationContent, Integer> {
    List<EvaluationContent> findByCriterionId(Integer criterionId);
}
