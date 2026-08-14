package org.vaibhav.concurrency;

import org.vaibhav.model.Bank;
import org.vaibhav.service.BankService;

public class TransferTask implements Runnable {
    private final BankService service;
    private final int from;
    private final int to;
    private final double amount;

    public TransferTask(BankService service, int from, int to, double amount){
        this.service = service;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
    @Override
    public void run(){
        service.transferMoney(from,to,amount);
    }
}
