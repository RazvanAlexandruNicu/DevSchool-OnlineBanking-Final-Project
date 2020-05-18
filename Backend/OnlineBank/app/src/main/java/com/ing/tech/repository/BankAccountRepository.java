package com.ing.tech.repository;

import com.ing.tech.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    BankAccount getBankAccountByAccountNumber(String accountNumber);
}
