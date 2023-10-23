package com.nikiforov.account.controllers;

import com.nikiforov.account.dto.Account;
import com.nikiforov.account.model.AccountEntity;
import com.nikiforov.account.services.AccountService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/account")
@Data
@NoArgsConstructor
public class AccountController {
    @Value("${feature.toggle.delete.account:false}")
    private boolean featureToggleDeleteAccount;

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public AccountEntity getAccount(@PathVariable long id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/search-by-user/{id}")
    public List<AccountEntity> getUserAccount(@PathVariable long id) {
        return accountService.getUserAccounts(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long id) {

        if (this.featureToggleDeleteAccount)
            accountService.deleteAccount(id);
        else
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Операция отключена администратором");
    }
}