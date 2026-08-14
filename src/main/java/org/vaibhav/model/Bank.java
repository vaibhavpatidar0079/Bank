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

    public synchronized void deleteAccount(int accountNumber) {
        accounts.remove(accountNumber);
    }

    public synchronized double totalAmount(){
        Map<Integer, Account> accounts = getAccounts();
        List<Integer> sortedAccountsNum = new ArrayList<>(accounts.keySet());

        sortedAccountsNum.sort(Comparator.naturalOrder());
        for(int i: sortedAccountsNum){
            accounts.get(i).lock();
        }
        try {
            double total = 0;
            for (int i : sortedAccountsNum) {
                total += accounts.get(i).getBalance();
            }
            return total;

        }finally {
            for (int i : sortedAccountsNum) {
                accounts.get(i).unlock();
            }
        }

    }

}