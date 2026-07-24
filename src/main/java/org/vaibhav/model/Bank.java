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

    public Account creatAccount(String name, double initialAmmount){
        Account a = new Account();
        a.setHolderName(name);
        a.deposit(initialAmmount);

        int accountNumber = generateAccountNumber();
        a.setAccountNumber(accountNumber);

        accounts.put(accountNumber,a);

        return a;
    }

    public Optional<Account> getAccount(int accountNumber){
        return Optional.ofNullable(accounts.get(accountNumber));
    }

}