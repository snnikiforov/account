package com.nikiforov.account.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class Account {
    private Long id;
    private String accountNumber;
    private Long idClient;

}
