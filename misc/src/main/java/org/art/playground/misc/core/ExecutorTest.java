package org.art.playground.misc.core;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 5; i++) {
            service.scheduleAtFixedRate(() -> {
                System.out.println(LocalDateTime.now() + ". STARED. Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(30_000);
                } catch (InterruptedException e) {
                    System.out.println("Exception: " + e);
//                    throw new RuntimeException(e);
                }
                System.out.println(LocalDateTime.now() + ". COMPLETED. Thread: " + Thread.currentThread().getName());
            }, 10, 10, TimeUnit.SECONDS);
        }

//        Thread.sleep(20_000);
        Thread.sleep(5_000);

        System.out.println(LocalDateTime.now() + ". MAIN BEFORE termination: " + Thread.currentThread().getName());

        service.shutdown();

        service.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(LocalDateTime.now() + ". MAIN AFTER termination: " + Thread.currentThread().getName());
    }
}
