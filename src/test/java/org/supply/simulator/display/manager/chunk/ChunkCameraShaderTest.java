package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.OpenGLDebugger;
import org.supply.simulator.display.assetengine.indices.ChunkType;
import org.supply.simulator.display.assetengine.indices.MockChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexHandle;
import org.supply.simulator.display.assetengine.shader.MockShaderEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderHandle;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.window.MockCamera;

/**
 * Created by Alex on 7/3/2014.
 */
public class ChunkCameraShaderTest {

    private final ChunkType chunkType = ChunkType.MEDIUM_T;


//    MockCamera camera;
MockShaderEngine<ShaderProgramType,BasicShaderHandle> shaderEngine;
    BasicChunk chunk;
    MockCamera camera;

    @Before
    public void create() {
        MockDisplayCore.build("ChunkCameraShaderTest");

        shaderEngine = new MockShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");




        camera = new MockCamera();
//        camera.setRows(rows);
//        camera.setColumns(columns);
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.build();

        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getChunkData(chunkType.rows(), chunkType.columns(), 0, 0));
        chunk.setAttributeLocations(new int[] {0,1});
        MockChunkIndexEngine<ChunkType,BasicChunkIndexHandle> chunkIndexEngine= new MockChunkIndexEngine();
        chunkIndexEngine.set(chunkType, null);
        chunk.setChunkIndexEngine(chunkIndexEngine);
        chunk.build();
        OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {

            //camera.update();


            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.render();


            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
            chunk.render();

            GL20.glUseProgram(0);


            MockDisplayCore.render();
        }

    }

    @After
    public void destroy() {
        //
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

        chunk.destroy();
        MockDisplayCore.destroy();

    }


}
