package vn.edu.ptithcm.WebUIS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    @Query("SELECT s FROM Semester s ORDER BY s.academicYear DESC, s.order DESC")
    List<Semester> findAllSemester();
}
