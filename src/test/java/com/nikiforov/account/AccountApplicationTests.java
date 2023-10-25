package com.nikiforov.account;

import com.nikiforov.account.model.AccountEntity;
import com.nikiforov.account.repositories.AccountRepository;
import com.nikiforov.account.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//@SpringBootTest
class AccountApplicationTests {

	@Test
	void shouldReturnAccount() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		AccountEntity account = new AccountEntity(1L,"test account",1L);
		when( accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
		AccountService ac = new AccountService(accountRepository);
		AccountEntity result = ac.getAccount(account.getId());
		Assertions.assertEquals(result,account);
	}
	@Test
	void shouldReturnAccountByClient() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		AccountEntity account = new AccountEntity(1L,"test account",1L);
		AccountEntity account1 = new AccountEntity(2L,"test account2",1L);
		List<AccountEntity> aclist = new ArrayList<AccountEntity>();
		aclist.add(account);
		aclist.add(account1);

		when( accountRepository.findAllByIdClient(anyLong())).thenReturn(aclist);
		AccountService ac = new AccountService(accountRepository);
		List<AccountEntity> result = ac.getUserAccounts(account.getIdClient());
		Assertions.assertEquals(result,aclist);
		result = ac.getUserAccounts(0L);
		Assertions.assertEquals(result.size(),0);
	}
	@Test
	void shouldThrow404() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		AccountEntity account = new AccountEntity(1L,"test account",1L);
		when( accountRepository.findById(anyLong())).thenReturn(Optional.empty());
		AccountService ac = new AccountService(accountRepository);
		//AccountEntity result = cl.getClient(client.getId());

		Assertions.assertThrows(ResponseStatusException.class,()->ac.getAccount(account.getId()));

	}
	/*@Test
	void shouldThrowByClient404() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		when( accountRepository.findById(anyLong())).thenReturn(Optional.empty());
		AccountService ac = new AccountService(accountRepository);
		AccountEntity account = new AccountEntity(1L,"test account",1L);
		AccountEntity account1 = new AccountEntity(2L,"test account2",1L);
		List<AccountEntity> aclist = new ArrayList<AccountEntity>();
		aclist.add(account);
		aclist.add(account1);
		Assertions.assertThrows(ResponseStatusException.class,()->ac.getAccount(account.getIdClient()));

	}*/


}
