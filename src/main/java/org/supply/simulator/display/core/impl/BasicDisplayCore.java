package org.supply.simulator.display.core.impl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.supply.simulator.display.core.AbstractDisplayCore;
import org.supply.simulator.display.core.DisplayCore;
import org.supply.simulator.display.window.Window;
import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.RepeatingScheduleInformation;
import org.supply.simulator.executor.impl.basic.BasicRepeatingScheduleInformation;

import java.util.ArrayList;

/**
 * Created by Alex on 7/19/2014.
 */
public class BasicDisplayCore extends AbstractDisplayCore implements DisplayCore,RepeatableTask {

   // private ArrayList<Window> windowList;
    private Window window;

    public BasicDisplayCore() {
        super();
      //  windowList = new ArrayList<>();
        titleString = "NUBCORE";
        WIDTH = 800;
        HEIGHT = 600;
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

    @Override
    public void run() {
        build(titleString);
        window.start();

        while (!Display.isCloseRequested()) {
            window.update();
            this.render();
        }
        window.stop();
        this.destroy();
    }



    @Override
    public void build(String title) {
        logger.info("START DISPLAY: "+title);
        // Setup an OpenGL context with API version 3.2
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);

            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle(title);
            Display.create(pixelFormat, contextAtrributes);

            GL11.glViewport(0, 0, WIDTH, HEIGHT);
        } catch (LWJGLException e) {
            logger.error(e);
        }

        //GL11.glClearColor(130f / 255f, 208f / 255f, 157f / 255f, 0f);
        GL11.glClearColor(0.0f, 0.8f, 1f, 0f);


        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void destroy() {
        logger.info("STOP DISPLAY");
        Display.destroy();
    }


    @Override
    public void render() {
        Display.sync(60);

        Display.update();

    }



//
//    public void setWindowList(ArrayList<Window> windowList) {
//        this.windowList =windowList;
//    }

    public void setWindow(Window window) {
        this.window = window;
    }

}
