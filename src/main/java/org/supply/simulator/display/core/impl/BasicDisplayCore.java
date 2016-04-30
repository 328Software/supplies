package org.supply.simulator.display.core.impl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.supply.simulator.display.core.AbstractDisplayCore;
import org.supply.simulator.display.core.DisplayCore;
import org.supply.simulator.display.window.Window;
import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.RepeatingScheduleInformation;
import org.supply.simulator.executor.impl.basic.BasicRepeatingScheduleInformation;

/**
 * Created by Alex on 7/19/2014.
 */
public class BasicDisplayCore extends AbstractDisplayCore implements DisplayCore,RepeatableTask {


    public BasicDisplayCore() {
        super();
        titleString = "NUBCORE";
        WIDTH = 800;
        HEIGHT = 600;
    }


    @Override
    public void build() {
        logger.info("START DISPLAY: "+titleString);
        // Setup an OpenGL context with API version 3.2
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);

            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle(titleString);
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

}
