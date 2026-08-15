package org.vaibhav.concurrency;

import org.vaibhav.service.BankService;

import java.util.concurrent.Callable;

public class TransferTask implements Callable<Boolean> {
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
    public Boolean call() throws Exception {
        try {
            return service.transferMoney(from, to, amount);
        } catch (Exception e) {
            throw new Exception(
                    "Transfer failed from " + from + " to " + to +
                            " for amount " + amount,
                    e
            );
        }
    }
}
