package vn.edu.ptithcm.WebUIS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, String> {
    List<ClassEntity> findByDepartment(Department department);
}
