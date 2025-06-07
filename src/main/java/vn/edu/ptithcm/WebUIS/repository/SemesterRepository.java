package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {

}
