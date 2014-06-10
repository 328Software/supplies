package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brandon on 6/9/2014.
 */
public class BasicDispatchService extends BasicTask implements DispatchService, Task {

    ScheduledThreadPoolExecutor executor;
//    List<ScheduledFuture> futures;

    @Override
    public void run() {
//        executor.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                futures = new ArrayList<ScheduledFuture>();
//
//            }
//        },0,1000l, TimeUnit.MILLISECONDS);
    }

    public void addTask(final RepeatableTask task) {
        final RepeatingScheduleInformation scheduleInformation = task.getScheduleInformation();
        //the task will never stop running
        if(scheduleInformation.getNumberOfExecutions() == 0) {
            executor.scheduleAtFixedRate(task, 0, scheduleInformation.getIntervalMillis(), TimeUnit.MILLISECONDS);
        } else {

        }
    }

    public void cancelTask(final RepeatableTask task) {
        executor.remove(task);
    }

    public void setExecutor(ScheduledThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
