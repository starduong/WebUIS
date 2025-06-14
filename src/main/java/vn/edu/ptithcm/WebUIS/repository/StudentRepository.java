package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    // SELECT * FROM SinhVien WHERE MaSV = :studentId
    Student findByStudentId(String id);

    Student findByAccount(Account account);

    boolean existsByCitizenId(String citizenId);

    @Query("SELECT s FROM Student s WHERE s.universityEmail = :email")
    Student findByUniversityEmail(String email);

}
