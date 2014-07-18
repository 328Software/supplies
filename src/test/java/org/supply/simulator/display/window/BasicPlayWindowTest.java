package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.mock.MockShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderHandle;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicPlayWindowTest {

    private BasicPlayWindow window;

    @Before
    public void create() {
        MockDisplayCore.build("BasicPlayWindowTest");

        MockShaderEngine<ShaderProgramType,BasicShaderHandle> shaderEngine = new MockShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");

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
