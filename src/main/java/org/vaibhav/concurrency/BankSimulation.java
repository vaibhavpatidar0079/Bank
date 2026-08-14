package org.vaibhav.concurrency;

import org.vaibhav.service.BankService;

import java.util.Random;
import java.util.concurrent.*;

public class BankSimulation {
    private BankService service;

    public BankSimulation(BankService service){
        this.service = service;
    }

    public void run(){
        ExecutorService executor = new ThreadPoolExecutor(
                5,
                50,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(30),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        Random random = new Random();
        for(int i = 0;i<1000; i++){
            int from = random.nextInt(1001,1500);
            int to = random.nextInt(1001,1500);
            double amount = random.nextInt(1,1000);

            while(from == to){
                to = random.nextInt(1001,1501);
            }

            executor.submit(new TransferTask(service,from,to,amount));

        }
        executor.shutdown();
        try{
            while(!executor.awaitTermination(1,TimeUnit.DAYS)){}
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
