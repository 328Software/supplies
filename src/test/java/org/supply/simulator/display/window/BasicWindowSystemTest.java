package org.supply.simulator.display.window;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.manager.impl.BasicMenuManager;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.mock.MockUnitManager;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.impl.BasicWindow;
import org.supply.simulator.display.window.impl.UserCameraInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7/18/2014.
 */
public class BasicWindowSystemTest {
    private static final int CHUNK_ROWS = 20;
    private static final int CHUNK_COLUMNS = 20;
    private static final int MAX_ENTITIES = 100;


    private BasicWindow window;
    private MockDisplayCore core;
    private Camera camera;

    private BasicShaderEngine shaderEngine;

    private BasicChunkRenderer chunkRenderer;
    private MockChunkManager chunkManager;

    DataGenerator generator;

    private Renderer unitRenderer;
    private Renderer menuRenderer;

    private MockUnitManager unitManager;
    private BasicMenuManager menuManager;
    private BasicIndexEngine indexEngine;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("BasicWindowSystemTest");

        shaderEngine = new BasicShaderEngine();
        indexEngine = new BasicIndexEngine();
        generator = new DataGenerator();

        camera = new Camera();
        camera.setAspectRatio(1);
        camera.setFarPlane(100);
        camera.setNearPlane(0.1f);
        camera.setFieldOfView(60);

        chunkManager=new MockChunkManager();
        chunkRenderer=new BasicChunkRenderer();
        chunkRenderer.setColumns(CHUNK_COLUMNS);
        chunkRenderer.setRows(CHUNK_ROWS);
        chunkRenderer.setIndexEngine(indexEngine);
        chunkRenderer.setAttributeLocations(new int [] {0,1});

        chunkManager.setEntityRenderer(chunkRenderer);
        BasicTextureEngine textureEngine = new BasicTextureEngine();

        generator.setTextureEngine(textureEngine);

        unitManager = new MockUnitManager();
        unitRenderer = new Renderer();
        unitRenderer.setAttributeLocations(new int [] {0,1,2});
        unitRenderer.setTextureEngine(textureEngine);
        unitRenderer.setIndexEngine(indexEngine);
        unitRenderer.setColumns(1);
        unitRenderer.setRows(MAX_ENTITIES);
        unitManager.setEntityRenderer(unitRenderer);

        menuManager = new BasicMenuManager();
        menuRenderer = new Renderer();
        menuRenderer.setAttributeLocations(new int [] {0,1,2});
        menuRenderer.setTextureEngine(textureEngine);
        menuRenderer.setIndexEngine(indexEngine);
        menuRenderer.setColumns(1);
        menuRenderer.setRows(MAX_ENTITIES);
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


        populateManagers();

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

    private void populateManagers() {
        List<Entity> menus=new ArrayList<>();
        menus.add(generator.createMenu(-0.15f, .8f, 0, .1f, .05f, "Y"));
        menus.add(generator.createMenu(-0.10f, .8f, 0, .1f, .05f, "O"));
        menus.add(generator.createMenu(-0.05f, .8f, 0, .1f, .05f, "U"));
        menus.add(generator.createMenu(0.0f, .8f, 0, .1f, .05f, " "));
        menus.add(generator.createMenu(0.05f, .8f, 0, .1f, .05f, "S"));
        menus.add(generator.createMenu(0.10f, .8f, 0, .1f, .05f, "U"));
        menus.add(generator.createMenu(0.15f, .8f, 0, .1f, .05f, "C"));
        menus.add(generator.createMenu(0.2f, .8f, 0, .1f, .05f, "K"));
        menuManager.add(menus);
    }
}
