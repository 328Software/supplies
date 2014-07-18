package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.mock.*;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderHandle;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicPlayWindowTest {

    private BasicPlayWindow window;
    private MockDisplayCore core;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("BasicPlayWindowTest");

        MockShaderEngine shaderEngine = new MockShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");
        TextureEngine textureEngine = new MockTextureEngine();
        ChunkIndexEngine indexEngine = new MockChunkIndexEngine();
        ChunkManager chunkManager = new MockChunkManager();
        chunkManager.setIndexEngine(indexEngine);
        window = new BasicPlayWindow();
        window.setShaderEngine(shaderEngine);
        window.setTextureEngine(textureEngine);
        window.setCamera(new MockCamera());
        window.setChunkManager(chunkManager);

        window.build();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            window.render();

            core.render();
        }

        window.destroy();
        core.destroy();
    }
}
