package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.OpenGLDebugger;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkIndexManager;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.ShaderType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.MockCamera;

/**
 * Created by Alex on 7/3/2014.
 */
public class ChunkCameraShaderTest {



//    MockCamera camera;
    BasicShaderEngine shaderEngine;
    BasicChunk chunk;
    MockCamera camera;

//    private Vector3f modelPos;
//    private Vector3f modelAngle;
//    private Vector3f modelScale = null;
//    private Vector3f cameraPos = null;
//    private Vector3f cameraAngle = null;
//    private Matrix4f projectionMatrix;
//    private Matrix4f viewMatrix;
//    private Matrix4f modelMatrix;
//    private FloatBuffer matrix44Buffer;

    private int columns;
    private int rows;


    @Before
    public void create() {
        columns=100;
        rows=100;
        MockDisplayCore.build("ChunkCameraShaderTest");

        shaderEngine = new BasicShaderEngine();
        shaderEngine.setShaderFile("shaders/vertex.glsl", ShaderType.VERTEX, ShaderProgramType.PLAY);
        shaderEngine.setShaderFile("shaders/fragments.glsl", ShaderType.FRAGMENT, ShaderProgramType.PLAY);




        camera = new MockCamera();
//        camera.setRows(rows);
//        camera.setColumns(columns);
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.build();

        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getChunkData(rows, columns, 0, 0));
        chunk.setAttributeLocations(new int[] {0,1});
        chunk.setIndexManager(new BasicChunkIndexManager());
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
