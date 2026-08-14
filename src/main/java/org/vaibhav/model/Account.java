package org.vaibhav.model;

import java.util.concurrent.locks.ReentrantLock;

public class Account{
    private final int accountNumber;
    private final String holderName;
    private double balance;

    private final ReentrantLock lock = new ReentrantLock();
    public void lock(){
        lock.lock();
    }
    public void unlock(){
        lock.unlock();
    }

    public Account(int accountNumber, String holderName, double initialAmount) {
        if(initialAmount < 0) throw new IllegalArgumentException("amount can not be negative");

        this.balance = initialAmount;
        this.accountNumber = accountNumber;
        this.holderName = holderName;

    }

    public void deposit(double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");

        lock();
        try{
            this.balance += amount;

        }finally {
            unlock();
        }
    }

    public void withdraw(double amount){
        if(amount <= 0) throw new IllegalArgumentException("Withdraw amount must be positive.");

        lock();
        try {
            if (this.balance < amount) throw new IllegalArgumentException("Insufficient balance.");
            this.balance -= amount;
        }finally {
            unlock();
        }
    }

    public double getBalance(){
        lock();
        double balance;
        try {
            balance = this.balance;
        }finally {
            unlock();
        }
        return balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public String getHolderName(){
        return this.holderName;
    }

}