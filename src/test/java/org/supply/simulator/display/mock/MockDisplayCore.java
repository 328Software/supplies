package org.supply.simulator.display.mock;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 6/29/2014.
 */
public class MockDisplayCore extends HasLogger {

    // Setup variables
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private String titleString;


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

    public void destroy() {
        logger.info("STOP DISPLAY");
        Display.destroy();
    }


    public void render() {
        Display.sync(60);

        Display.update();

    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }
}
