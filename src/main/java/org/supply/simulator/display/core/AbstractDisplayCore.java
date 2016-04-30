package org.supply.simulator.display.core;

import org.lwjgl.opengl.Display;
import org.supply.simulator.display.window.Window;
import org.supply.simulator.executor.RepeatingScheduleInformation;
import org.supply.simulator.executor.impl.basic.BasicRepeatingScheduleInformation;
import org.supply.simulator.logging.HasLogger;

/**
 * Basic implementation of DisplayCore. Implements RepeatableTask interface.
 *
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractDisplayCore extends HasLogger implements DisplayCore {

    // Setup variables
    protected int WIDTH;
    protected int HEIGHT;
    protected String titleString;
    protected Window window;

    @Override
    public void run() {
        this.build();
        window.start();

        while (!Display.isCloseRequested()) {
            window.update();
            this.render();
        }
        window.stop();
        this.destroy();
    }

    @Override
    public RepeatingScheduleInformation getScheduleInformation() {
        //TODO: fill this out
        BasicRepeatingScheduleInformation scheduleInformation = new BasicRepeatingScheduleInformation();
        scheduleInformation.setNumberOfExecutions(1);
        scheduleInformation.setMaxDurationMillis(0);
//        scheduleInformation.setNumberOfExecutions(1);
        return scheduleInformation;
    }

    @Override
    public long getTimeLastStarted() {
        //TODO: fill this out
        return 0;
    }

    @Override
    public long getTimeLastCompleted() {
        //TODO: fill this out
        return 0;
    }

    protected abstract void build();

    protected abstract void render();

    protected abstract void destroy();

    public void setTitleString (String titleString) {
        this.titleString=titleString;
    }

    public void setWidth (int width) {
        this.WIDTH=width;
    }

    public void setHeight (int height) {
        this.HEIGHT=height;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

}
