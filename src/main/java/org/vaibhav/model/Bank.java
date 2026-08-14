package org.vaibhav.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bank{
    private Map<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    private int generateAccountNumber() {
        return nextAccountNumber++;
    }

    public synchronized Account createAccount(String name, double initialAmount){
        int accountNumber = generateAccountNumber();

        Account a = new Account(accountNumber,name, initialAmount);
        accounts.put(accountNumber,a);

        return a;
    }

    public Optional<Account> getAccount(int accountNumber){
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public void deleteAccount(int accountNumber) {
        accounts.remove(accountNumber);
    }

    public synchronized double totalAmount(){
        double total = 0;
        for (Account account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }

}