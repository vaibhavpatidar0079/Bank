package org.vaibhav.model;

public class Account{
    private int accountNumber;
    private String holderName;
    private double balance;

    public void deposit(int amount){
        if(amount >0) this.balance += amount;
        else{throw new IllegalArgumentException();}
    }

    public void withdraw(int amount){
        if(amount >0) this.balance += amount;
        else{throw new IllegalArgumentException();}
    }

}