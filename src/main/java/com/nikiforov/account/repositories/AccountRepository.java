package com.nikiforov.account.repositories;

import com.nikiforov.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

    List<AccountEntity> findAllByIdClient(Long idClient);

}
