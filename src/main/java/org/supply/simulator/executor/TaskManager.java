package org.supply.simulator.executor;

import org.supply.simulator.executor.impl.basic.BasicDispatchService;

import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Brandon on 6/8/2014.
 */
public interface TaskManager {
    public void execute(Runnable runnable);

    public void schedule(RepeatableTask task);

    public void unschedule(RepeatableTask task);

    Collection<Runnable> getAllScheduledTasks();

    public void setDispatchService(BasicDispatchService dispatchService);

    public void setExecutor(ThreadPoolExecutor executor);
}
