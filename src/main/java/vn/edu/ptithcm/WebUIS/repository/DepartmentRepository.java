package vn.edu.ptithcm.WebUIS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findByType(Boolean type);
}
