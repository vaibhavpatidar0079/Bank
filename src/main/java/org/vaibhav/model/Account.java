package org.vaibhav.model;

public class Account{
    private final int accountNumber;
    private final String holderName;
    private double balance;

    public Account(int accountNumber, String holderName, double initialAmount) {
        if(initialAmount >= 0) this.balance = initialAmount;
        else{
            throw new IllegalArgumentException("amount can not be negative");
        }
        this.accountNumber = accountNumber;
        this.holderName = holderName;

    }

    public synchronized void deposit(double amount) {
        if(amount >0) this.balance += amount;
        else{throw new IllegalArgumentException("Deposit amount must be positive.");}
    }

    public synchronized void withdraw(double amount){
        if(amount > 0){
            if(this.balance < amount) throw new IllegalArgumentException("Insufficient balance.");
            this.balance -= amount;
        }
        else{throw new IllegalArgumentException("Withdraw amount cant be negative.");}
    }

    public double getBalance(){
        return this.balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public String getHolderName(){
        return this.holderName;
    }

}