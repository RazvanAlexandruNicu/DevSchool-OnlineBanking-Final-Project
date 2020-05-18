package com.ing.tech.controller;

import com.ing.tech.model.dto.TransactionRequestDTO;
import com.ing.tech.model.dto.TransactionResponseDTO;
import com.ing.tech.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity saveTransaction(@RequestBody TransactionRequestDTO trans) {
        TransactionResponseDTO savedTransaction = transactionService.save(trans);
        return savedTransaction != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction)
                                        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id) {
        if(transactionService.deleteById(id))
            return ResponseEntity.status(HttpStatus.OK).body("Transaction with id = "+id+ " successfully deleted");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such transaction registered");
    }

    @GetMapping("/all")
    public ResponseEntity getAllTransactions() {
        List<TransactionResponseDTO> response = transactionService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity getAllTransactionsForId(@PathVariable Long id) {
        System.out.println("MISTO");
        List<TransactionResponseDTO> response = transactionService.getAllForId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
