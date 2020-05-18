package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private String sourceAccount;
    private String destinationAccount;
    private float sum;
    private long miliseconds;
}
