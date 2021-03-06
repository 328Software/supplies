package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.DispatchService;
import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.RepeatingScheduleInformation;
import org.supply.simulator.executor.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Brandon on 6/9/2014.
 */
public class BasicDispatchService extends BasicTask implements DispatchService, Task {

    //todo make sure volatile is sufficient
    volatile protected ScheduledThreadPoolExecutor executor;
    volatile protected Map<Runnable, Future> futures;
    volatile protected Map<Runnable, AtomicInteger> executionCount;

    public BasicDispatchService() {
        futures = new HashMap<Runnable, Future>();
        executionCount = new HashMap<Runnable, AtomicInteger>();
    }

    @Override
    public void run() {
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            //TODO something at least. also log this
            System.err.println("oh darn!");
        }
    }

    @Override
    public void addTask(final RepeatableTask task) {
        final RepeatingScheduleInformation scheduleInformation = task.getScheduleInformation();

        long interval = scheduleInformation.getIntervalMillis();
        interval = (interval <= 0)?1:interval;

        if(scheduleInformation.getNumberOfExecutions() == 0) {
            synchronized (this) {
                Future f = executor.scheduleWithFixedDelay(task, 0, interval, TimeUnit.MILLISECONDS);
                futures.put(task, f);
            }
        } else {
            Runnable r = new Runnable() {
                final int numberOfExecutions = scheduleInformation.getNumberOfExecutions();

                @Override
                public void run() {
                    Future thisFuture = futures.get(task);
                    executionCount.get(task).incrementAndGet();
                    task.run();
                    if(executionCount.get(task).intValue() >= numberOfExecutions) {
                        synchronized (this) {
                            executionCount.remove(task);
                            futures.remove(task);
                            thisFuture.cancel(true); //TODO decide if this is decidable
                        }
                    }
                }
            };


            synchronized (this) {
                executionCount.put(task,new AtomicInteger());
                Future f = executor.scheduleWithFixedDelay(r, 0, interval, TimeUnit.MILLISECONDS);
                futures.put(task, f);
            }
        }
    }

    @Override
    public void removeTask(final RepeatableTask task) {
        synchronized (this) {
            executionCount.remove(task);
            futures.remove(task).cancel(true);
        }
    }

    @Override
    public void stopService() {
        stopService(0);
    }

    @Override
    public int getTaskCount() {
        return futures.size();
    }

    @Override
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

    public Set<Runnable> getAll() {
        return futures.keySet();
    }

    public void setExecutor(ScheduledThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
