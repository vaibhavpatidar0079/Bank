package org.vaibhav.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.vaibhav.model.Account;
import org.vaibhav.model.Bank;

import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {
    Bank bank;
    BankService service;
    Account account1;
    Account account2;

    @BeforeEach
    public void setup() throws InterruptedException {
        bank = new Bank();
        service = new BankService(bank);
        account1 = bank.createAccount("vaibhav",1000);
        account2 = bank.createAccount("v",500);
    }

    //Deposit
    @Test
    void depositMoney_ShouldIncreaseBalance() {
        double initialAmount = account1.getBalance();
        service.depositMoney(account1.getAccountNumber(),100);
        assertEquals(initialAmount+100,account1.getBalance());
    }
    @Test
    void depositMoney_withInvalidAccount_shouldThrowException(){
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, ()-> service.depositMoney(1006,300)
        );
        assertEquals("Account not found: 1006",e.getMessage());
    }
    @Test
    void depositMoney_withNegativeAmount_shouldThrowException(){
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, ()-> service.depositMoney(account1.getAccountNumber(),-20)
        );
        assertEquals("Deposit amount must be positive.", e.getMessage());
    }

    //Withdrawal
    @Test
    void withdrawMoney_shouldDecreaseBalance(){
        double initialAmount = account1.getBalance();
        service.withdrawMoney(account1.getAccountNumber(),100);
        assertEquals(initialAmount-100,account1.getBalance());
    }
    @Test
    void withdrawMoney_withInsufficientBalance_shouldThrowException(){
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, () -> service.withdrawMoney( account1.getAccountNumber(),10000)
        );
        assertEquals("Insufficient balance.",e.getMessage());
    }
    @Test
    void withdrawMoney_withInvalidAccount_shouldThrowException(){
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, () -> service.withdrawMoney(74839,10)
        );
        assertEquals("Account not found: 74839",e.getMessage());
    }

}
