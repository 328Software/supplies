package org.supply.simulator.display.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.impl.BasicCamera;

/**
 * Created by Alex on 6/29/2014.
 */
public class DisplayCoreTest {

    private static final boolean doInteractiveTest = false;

    // Setup variables
    private static final String WINDOW_TITLE = "Supply Simulator: You suck";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Input input;


    @Before
    public void createFixture() {
        input = new Input();
        input.init();
        start();

    }

    @Test
    public void TestDisplayCore() {
        if (doInteractiveTest) {
            while (!Display.isCloseRequested()) {

                    input.refreshInput();

            }

        }
    }


    @After
    public void destroyFixture() {
        stop();
    }


    public static void start() {
        System.out.println("START DISPLAY");
        setupOpenGL();
    }

    public static void stop() {
        System.out.println("STOP DISPLAY");
        destroyOpenGl();
    }

    private static void setupOpenGL() {
        // Setup an OpenGL context with API version 3.2
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);

            org.lwjgl.opengl.Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            org.lwjgl.opengl.Display.setTitle(WINDOW_TITLE);
            org.lwjgl.opengl.Display.create(pixelFormat, contextAtrributes);

            GL11.glViewport(0, 0, WIDTH, HEIGHT);
        } catch (LWJGLException e) {
            e.printStackTrace();
//            System.exit(-1);
        }

        // Setup an XNA like background color
        GL11.glClearColor(130f / 255f, 208f / 255f, 157f / 255f, 0f);

        // Map the internal OpenGL coordinate system to the entire screen
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    private static void destroyOpenGl() {
        Display.destroy();
    }

    public class Input {

        private final float rotationDelta = 0.02f;
        private final float rotationDelta2 = 3f;
        private final float posDelta = 0.02f;
        private final float mouseDeltaThresh = 0.000000001f;

        private final float scaleDelta = 0.1f;
        private final Vector3f scaleAddResolution = new Vector3f(scaleDelta, scaleDelta, scaleDelta);
        private final Vector3f scaleMinusResolution = new Vector3f(-scaleDelta, -scaleDelta, -scaleDelta);

        private BasicCamera camera;

        public void init() {
            Keyboard.enableRepeatEvents(true);
            camera = new BasicCamera();
            camera.setModelPos(new Vector3f(0, 0, 0));
            camera.setModelAngle(new Vector3f(0, 0, 0));
            camera.setModelScale(new Vector3f(1, 1, 1));
            camera.setCameraPos(new Vector3f(0, 0, -1));
            camera.setCameraAngle(new Vector3f(0, 0, 0));
        }

        public void refreshInput() {
            Mouse.poll();
            while (Keyboard.next()/*||Mouse.next()|| Mouse.isInsideWindow()*/) {
                // Only listen to events where the key was pressed (down event)
                if (!Keyboard.getEventKeyState()) continue;

//
            }

        }

            //*****Getters

        public Camera getCamera() {
            return (Camera)this.camera;
            }



    }
}
