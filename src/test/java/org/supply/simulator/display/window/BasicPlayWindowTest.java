package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.simple.*;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicPlayWindowTest {

    private BasicPlayWindow window;
    private SimpleDisplayCore core;
    private SimpleShaderEngine shaderEngine;
    private SimpleTextureEngine textureEngine;
    private SimpleChunkIndexEngine indexEngine;
    private SimpleChunkManager chunkManager;

    @Before
    public void create() {
        core = new SimpleDisplayCore();
        core.build("BasicPlayWindowTest");

        shaderEngine = new SimpleShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");
        textureEngine = new SimpleTextureEngine();
        indexEngine = new SimpleChunkIndexEngine();
        chunkManager = new SimpleChunkManager();
        chunkManager.setIndexEngine(indexEngine);
        window = new BasicPlayWindow();
        window.setShaderEngine(shaderEngine);
        window.setTextureEngine(textureEngine);
        window.setCamera(new SimpleCamera());
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
