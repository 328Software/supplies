package org.supply.simulator.display.core;

//import org.supply.simulator.executor.RepeatableTask;

/**
 * The core of the display engine. Controls the windows interfaces and starts up the shader engine.
 *
 * Created by Alex on 6/17/2014.
 */
public interface DisplayCore extends SupplyDisplay {
    public void setTitleString(String titleString);
}
