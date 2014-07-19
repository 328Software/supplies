package org.supply.simulator.display.core.impl;

import org.lwjgl.opengl.Display;
import org.supply.simulator.display.core.AbstractBasicDisplayCore;
import org.supply.simulator.display.core.DisplayCore;
import org.supply.simulator.display.window.Window;
import org.supply.simulator.executor.RepeatingScheduleInformation;

import java.util.ArrayList;

/**
 * Created by Alex on 7/19/2014.
 */
public class BasicDisplayCore extends AbstractBasicDisplayCore implements DisplayCore {

   // private ArrayList<Window> windowList;
    private Window window;

    public BasicDisplayCore() {
        super();
      //  windowList = new ArrayList<>();
        titleString = "BASICCORE";
        WIDTH = 800;
        HEIGHT = 600;
    }

    @Override
    public RepeatingScheduleInformation getScheduleInformation() {
        //TODO: fill this out
        return null;
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

    @Override
    public void run() {
        build(titleString);

        while (!Display.isCloseRequested()) {
            this.render();
        }
        this.destroy();
    }

    @Override
    protected void render() {
//        for (Window window:windowList) {
//            window.render();
//        }
        window.render();
        super.render();
    }

    @Override
    protected void destroy() {
    //    for (Window window:windowList) {
            window.destroy();
//        }
//        windowList.clear();
        super.destroy();
    }
//
//    public void setWindowList(ArrayList<Window> windowList) {
//        this.windowList =windowList;
//    }

    public void setWindow(Window window) {
        this.window = window;
    }

}
