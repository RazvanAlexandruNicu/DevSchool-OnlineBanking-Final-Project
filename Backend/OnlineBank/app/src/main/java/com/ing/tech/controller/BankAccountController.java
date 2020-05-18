package com.ing.tech.controller;

import com.ing.tech.model.dto.BankAccountRequestDTO;
import com.ing.tech.model.dto.BankAccountResponseDTO;
import com.ing.tech.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/bankAccount")
public class BankAccountController {
    private BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity saveBankAccount(@RequestBody BankAccountRequestDTO account) {
        BankAccountResponseDTO savedAccount = bankAccountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBankAccount(@PathVariable Long id) {
        if(bankAccountService.deleteById(id))
            return ResponseEntity.status(HttpStatus.OK).body("Bank acoount with id = "+id+ " successfully deleted");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such bank account");
    }

    @GetMapping("/{id}")
    public ResponseEntity getAccountById(@PathVariable Long id) {
        BankAccountResponseDTO response = bankAccountService.getById(id);
        return response != null ? ResponseEntity.status(HttpStatus.OK).body(response)
                                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");
    }

    @GetMapping("/all")
    public ResponseEntity getAllAccounts() {
        List<BankAccountResponseDTO> response = bankAccountService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody BankAccountRequestDTO accountLogin) {
        BankAccountResponseDTO response = bankAccountService.checkUser(accountLogin);
        System.out.println("REZULTAT = "+response);
        return response != null ? ResponseEntity.status(HttpStatus.OK).body(response)
                                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error");
    }

}
