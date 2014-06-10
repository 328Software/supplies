package org.supply.simulator.executor;

/**
 * Created by Brandon on 6/9/2014.
 */
public interface RepeatableTask extends Task {
    @Override
    RepeatingScheduleInformation getScheduleInformation();
}
