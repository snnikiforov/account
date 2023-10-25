package com.nikiforov.account;

import com.nikiforov.account.controllers.AccountController;
import com.nikiforov.account.dto.Account;
import com.nikiforov.account.services.AccountService;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Test
    void shouldToggleAndDelete() {

        AccountService accountService = Mockito.mock(AccountService.class);

        doNothing().when(accountService).deleteAccount(anyLong());
        AccountController cl = new AccountController();
        cl.setAccountService(accountService);
        cl.setFeatureToggleDeleteAccount(true);
        cl.deleteAccount(1l);
        verify(accountService,times(1)).deleteAccount(anyLong());
    }
    @Test
    void shouldToggleAndThrow405() {

        AccountService accountService = Mockito.mock(AccountService.class);
        doNothing().when(accountService).deleteAccount(anyLong());
        AccountController cl = new AccountController();
        cl.setAccountService(accountService);
        cl.setFeatureToggleDeleteAccount(false);
        Assertions.assertThrows(ResponseStatusException.class,()->cl.deleteAccount(1l));
        verify(accountService,times(0)).deleteAccount(anyLong());
    }
    @Test
    void shouldCreate() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Account account = new Account(1l,"test number",1l);
        when(accountService.createAccount(any(Account.class))).thenReturn(account.getId());
        //doNothing().when(accountService).createAccount(any(Account.class));
        AccountController ac = new AccountController();
        ac.setAccountService(accountService);
        Long result = ac.createAccount(account);
        Assertions.assertEquals(result,account.getId());
        //verify(accountService,times(1)).createAccount(any(Account.class));
    }
    @Test
    void shouldUpdate() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Account account = new Account(1l,"test number",1l);
        //when(accountService.updateAccount(any(Account.class));).thenReturn(account.getId());
        doNothing().when(accountService).updateAccount(any(Account.class));
        AccountController ac = new AccountController();
        ac.setAccountService(accountService);
        ac.updateAccount(account);
        verify(accountService,times(1)).updateAccount(any(Account.class));
    }
}
