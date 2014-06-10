package org.supply.simulator.executor;

/**
 * Created by Brandon on 6/9/2014.
 */
public interface RepeatingScheduleInformation extends ScheduleInformation {
    int getNumberOfExecutions();
    long getIntervalMillis();
}
