package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccountResponseDTO {
    private Long id;
    private String accountNumber;
    private float balance;
}
