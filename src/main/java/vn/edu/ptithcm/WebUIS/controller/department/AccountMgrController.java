package vn.edu.ptithcm.WebUIS.controller.department;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.request.CreateAccountRequest;
import vn.edu.ptithcm.WebUIS.domain.response.CreateAccountResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AccountService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/department/accounts")
@Secured("EMPLOYEE_DEPARTMENT")
public class AccountMgrController {
    private final AccountService accountService;

    @GetMapping
    @ApiMessage("Get all accounts")
    public ResponseEntity<List<CreateAccountResponse>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @PostMapping
    @ApiMessage("Create a new account")
    public ResponseEntity<CreateAccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest regCreateAccountDTO)
            throws IdInValidException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                accountService
                        .convertToResponseCreateAccountDTO(accountService.handleCreateAccount(regCreateAccountDTO)));
    }

    @PutMapping
    @ApiMessage("Update an account")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account) throws IdInValidException {
        return ResponseEntity.ok(accountService.handleUpdateAccount(account));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete an account")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) throws IdInValidException {
        accountService.handleDeleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
