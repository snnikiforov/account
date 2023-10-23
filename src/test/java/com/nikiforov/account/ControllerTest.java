package com.nikiforov.account;

import com.nikiforov.account.controllers.AccountController;
import com.nikiforov.account.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ControllerTest {

    @Test
    void shouldToggleAndDelete() {

        AccountService accountService = Mockito.mock(AccountService.class);

        doNothing().when(accountService).deleteClient(anyLong());
        AccountController cl = new AccountController();
        cl.setAccountService(accountService);
        cl.setFeatureToggleDeleteClient(true);
        cl.deleteClient(1l);
        verify(accountService,times(1)).deleteClient(anyLong());


    }
    @Test
    void shouldToggleAndThrow405() {

        AccountService accountService = Mockito.mock(AccountService.class);
        doNothing().when(accountService).deleteClient(anyLong());
        AccountController cl = new AccountController();
        cl.setAccountService(accountService);
        cl.setFeatureToggleDeleteClient(false);
        Assertions.assertThrows(ResponseStatusException.class,()->cl.deleteClient(1l));
        verify(accountService,times(0)).deleteClient(anyLong());
    }
}
