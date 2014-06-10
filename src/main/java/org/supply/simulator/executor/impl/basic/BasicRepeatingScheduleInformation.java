package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.RepeatingScheduleInformation;

/**
 * Created by Brandon on 6/9/2014.
 */
public class BasicRepeatingScheduleInformation extends BasicScheduleInformation implements RepeatingScheduleInformation {
    private int numberOfExecutions = 1;
    private long intervalMillis;

    @Override
    public long getIntervalMillis() {
        return intervalMillis;
    }

    @Override
    public int getNumberOfExecutions() {
        return numberOfExecutions;
    }

    public void setInterval(long intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    public void setNumberOfExecutions(int numberOfExecutions) {
        this.numberOfExecutions = numberOfExecutions;
    }
}
