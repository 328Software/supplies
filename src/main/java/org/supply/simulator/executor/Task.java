package org.supply.simulator.executor;

/**
 * Created by Brandon on 6/8/2014.
 */
public interface Task extends Runnable {
    ScheduleInformation getScheduleInformation();
    long getTimeLastStarted();
    long getTimeLastCompleted();
}
