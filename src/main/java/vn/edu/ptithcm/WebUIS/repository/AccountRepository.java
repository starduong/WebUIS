package vn.edu.ptithcm.WebUIS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.ptithcm.WebUIS.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByUsername(String username); // for login

    // find account by refresh token and username
    @Query("SELECT a FROM Account a WHERE a.refreshToken = :refreshToken AND a.username = :username")
    Account findByRefreshTokenAndUsername(@Param("refreshToken") String refreshToken,
            @Param("username") String username);

    /**
     * Kiểm tra tài khoản tồn tại theo username
     */
    boolean existsByUsername(String username);

    /**
     * Đếm số lượng tài khoản theo tên quyền
     */
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role.name = :roleName")
    long countByRoleName(@Param("roleName") String roleName);
}
