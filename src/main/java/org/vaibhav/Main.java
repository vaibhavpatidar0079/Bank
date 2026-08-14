package org.vaibhav;

import org.vaibhav.concurrency.BankSimulation;
import org.vaibhav.concurrency.TransferTask;
import org.vaibhav.model.Bank;
import org.vaibhav.service.BankService;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        BankService service = new BankService(bank);

        for(int i = 0; i<500; i++){
            service.createAccount("name" + i+1, 100);
        }
        double totalInitial = service.totalAmount();
        BankSimulation s = new BankSimulation(service);
        s.run();
        double totalFinal = service.totalAmount();

        if(totalInitial == totalFinal){
            System.out.println("Total initial amount is equal to final: " + totalFinal);
        }else{
            System.out.println("fucked");
            System.out.printf("initial amount: "+ totalInitial +", final: "+ totalFinal);
        }
    }
}