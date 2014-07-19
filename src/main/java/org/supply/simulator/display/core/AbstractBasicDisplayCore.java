package org.supply.simulator.display.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.supply.simulator.display.window.Window;
import org.supply.simulator.logging.HasLogger;

/**
 * Basic implementaion of DisplayCore. Implements RepeatableTask interface.
 *
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractBasicDisplayCore extends HasLogger implements DisplayCore {


    // Setup variables
    protected int WIDTH;
    protected int HEIGHT;
    protected String titleString;


    protected void build(String title) {
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

        GL11.glClearColor(130f / 255f, 208f / 255f, 157f / 255f, 0f);

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    protected void destroy() {
        logger.info("STOP DISPLAY");
        Display.destroy();
    }


    protected void render() {
        Display.sync(60);

        Display.update();

    }

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
