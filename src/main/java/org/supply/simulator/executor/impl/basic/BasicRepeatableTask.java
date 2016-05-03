package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.RepeatingScheduleInformation;

/**
 * Created by Brandon on 6/9/2014.
 */
public abstract class BasicRepeatableTask extends BasicTask implements RepeatableTask {
    RepeatingScheduleInformation scheduleInformation;

    @Override
    public RepeatingScheduleInformation getScheduleInformation() {
        return scheduleInformation;
    }

    public void setScheduleInformation(RepeatingScheduleInformation scheduleInformation) {
        this.scheduleInformation = scheduleInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicRepeatableTask)) return false;

        BasicRepeatableTask that = (BasicRepeatableTask) o;

        return scheduleInformation.equals(that.scheduleInformation);
    }

    @Override
    public int hashCode() {
        return scheduleInformation.hashCode();
    }
}
