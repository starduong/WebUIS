package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.AcademicAdvisor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;

@Repository
public interface AcademicAdvisorRepository extends JpaRepository<AcademicAdvisor, Integer> {
    AcademicAdvisor findByLecturerAndClassEntity(Lecturer lecturer, ClassEntity classEntity);

    AcademicAdvisor findByLecturer(Lecturer lecturer);
}