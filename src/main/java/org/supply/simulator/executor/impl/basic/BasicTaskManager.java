package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.DispatchService;
import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.TaskManager;

import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Brandon on 6/15/2014.
 */
public class BasicTaskManager implements TaskManager {
    volatile BasicDispatchService dispatchService;
    volatile ThreadPoolExecutor executor;

    @Override
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    @Override
    public void schedule(RepeatableTask task) {
        dispatchService.addTask(task);
    }

    @Override
    public void unschedule(RepeatableTask task) {
        dispatchService.removeTask(task);
    }

    @Override
    public Collection<Runnable> getAllScheduledTasks() {
        return dispatchService.getAll();
    }

    @Override
    public void setDispatchService(BasicDispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @Override
    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
