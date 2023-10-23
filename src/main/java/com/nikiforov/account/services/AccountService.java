package com.nikiforov.account.services;

import com.nikiforov.account.dto.Account;
import com.nikiforov.account.model.AccountEntity;
import com.nikiforov.account.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Счет не найден с таким айди"));
    }
    public List<AccountEntity> getUserAccounts(Long idClient) {
        return accountRepository.findAllByIdClient(idClient);
    }

    public long createAccount(Account account) {
       return accountRepository.save(new AccountEntity(null, account.getAccountNumber(),account.getIdClient())).getId();

    }

    public void updateAccount(Account account) {
        AccountEntity ac = getAccount(account.getId());
        ac.setAccountNumber(account.getAccountNumber());
        ac.setIdClient(account.getIdClient());
        accountRepository.save(ac);
    }


    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);

    }
}
