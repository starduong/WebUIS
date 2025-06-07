package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.FormTemplate;

@Repository
public interface FormTemplateRepository extends JpaRepository<FormTemplate, Integer> {

}
