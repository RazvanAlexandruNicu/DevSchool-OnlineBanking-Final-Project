package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString //@EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sourceAccount;
    private String destinationAccount;
    private float sum;
    private long miliseconds;
    // The sum will be transferred from sourceAccount to destinationAccount


    public Transaction(String sourceAccount, String destinationAccount, float sum, long miliseconds) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.sum = sum;
        this.miliseconds = miliseconds;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sourceAccount='" + sourceAccount + '\'' +
                ", destinationAccount='" + destinationAccount + '\'' +
                ", sum=" + sum +
                ", miliseconds=" + miliseconds+
                '}';
    }
}
