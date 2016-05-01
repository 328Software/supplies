package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.manager.menu.impl.BasicMenuManager;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.mock.MockUnitManager;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.renderer.menu.impl.BasicMenuRenderer;
import org.supply.simulator.display.renderer.unit.impl.BasicUnitRenderer;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 7/18/2014.
 */
public class BasicPlayWindowSystemTest {


    private BasicPlayWindow window;
    private MockDisplayCore core;
    private MockCamera camera;

    private BasicShaderEngine shaderEngine;

    private BasicChunkRenderer chunkRenderer;
    private MockChunkManager chunkManager;

    private BasicUnitRenderer unitRenderer;
    private MockUnitManager unitManager;
    private BasicMenuManager menuManager;
    private BasicMenuRenderer menuRenderer;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("BasicPlayWindowSystemTest");

        shaderEngine = new BasicShaderEngine();

        camera = new MockCamera();

        chunkManager=new MockChunkManager();
        chunkRenderer=new BasicChunkRenderer();
        chunkRenderer.setChunkIndexEngine(new BasicChunkIndexEngine());
        chunkRenderer.setAttributeLocations(new int [] {0,1});

        chunkManager.setEntityRenderer(chunkRenderer);

        unitManager = new MockUnitManager();
        unitRenderer = new BasicUnitRenderer();
        unitRenderer.setAttributeLocations(new int [] {0,1,2});
        unitRenderer.setTextureEngine(new BasicTextureEngine());
        unitManager.setEntityRenderer(unitRenderer);

        menuManager = new BasicMenuManager();
        menuRenderer = new BasicMenuRenderer();
        menuRenderer.setAttributeLocations(new int [] {0,1,2});
        menuRenderer.setTextureEngine(new BasicTextureEngine());
        menuManager.setEntityRenderer(menuRenderer);

        window = new BasicPlayWindow();

        window.setShaderEngine(shaderEngine);

        window.setCamera(camera);

        window.setChunkManager(chunkManager);
        window.setUnitManager(unitManager);
        window.setMenuManager(menuManager);
        window.start();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            window.update();

            core.render();
        }

        window.stop();
        core.destroy();
    }
}