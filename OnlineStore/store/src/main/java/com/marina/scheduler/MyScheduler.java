package com.marina.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyScheduler {
    public void execute(Runnable runnable, int period){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(runnable, period, TimeUnit.MINUTES);
        scheduledExecutorService.shutdown();
    }
}
