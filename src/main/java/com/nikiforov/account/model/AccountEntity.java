package com.nikiforov.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DialectOverride;

@Entity
@Table(name="account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @SequenceGenerator(name= "test",sequenceName = "account_id_seq",allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "test")
    private Long id;
    private String accountNumber;
    private Long idClient;
}
