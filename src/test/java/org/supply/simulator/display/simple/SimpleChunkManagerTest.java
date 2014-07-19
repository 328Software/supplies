package org.supply.simulator.display.simple;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkRenderable;
import org.supply.simulator.display.mock.MockChunkManager;
import org.supply.simulator.display.window.Camera;

import java.util.Iterator;

/**
 * Created by Alex on 7/19/2014.
 */
public class SimpleChunkManagerTest {
    private SimpleChunkManager manager;
    private SimpleCamera camera;
    private SimpleShaderEngine shaderEngine;
    private SimpleDisplayCore core;


    @Before
    public void create() {
        core = new SimpleDisplayCore();
        core.build("SimpleChunkManagerTest");

        shaderEngine = new SimpleShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");

        camera = new SimpleCamera();

        manager = new SimpleChunkManager();
        manager.setIndexEngine(new SimpleChunkIndexEngine());

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());


        camera.build();
    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            //camera.update();


            // Set shader program type to VIEW
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.render();

            // Clear shader program type
            GL20.glUseProgram(0);

            // Clear bit
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // Set shader program type to CHUNK
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            // Update visibleChunks with new camera position
            manager.update(camera);
            Iterator<SimpleChunkRenderable> it = manager.iterator();
            while (it.hasNext())
            {
                it.next().render();
            }

            GL20.glUseProgram(0);

            core.render();
        }

        manager.clear();
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
        core.destroy();
    }
}
