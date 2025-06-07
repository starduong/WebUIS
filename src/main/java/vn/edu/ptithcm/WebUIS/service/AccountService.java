package vn.edu.ptithcm.WebUIS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.request.CreateAccountRequest;
import vn.edu.ptithcm.WebUIS.domain.response.CreateAccountResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.AccountRepository;
import vn.edu.ptithcm.WebUIS.repository.EmployeeRepository;
import vn.edu.ptithcm.WebUIS.repository.LecturerRepository;
import vn.edu.ptithcm.WebUIS.repository.RoleRepository;
import vn.edu.ptithcm.WebUIS.repository.StudentRepository;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository teacherRepository;
    private final EmployeeRepository employeeRepository;

    public List<CreateAccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::convertToResponseCreateAccountDTO).collect(Collectors.toList());
    }

    public Account findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account handleCreateAccount(CreateAccountRequest regCreateAccountDTO) throws IdInValidException {
        if (accountRepository.findByUsername(regCreateAccountDTO.getUsername()).isPresent()) {
            throw new IdInValidException("Username already exists");
        }
        Account account = new Account();
        account.setUsername(regCreateAccountDTO.getUsername());
        String hashPassword = passwordEncoder.encode(regCreateAccountDTO.getPassword());
        account.setPassword(hashPassword);
        account.setRole(roleRepository.findById(regCreateAccountDTO.getRoleId()).orElse(null));
        return accountRepository.save(account);
    }

    public CreateAccountResponse convertToResponseCreateAccountDTO(Account account) {
        return new CreateAccountResponse(account.getId(), account.getUsername(),
                new CreateAccountResponse.AccountRole(account.getRole().getId(), account.getRole().getName()));
    }

    public Account handleUpdateAccount(Account account) throws IdInValidException {
        Account curAccount = accountRepository.findById(account.getId()).orElse(null);
        if (curAccount == null) {
            throw new IdInValidException("Account not found");
        } else {
            if (account.getPassword() != null) {
                String hashPassword = passwordEncoder.encode(account.getPassword());
                curAccount.setPassword(hashPassword);
            }
            if (account.getUsername() != null && !account.getUsername().equals(curAccount.getUsername())) {
                if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
                    throw new IdInValidException("Username already exists");
                }
                curAccount.setUsername(account.getUsername());
            }
            if (account.getRole() != null) {
                curAccount.setRole(account.getRole());
            }
            accountRepository.save(curAccount);
        }
        return curAccount;
    }

    public void handleDeleteAccount(Integer id) throws IdInValidException {
        Account curAccount = accountRepository.findById(id).orElse(null);
        // check current login account
        if (studentRepository.findByAccount(curAccount) != null) {
            throw new IdInValidException("Account is used by a student");
        }
        if (teacherRepository.findByAccount(curAccount) != null) {
            throw new IdInValidException("Account is used by a teacher");
        }
        if (employeeRepository.findByAccount(curAccount) != null) {
            throw new IdInValidException("Account is used by a department");
        }
        accountRepository.deleteById(id);
    }

    // for login
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    // update account token in database
    public void updateAccountToken(String username, String token) {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account != null) {
            account.setRefreshToken(token);
            accountRepository.save(account);
        }
    }

    // find account by refresh token and username
    public Account findByRefreshTokenAndUsername(String refreshToken, String username) {
        return accountRepository.findByRefreshTokenAndUsername(refreshToken, username);
    }
}
