package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.MockChunkManager;
import org.supply.simulator.display.shader.ShaderType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicPlayWindowTest {

    private BasicPlayWindow window;

    @Before
    public void create() {
        MockDisplayCore.build("BasicPlayWindowTest");

        BasicShaderEngine shaderEngine = new BasicShaderEngine();
        shaderEngine.setPlayShaderFile("shaders/vertex.glsl", ShaderType.VERTEX);
        shaderEngine.setPlayShaderFile("shaders/fragments.glsl", ShaderType.FRAGMENT);

        window = new BasicPlayWindow();
        window.setShaderEngine(shaderEngine);
        window.setCamera(new MockCamera());
        window.setChunkManager(new MockChunkManager<>());

        window.build();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            window.render();

            MockDisplayCore.render();
        }

        window.destroy();
        MockDisplayCore.destroy();
    }
}
