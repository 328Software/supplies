package org.supply.simulator.display.manager.chunk;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.ShaderType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.MockCamera;

import java.util.Iterator;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicChunkManagerTest {

    private ChunkManager manager;
    private Camera camera;
    private ShaderEngine shaderEngine;


    @Before
    public void create() {
        MockDisplayCore.build("BasicChunkManagerTest");

        shaderEngine = new BasicShaderEngine();
        shaderEngine.setPlayShaderFile("shaders/vertex.glsl", ShaderType.VERTEX);
        shaderEngine.setPlayShaderFile("shaders/fragments.glsl", ShaderType.FRAGMENT);

        camera = new MockCamera();

         manager = new MockChunkManager();

        shaderEngine.createProgram(ShaderProgramType.PLAY);
        camera.setProjectionMatrixLocation(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY));
        camera.setModelMatrixLocation(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY));
        camera.setViewMatrixLocation(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY));


        camera.build();
    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            //camera.update();


            // Set shader program type to VIEW
            shaderEngine.useProgram(ShaderProgramType.PLAY);

            camera.render();

            // Clear shader program type
            shaderEngine.useProgram(ShaderProgramType.CLEAR);

            // Clear bit
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // Set shader program type to CHUNK
            shaderEngine.useProgram(ShaderProgramType.PLAY);

            // Update chunkCollection with new camera position
            manager.update(camera);
            Iterator<BasicChunk> it = manager.iterator();
            while (it.hasNext())
            {
                it.next().render();
            }
//        for (BasicChunk chunk: chunkManager.toArray(new BasicChunk[chunkManager.size()])) {
//            chunk.render();
//        }
            shaderEngine.useProgram(ShaderProgramType.CLEAR);

            MockDisplayCore.render();
        }

        manager.clear();
        shaderEngine.useProgram(ShaderProgramType.CLEAR);
        shaderEngine.deleteProgram(ShaderProgramType.PLAY);
        MockDisplayCore.destroy();
    }
}
