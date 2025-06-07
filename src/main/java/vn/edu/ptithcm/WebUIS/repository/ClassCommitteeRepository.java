package vn.edu.ptithcm.WebUIS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.ptithcm.WebUIS.domain.entity.ClassCommittee;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;

public interface ClassCommitteeRepository extends JpaRepository<ClassCommittee, Integer> {
    Optional<ClassCommittee> findByStudent(Student student);
}
