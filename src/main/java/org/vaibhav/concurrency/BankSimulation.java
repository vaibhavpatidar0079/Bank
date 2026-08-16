package org.vaibhav.concurrency;

import org.vaibhav.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BankSimulation {
    private final BankService service;
    private final List<Future<Boolean>> futures = new ArrayList<>();
    private int submitted;
    private int success;
    private int failed;
    private ExecutorService executor;

    public BankSimulation(BankService service) {
        this.service = service;
    }
    public boolean isFinished(){
        return futures.stream().allMatch(Future::isDone);
    }
    public void awaitCompletion() throws InterruptedException {
        if(executor == null) throw new IllegalStateException("Simulation has not been started.");
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    public void calculateResults() {
        submitted = futures.size();
        success = 0;
        failed = 0;

        for(Future<Boolean> f: futures){
            try {
                if (Boolean.TRUE.equals(f.get())) success++;
            }catch(Exception e){
                failed++;
                System.out.println(e.getMessage());
            }
        }
    }
    public int getSubmitted(){return submitted;}
    public int getSuccess(){return success;}
    public int getFailed(){return failed;}

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
            int from = random.nextInt(1001,1501);
            int to = random.nextInt(1001,1501);
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
