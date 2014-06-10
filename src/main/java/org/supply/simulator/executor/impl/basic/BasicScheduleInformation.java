package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.ScheduleInformation;

/**
 * Created by Brandon on 6/9/2014.
 */
public class BasicScheduleInformation implements ScheduleInformation {
    private long maxDurationMillis;

    @Override
    public long getMaxDurationMillis() {
        return maxDurationMillis;
    }

    public void setMaxDurationMillis(long maxDurationMillis) {
        this.maxDurationMillis = maxDurationMillis;
    }
}
