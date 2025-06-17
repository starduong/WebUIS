package vn.edu.ptithcm.WebUIS.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;

@Repository
public interface TrainingScoreRepository
                extends JpaRepository<TrainingScore, Integer>, JpaSpecificationExecutor<TrainingScore> {

        @Query("SELECT ts FROM TrainingScore ts JOIN FETCH ts.student JOIN FETCH ts.semester WHERE ts.student.classEntity.classId = :classId AND ts.semester.id = :semesterId")
        List<TrainingScore> findByClassIdAndSemesterId(@Param("classId") String classId,
                        @Param("semesterId") Integer semesterId);

        @Query("SELECT ts FROM TrainingScore ts JOIN FETCH ts.student JOIN FETCH ts.semester WHERE ts.student.id = :studentId")
        List<TrainingScore> findByStudentId(@Param("studentId") String studentId);

        @Query("SELECT COUNT(ts) > 0 FROM TrainingScore ts WHERE ts.student.id = :studentId AND ts.semester.id = :semesterId")
        boolean existsByStudentIdAndSemesterId(@Param("studentId") String studentId,
                        @Param("semesterId") Integer semesterId);

        @Query("SELECT ts FROM TrainingScore ts JOIN FETCH ts.student JOIN FETCH ts.semester WHERE ts.student.studentId = :studentId AND ts.semester.id = :semesterId")
        TrainingScore findByStudentIdAndSemesterId(@Param("studentId") String studentId,
                        @Param("semesterId") Integer semesterId);

        @Query("SELECT ts FROM TrainingScore ts JOIN FETCH ts.student JOIN FETCH ts.semester WHERE ts.status IN :statuses AND ts.endDate < :dateTime")
        List<TrainingScore> findAllByStatusInAndEndDateBefore(
                        @Param("statuses") List<TrainingScoreStatus> statuses,
                        @Param("dateTime") LocalDateTime dateTime);

        @Query("SELECT ts FROM TrainingScore ts JOIN FETCH ts.semester WHERE ts.semester.id = :semesterId")
        List<TrainingScore> findBySemesterId(@Param("semesterId") Integer semesterId);

}
