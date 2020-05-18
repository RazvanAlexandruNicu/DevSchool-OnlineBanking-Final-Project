package com.ing.tech.service;

import com.ing.tech.model.BankAccount;
import com.ing.tech.model.Transaction;
import com.ing.tech.model.dto.TransactionRequestDTO;
import com.ing.tech.model.dto.TransactionResponseDTO;
import com.ing.tech.repository.BankAccountRepository;
import com.ing.tech.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {

    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    public TransactionResponseDTO save(TransactionRequestDTO trans) {
        String src = trans.getSourceAccount();
        String dst = trans.getDestinationAccount();
        BankAccount source = bankAccountRepository.getBankAccountByAccountNumber(src);
        BankAccount destination = bankAccountRepository.getBankAccountByAccountNumber(dst);
        if(source != null && destination != null) {
            System.out.println("2 conturi BUNE");
            if (source.equals(destination)) {
                return null;
            }
            if(source.getBalance() < trans.getSum()) {
                return null;
            }
            source.withdraw(trans.getSum());
            destination.credit(trans.getSum());
            bankAccountRepository.save(source);
            bankAccountRepository.save(destination);
            Transaction solved = transactionRepository.save(new Transaction(
                    trans.getSourceAccount(), trans.getDestinationAccount(), trans.getSum(), trans.getMiliseconds()
            ));
            return new TransactionResponseDTO(solved.getId(), solved.getSourceAccount(), solved.getDestinationAccount(), solved.getSum(),
                    solved.getMiliseconds());
        }
        System.out.println("UNUL E NULL");
        return null;
    }

    public boolean deleteById(Long id) {
        if(!transactionRepository.findById(id).isPresent())
            return false;
        transactionRepository.deleteById(id);
        return true;
    }

    public List<TransactionResponseDTO> getAll() {
        System.out.println("get all transactions!!!====");
        List<TransactionResponseDTO> result = new ArrayList<>();
        transactionRepository.findAll().forEach(x->result.add(new TransactionResponseDTO(x.getId(), x.getSourceAccount(),
                                                                x.getDestinationAccount(), x.getSum(), x.getMiliseconds())));
        return result;
    }

    public List<TransactionResponseDTO> getAllForId(Long id) {
        List<TransactionResponseDTO> aux = new ArrayList<>();
        List<TransactionResponseDTO> result = new ArrayList<>();

        BankAccount gotEl = bankAccountRepository.findById(id).orElse(null);
        if(gotEl == null)
            return result;
        for(Transaction x : transactionRepository.findAll()) {
            if(x.getDestinationAccount().equals(gotEl.getAccountNumber())
               || x.getSourceAccount().equals(gotEl.getAccountNumber()))
                result.add(new TransactionResponseDTO(x.getId(), x.getSourceAccount(),
                        x.getDestinationAccount(), x.getSum(), x.getMiliseconds()));
        }
        return result;
    }
}
