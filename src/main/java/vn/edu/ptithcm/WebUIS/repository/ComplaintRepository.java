package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Complaint;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    Page<Complaint> findByStudent(Student student, Pageable pageable);

}
