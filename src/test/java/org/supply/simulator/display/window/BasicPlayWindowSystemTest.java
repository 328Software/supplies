package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.simple.*;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 7/18/2014.
 */
public class BasicPlayWindowSystemTest {


    private BasicPlayWindow window;
    private SimpleDisplayCore core;
    private BasicShaderEngine shaderEngine;
    private BasicTextureEngine textureEngine;
    private BasicChunkIndexEngine indexEngine;
    private ChunkManager chunkManager;

    @Before
    public void create() {
        core = new SimpleDisplayCore();
        core.build("BasicPlayWindowSystemTest");

        shaderEngine = new BasicShaderEngine();
        textureEngine = new BasicTextureEngine();
        indexEngine = new BasicChunkIndexEngine();
        chunkManager = new MockChunkManager();
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
