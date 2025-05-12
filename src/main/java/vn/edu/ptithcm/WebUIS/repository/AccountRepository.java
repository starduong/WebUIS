package vn.edu.ptithcm.WebUIS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Integer>{
    Optional<Account> findByUsername(String username); //for login
}
