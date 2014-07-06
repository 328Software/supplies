package org.supply.simulator.display.window;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.core.DisplayCoreTest;
import org.supply.simulator.display.manager.chunk.MockChunkManager;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicPlayWindowTest {

    private BasicPlayWindow window;

    @Before
    public void create() {
        DisplayCoreTest.build();

        BasicShaderEngine shaderEngine = new BasicShaderEngine();
        shaderEngine.setPlayVertexShader("shaders/vertex.glsl");
        shaderEngine.setPlayFragmentShader("shaders/fragments.glsl");

        window = new BasicPlayWindow();
        window.setCamera(new MockCamera());
        window.setShaderEngine(shaderEngine);
        window.setChunkManager(new MockChunkManager<>());

        window.build();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            window.render();

            DisplayCoreTest.render();
        }

        window.destroy();
        DisplayCoreTest.destroy();
    }

//    @After
//    public void destroy() {
//        //
//
//
//
//    }

}
