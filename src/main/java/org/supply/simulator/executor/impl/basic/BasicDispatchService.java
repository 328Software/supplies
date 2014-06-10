package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.*;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Brandon on 6/9/2014.
 */
public class BasicDispatchService extends BasicTask implements DispatchService, Task {

    ScheduledThreadPoolExecutor executor;
    Map<Runnable, Future> futures;
    Map<Runnable, AtomicInteger> executionCount;

    public BasicDispatchService() {
        futures = new HashMap<Runnable, Future>();
        executionCount = new HashMap<Runnable, AtomicInteger>();
    }

    @Override
    public void run() {
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //TODO something at least. also log this
            System.err.println("oh darn!");
        }
    }

    public void addTask(final RepeatableTask task) {
        final RepeatingScheduleInformation scheduleInformation = task.getScheduleInformation();
        if(scheduleInformation.getNumberOfExecutions() == 0) {
            executor.scheduleWithFixedDelay(task, 0, scheduleInformation.getIntervalMillis(), TimeUnit.MILLISECONDS);
        } else {

            Runnable r = new Runnable() {
                int numberOfExecutions = scheduleInformation.getNumberOfExecutions();

                @Override
                public void run() {
                    Future thisFuture = futures.get(this);
                    if(executionCount.get(this).intValue() >=  numberOfExecutions) {
                        futures.remove(this);
                        executionCount.remove(this);
                        thisFuture.cancel(true); //TODO decide if this is decidable
                    } else {
                        executionCount.get(this).incrementAndGet();
                        task.run();
                    }
                }
            };

            executionCount.put(r,new AtomicInteger());

            synchronized (this) {
                Future f = executor.scheduleWithFixedDelay(r, 0, scheduleInformation.getIntervalMillis(), TimeUnit.MILLISECONDS);
                futures.put(r, f);
            }
        }
    }

    public void stopService() {
        stopService(0);
    }

    public void stopService(int timeout) {
        try {
            executor.shutdown();
            executor.awaitTermination(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Failed to wait for tasks to finish. Aborting all tasks immediately.");
        } finally {
            executor.shutdownNow();
        }
    }

    public void cancelTask(final RepeatableTask task) {
        executor.remove(task);
    }

    public void setExecutor(ScheduledThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
