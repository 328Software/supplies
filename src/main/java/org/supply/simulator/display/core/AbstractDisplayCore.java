package org.supply.simulator.display.core;

import org.supply.simulator.logging.HasLogger;

/**
 * Basic implementaion of DisplayCore. Implements RepeatableTask interface.
 *
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractDisplayCore extends HasLogger implements DisplayCore {


    // Setup variables
    protected int WIDTH;
    protected int HEIGHT;
    protected String titleString;

    public void start() {
        build(titleString);
    }

    public void update() {
        render();
    }

    public void stop() {
        destroy();
    }

    protected abstract void build(String titleString);

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

}
