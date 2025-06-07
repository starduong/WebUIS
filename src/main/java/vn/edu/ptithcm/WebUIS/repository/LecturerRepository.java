package vn.edu.ptithcm.WebUIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, String> {

    Lecturer findByAccount(Account account);

    boolean existsByCitizenId(String citizenId);
}
