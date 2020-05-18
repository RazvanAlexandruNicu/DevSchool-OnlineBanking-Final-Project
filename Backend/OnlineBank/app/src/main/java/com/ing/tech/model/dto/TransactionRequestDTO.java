package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDTO {
    private String sourceAccount;
    private String destinationAccount;
    private float sum;
    private long miliseconds;
}
