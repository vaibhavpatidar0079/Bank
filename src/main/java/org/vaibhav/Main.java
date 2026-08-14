package org.vaibhav;

import org.vaibhav.model.Bank;
import org.vaibhav.service.BankService;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        BankService service = new BankService(bank);

        for(int i =1; i<=10; i++){
            service.createAccount("name"+i,1000);
        }

        Thread t1 = new Thread(() -> {
            try {
                service.transferMoney(1001, 1005, 100);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                service.transferMoney(1005, 1002, 100);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                service.transferMoney(1001, 1008, 100);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                service.transferMoney(1008, 1001, 100);
            } catch (IllegalArgumentException  e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println("total: " + bank.totalAmount());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println("total: " + bank.totalAmount());
        t1.join();
        t2.join();
        t3.join();
        t4. join();
        System.out.println("total: " + bank.totalAmount());



    }
}