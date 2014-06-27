package org.supply.simulator.executor;

/**
 * Created by Brandon on 6/9/2014.
 */
public interface DispatchService {
    void addTask(RepeatableTask task);
    void removeTask(RepeatableTask task);
    void stopService();
    void stopService(int timeout);
    int getTaskCount();
}
