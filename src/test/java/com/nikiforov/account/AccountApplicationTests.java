package com.nikiforov.account;

import com.nikiforov.account.model.AccountEntity;
import com.nikiforov.account.repositories.AccountRepository;
import com.nikiforov.account.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//@SpringBootTest
class AccountApplicationTests {

	@Test
	void shouldReturnClient() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		AccountEntity client = new AccountEntity(1l,"fioTest");
		when( accountRepository.findById(anyLong())).thenReturn(Optional.of(client));
		AccountService cl = new AccountService(accountRepository);
		AccountEntity result = cl.getClient(client.getId());

		Assertions.assertEquals(result,client);


	}
	@Test
	void shouldThrow404() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		AccountEntity client = new AccountEntity(1l,"fioTest");
		when( accountRepository.findById(anyLong())).thenReturn(Optional.empty());
		AccountService cl = new AccountService(accountRepository);
		//AccountEntity result = cl.getClient(client.getId());

		Assertions.assertThrows(ResponseStatusException.class,()->cl.getClient(client.getId()));

	}


}
