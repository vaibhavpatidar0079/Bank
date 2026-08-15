package org.vaibhav.concurrency;

import org.vaibhav.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BankSimulation {
    private BankService service;
    private List<Future<Boolean>> futures = new ArrayList<>();
    ExecutorService executor;

    public BankSimulation(BankService service) {
        this.service = service;
    }
    public boolean isFinished(){
        return futures.stream().allMatch(Future::isDone);
    }
    public void awaitCompletion() throws InterruptedException {
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    public void start(){
        executor = new ThreadPoolExecutor(
                5,
                50,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()
        );

        Random random = new Random();
        for(int i = 0;i<1000; i++){
            int from = random.nextInt(1001,1500);
            int to = random.nextInt(1001,1500);
            double amount = random.nextInt(1,100);

            while(from == to){
                to = random.nextInt(1001,1501);
            }
            Future<Boolean> f = executor.submit(new TransferTask(service,from,to,amount));
            futures.add(f);
        }
        executor.shutdown();
    }
}
