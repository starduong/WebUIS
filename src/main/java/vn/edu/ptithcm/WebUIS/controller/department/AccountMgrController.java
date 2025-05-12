package vn.edu.ptithcm.WebUIS.controller.department;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AccountService;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/department")
public class AccountMgrController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<Collection<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.handleCreateAccount(account));
    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account) throws IdInValidException {
        return ResponseEntity.ok(accountService.handleUpdateAccount(account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        accountService.handleDeleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
