package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.assetengine.indices.impl.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.manager.impl.BasicMenuManager;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.mock.MockUnitManager;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.renderer.impl.BasicMenuRenderer;
import org.supply.simulator.display.renderer.impl.BasicUnitRenderer;
import org.supply.simulator.display.window.impl.BasicWindow;
import org.supply.simulator.display.window.impl.UserCameraInterface;

/**
 * Created by Alex on 7/18/2014.
 */
public class BasicWindowSystemTest {


    private BasicWindow window;
    private MockDisplayCore core;
    private Camera camera;

    private BasicShaderEngine shaderEngine;

    private BasicChunkRenderer chunkRenderer;
    private MockChunkManager chunkManager;

    private BasicUnitRenderer unitRenderer;
    private MockUnitManager unitManager;
    private BasicMenuManager menuManager;
    private BasicMenuRenderer menuRenderer;
    private UnitIndexEngine indexEngine;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("BasicWindowSystemTest");

        shaderEngine = new BasicShaderEngine();
        indexEngine = new UnitIndexEngine();

        camera = new Camera();
        camera.setAspectRatio(1);
        camera.setFarPlane(100);
        camera.setNearPlane(0.1f);
        camera.setFieldOfView(60);

        chunkManager=new MockChunkManager();
        chunkRenderer=new BasicChunkRenderer();
        chunkRenderer.setIndexEngine(new ChunkIndexEngine(20, 20));
        chunkRenderer.setAttributeLocations(new int [] {0,1});

        chunkManager.setEntityRenderer(chunkRenderer);

        unitManager = new MockUnitManager();
        unitRenderer = new BasicUnitRenderer();
        unitRenderer.setAttributeLocations(new int [] {0,1,2});
        unitRenderer.setTextureEngine(new BasicTextureEngine());
        unitRenderer.setIndexEngine(indexEngine);
        unitManager.setEntityRenderer(unitRenderer);

        menuManager = new BasicMenuManager();
        menuRenderer = new BasicMenuRenderer();
        menuRenderer.setAttributeLocations(new int [] {0,1,2});
        menuRenderer.setTextureEngine(new BasicTextureEngine());
        menuRenderer.setIndexEngine(indexEngine);
        menuManager.setEntityRenderer(menuRenderer);

        window = new BasicWindow();
        UserCameraInterface userCameraInterface = new UserCameraInterface();
        userCameraInterface.setCamera(camera);
        window.setUserCameraInterface(userCameraInterface);

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
