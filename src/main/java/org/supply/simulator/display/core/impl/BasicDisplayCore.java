package org.supply.simulator.display.core.impl;

import org.supply.simulator.display.core.DisplayCore;
import org.supply.simulator.executor.RepeatingScheduleInformation;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicDisplayCore extends HasLogger implements DisplayCore{


    @Override
    public RepeatingScheduleInformation getScheduleInformation() {
        return null;
    }

    @Override
    public long getTimeLastStarted() {
        return 0;
    }

    @Override
    public long getTimeLastCompleted() {
        return 0;
    }

    @Override
    public void run() {



    }
}
