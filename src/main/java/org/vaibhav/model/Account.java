package org.vaibhav.model;

public class Account{
    private int accountNumber;
    private String holderName;
    private double balance;

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

    public void setAccountNumber(int number) {
        if (this.accountNumber != 0) {
            throw new IllegalStateException("Account number is already assigned.");
        }

        this.accountNumber = number;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public void setHolderName(String name){
        this.holderName = name;
    }
    public String getHolderName(){
        return this.holderName;
    }

}