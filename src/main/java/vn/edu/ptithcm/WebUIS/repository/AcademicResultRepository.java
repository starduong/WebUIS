package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.ptithcm.WebUIS.domain.entity.AcademicResult;
import vn.edu.ptithcm.WebUIS.domain.entity.AcademicResultPK;

import java.util.List;

@Repository
public interface AcademicResultRepository extends JpaRepository<AcademicResult, AcademicResultPK> {
    @Query("SELECT ar FROM AcademicResult ar JOIN FETCH ar.student WHERE ar.id.studentId = :studentId")
    List<AcademicResult> findByIdStudentId(String studentId);

    @Query("SELECT AVG(ar.gpa) FROM AcademicResult ar WHERE ar.id.studentId = :studentId")
    Double findAverageGpaByStudentId(@Param("studentId") String studentId);
}