package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    // SELECT * FROM SinhVien WHERE MaSV = :studentId
    Student findByStudentId(String id);
}
