package com.ing.tech.service;

import com.ing.tech.model.BankAccount;
import com.ing.tech.model.dto.BankAccountRequestDTO;
import com.ing.tech.model.dto.BankAccountResponseDTO;
import com.ing.tech.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BankAccountService {

    private BankAccountRepository bankAccountRepository;

    public BankAccountResponseDTO save(BankAccountRequestDTO account) {
        if(this.checkUser(account) != null) {
            BankAccount x = bankAccountRepository.getBankAccountByAccountNumber(account.getAccountNumber());
            return new BankAccountResponseDTO(x.getId(), x.getAccountNumber(), x.getBalance());
        }
        BankAccount bankAccount = bankAccountRepository.save(
                new BankAccount(account.getAccountNumber(), account.getPin(), account.getBalance()));
        return  new BankAccountResponseDTO(bankAccount.getId(), bankAccount.getAccountNumber(), bankAccount.getBalance());
    }

    public boolean deleteById(Long id) {
        if(!bankAccountRepository.findById(id).isPresent())
            return false;
        bankAccountRepository.deleteById(id);
        return true;
    }

    public List<BankAccountResponseDTO> getAll() {
        System.out.println("get all!!!====");
        List<BankAccountResponseDTO> result = new ArrayList<>();
        bankAccountRepository.findAll().forEach(x->result.add(new BankAccountResponseDTO(x.getId(), x.getAccountNumber(), x.getBalance())));
        return result;
    }

    public BankAccountResponseDTO checkUser(BankAccountRequestDTO logAccount) {
        System.out.println("CHECK "+logAccount);
        BankAccount account = bankAccountRepository.getBankAccountByAccountNumber(logAccount.getAccountNumber());
        System.out.println(account);
        if(account != null && account.getPin().compareTo(logAccount.getPin()) == 0)
            return new BankAccountResponseDTO(account.getId(), account.getAccountNumber(), account.getBalance());
        return null;
    }

    public BankAccountResponseDTO getById(Long id) {
        BankAccount res = bankAccountRepository.findById(id).orElse(null);
        if(res == null)
            return null;
        return new BankAccountResponseDTO(res.getId(), res.getAccountNumber(), res.getBalance());
    }
}
