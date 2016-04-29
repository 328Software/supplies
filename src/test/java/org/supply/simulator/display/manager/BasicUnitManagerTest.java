package org.supply.simulator.display.manager;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.extra.SimpleDisplayCore;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.mock.MockUnitManager;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.renderer.unit.impl.BasicUnitRenderer;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicUnitManagerTest {

    private MockUnitManager unitManager;
    private MockChunkManager chunkManager;
    private BasicUnitRenderer unitRenderer;
    private BasicChunkRenderer chunkRenderer;
    private MockCamera camera;
    private BasicShaderEngine shaderEngine;
    private SimpleDisplayCore core;


    @Before
    public void create() {
        core = new SimpleDisplayCore();
        core.build("BasicUnitManagerTest");

        shaderEngine = new BasicShaderEngine();

        camera = new MockCamera();

        unitManager = new MockUnitManager();
        chunkManager = new MockChunkManager();

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());



        camera.start();
        unitRenderer =new BasicUnitRenderer();
        unitRenderer.setTextureEngine(new BasicTextureEngine());
        unitRenderer.setAttributeLocations(new int[]{0, 1, 2});

        chunkRenderer =new BasicChunkRenderer();
        chunkRenderer.setChunkIndexEngine(new BasicChunkIndexEngine());
        chunkRenderer.setAttributeLocations(new int[]{0, 1});

        chunkManager.setEntityRenderer(chunkRenderer);
        chunkManager.start();

        unitManager.setEntityRenderer(unitRenderer);
        unitManager.start();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {


            // Set shader program type to VIEW
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.update();

            // Clear shader program type
            GL20.glUseProgram(0);

            // Clear bit
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//            chunkManager.update();
//            GL20.glUseProgram(0);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNIT).getProgramId());
            unitManager.update();
            GL20.glUseProgram(0);

            core.render();
        }

        unitManager.stop();
        shaderEngine.done(ShaderProgramType.UNIT);
        shaderEngine.done(ShaderProgramType.PLAY);
        core.destroy();
    }
}
