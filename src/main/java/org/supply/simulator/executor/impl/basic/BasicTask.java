package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.ScheduleInformation;
import org.supply.simulator.executor.Task;

/**
 * Created by Brandon on 6/9/2014.
 */
public abstract class BasicTask implements Task {

    ScheduleInformation scheduleInformation;
    long timeLastStarted;
    long timeLastCompleted;

    {
        timeLastStarted = timeLastCompleted = System.currentTimeMillis();
    }

    @Override
    public ScheduleInformation getScheduleInformation() {
        return scheduleInformation;
    }

    @Override
    public long getTimeLastStarted() {
        return timeLastStarted;
    }

    @Override
    public long getTimeLastCompleted() {
        return timeLastCompleted;
    }

    public void setScheduleInformation(ScheduleInformation scheduleInformation) {
        this.scheduleInformation = scheduleInformation;
    }

    public void setTimeLastStarted(long timeLastStarted) {
        this.timeLastStarted = timeLastStarted;
    }

    public void setTimeLastCompleted(long timeLastCompleted) {
        this.timeLastCompleted = timeLastCompleted;
    }
}
