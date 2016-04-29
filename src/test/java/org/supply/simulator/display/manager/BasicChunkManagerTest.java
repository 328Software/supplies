package org.supply.simulator.display.manager;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.extra.SimpleDisplayCore;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.impl.BasicCamera;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicChunkManagerTest {

    private MockChunkManager manager;
    private BasicChunkRenderer renderer;
    private MockCamera camera;
    private BasicShaderEngine shaderEngine;
    private SimpleDisplayCore core;


    @Before
    public void create() {
        core = new SimpleDisplayCore();
        core.build("BasicChunkManagerTest");

        shaderEngine = new BasicShaderEngine();

        camera = new MockCamera();

        manager = new MockChunkManager();

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());



        camera.start();
        renderer=new BasicChunkRenderer();
        renderer.setChunkIndexEngine(new BasicChunkIndexEngine());
        renderer.setAttributeLocations(new int [] {0,1});

        manager.setEntityRenderer(renderer);
        manager.start();

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

            // Set shader program type to CHUNK
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            // Update visibleRenderables with new camera position
            manager.update();
            GL20.glUseProgram(0);

            core.render();
        }

        manager.stop();
        shaderEngine.done(ShaderProgramType.PLAY);
        core.destroy();
    }
}
