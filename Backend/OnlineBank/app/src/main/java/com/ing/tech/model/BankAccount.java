package com.ing.tech.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString //@EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;
    private String pin;
    private float balance;

    public BankAccount(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.f;
    }

    public BankAccount(String accountNumber, String pin, float balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public void withdraw(float sum) {
        this.balance -= sum;
    }

    public void credit(float sum) {
        this.balance += sum;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", password='" + pin + '\'' +
                '}';
    }
}
