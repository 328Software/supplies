package org.supply.simulator.display.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.impl.BasicCamera;

/**
 * Created by Alex on 6/29/2014.
 */
public class DisplayCoreTest {

    protected static Logger logger = LogManager.getLogger(DisplayCoreTest.class);

    private static final boolean doInteractiveTest = false;

    // Setup variables
    private static String WINDOW_TITLE;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;


    @Before
    public void createFixture() {
        WINDOW_TITLE = "Supply Simulator: You suck";
        build(WINDOW_TITLE);
    }

    @Test
    public void TestDisplayCore() {
        render();
    }


    @After
    public void destroyFixture() {
        destroy();
    }


    public static void build(String title) {
        logger.info("START DISPLAY: "+title);
        setupOpenGL(title);
    }

    public static void destroy() {
        logger.info("STOP DISPLAY");
        destroyOpenGl();
    }

    private static void setupOpenGL(String title) {
        // Setup an OpenGL context with API version 3.2
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);

            org.lwjgl.opengl.Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            org.lwjgl.opengl.Display.setTitle(title);
            org.lwjgl.opengl.Display.create(pixelFormat, contextAtrributes);

            GL11.glViewport(0, 0, WIDTH, HEIGHT);
        } catch (LWJGLException e) {
            logger.error(e);
        }

        GL11.glClearColor(130f / 255f, 208f / 255f, 157f / 255f, 0f);

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    private static void destroyOpenGl() {
        Display.destroy();
    }

    public static void render() {

        Display.sync(60);

        Display.update();
    }

}
