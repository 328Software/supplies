package org.supply.simulator.display.core;

//import org.supply.simulator.executor.RepeatableTask;

import org.supply.simulator.display.window.Window;
import org.supply.simulator.executor.RepeatableTask;

/**
 * The core of the display engine. Controls the windows interfaces and starts up the shader engine.
 *
 * Created by Alex on 6/17/2014.
 */
public interface DisplayCore extends RepeatableTask {
    /**
     *
     * @param titleString
     */
    public void setTitleString(String titleString);

    /**
     *
     * @param width
     */
    public void setWidth (int width);

    /**
     *
     * @param height
     */
    public void setHeight (int height);

    /**
     *
     * @param window
     */
    public void setWindow(Window window);
}
