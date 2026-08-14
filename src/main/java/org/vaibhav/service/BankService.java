package org.vaibhav.service;

import org.vaibhav.model.Account;
import org.vaibhav.model.Bank;

import java.util.Map;

public class BankService {

    private final Bank bank;

    public BankService(Bank bank) {
        this.bank = bank;
    }

    public Account createAccount(String name, double initialAmount) throws InterruptedException {
        Account account = bank.creatAccount(name, initialAmount);

        System.out.println("Account created successfully.");
        System.out.println("Account Number : " + account.getAccountNumber());

        return account;
    }

    public void depositMoney(int accountNum, double amount) throws InterruptedException {
        Account account = bank.getAccount(accountNum)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found: " + accountNum));

        account.deposit(amount);

        System.out.println("Deposit successful.");
        System.out.println("Current Balance : " + account.getBalance());
    }

    public void withdrawMoney(int accountNum, double amount) {
        Account account = bank.getAccount(accountNum)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found: " + accountNum));

        account.withdraw(amount);

        System.out.println("Withdrawal successful.");
        System.out.println("Current Balance : " + account.getBalance());
    }

    public void transferMoney(int fromAccNum, int destinationAccNum, double amount){

        Account from = bank.getAccount(fromAccNum)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found: " + fromAccNum));

        Account to = bank.getAccount(destinationAccNum)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found: " + destinationAccNum));

        from.withdraw(amount);
        to.deposit(amount);

        System.out.println("Transfer successful.");
    }

    public void viewBalance(int accountNum) {

        Account account = bank.getAccount(accountNum)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found: " + accountNum));

        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Holder Name    : " + account.getHolderName());
        System.out.println("Balance        : " + account.getBalance());
    }

    public void listAccounts() {

        Map<Integer, Account> accounts = bank.getAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts.values()) {

            System.out.println("------------------------------");
            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Holder Name    : " + account.getHolderName());
            System.out.println("Balance        : " + account.getBalance());
        }
    }

    public void deleteAccount(int accountNum) {

        Account account = bank.getAccount(accountNum)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found: " + accountNum));

        if (account.getBalance() != 0) {
            throw new IllegalArgumentException(
                    "Account cannot be deleted unless balance is zero.");
        }

        bank.deleteAccount(accountNum);

        System.out.println("Account deleted successfully.");
    }
}