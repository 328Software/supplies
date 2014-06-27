package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.DispatchService;
import org.supply.simulator.executor.RepeatableTask;

import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Brandon on 6/15/2014.
 */
public class BasicTaskManager {
    BasicDispatchService dispatchService;
    ThreadPoolExecutor executor;

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public void schedule(RepeatableTask task) {
        dispatchService.addTask(task);
    }

    public void unschedule(RepeatableTask task) {
        dispatchService.removeTask(task);
    }

    Collection<Runnable> getAllScheduledTasks() {
        return dispatchService.getAll();
    }

    public void setDispatchService(BasicDispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
