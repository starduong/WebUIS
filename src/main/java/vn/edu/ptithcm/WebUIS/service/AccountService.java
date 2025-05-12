package vn.edu.ptithcm.WebUIS.service;

import java.util.Collection;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;


    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account handleCreateAccount(Account account){
        String hashPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(hashPassword);
       return accountRepository.save(account);
    }

    public Account handleUpdateAccount(Account account) throws IdInValidException{
        Account curAccount = accountRepository.findById(account.getId()).orElse(null);
        if (curAccount == null){
            throw new IdInValidException("Id is invalid");
        } else {
            String hashPassword = passwordEncoder.encode(account.getPassword());
            curAccount.setUsername(account.getUsername());
            curAccount.setPassword(hashPassword);
            curAccount.setRole(account.getRole());
            accountRepository.save(curAccount);
        }
        return curAccount;
    }

    public void handleDeleteAccount(Integer id){
        accountRepository.deleteById(id);
    }

    //for login
    public Account findByUsername(String username){
        return accountRepository.findByUsername(username).orElse(null);
    }
}
