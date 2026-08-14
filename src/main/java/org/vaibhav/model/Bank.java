package org.vaibhav.model;

import java.util.*;

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

    public synchronized Optional<Account> getAccount(int accountNumber){
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public synchronized Map<Integer, Account> getAccounts() {
        return new HashMap<>(accounts);
    }

    public synchronized void deleteAccount(Account account){
        //lock order rule: bank -> account
        account.lock();
        try {
            if (account.getBalance() != 0.0) {
                throw new IllegalStateException(
                        "Account cannot be deleted unless balance is zero.");
            }
            accounts.remove(account.getAccountNumber());
        } finally {
            account.unlock();
        }


    }

}