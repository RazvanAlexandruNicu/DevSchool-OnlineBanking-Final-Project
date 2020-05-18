package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccountRequestDTO {
    private String accountNumber;
    private String pin;
    private float balance;
}
