package org.supply.simulator.executor.impl.basic;

import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.RepeatingScheduleInformation;
import org.supply.simulator.executor.ScheduleInformation;

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
}
