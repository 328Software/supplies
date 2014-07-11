package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.supply.simulator.display.OpenGLDebugger;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkIndexManager;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.ShaderType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
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
        shaderEngine.setPlayShaderFile("shaders/vertex.glsl", ShaderType.VERTEX);
        shaderEngine.setPlayShaderFile("shaders/fragments.glsl", ShaderType.FRAGMENT);

        shaderEngine.createProgram(ShaderProgramType.PLAY);



        camera = new MockCamera();
        camera.setRows(rows);
        camera.setColumns(columns);
        camera.setProjectionMatrixLocation(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY));
        camera.setViewMatrixLocation(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY));
        camera.setModelMatrixLocation(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY));
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


            shaderEngine.useProgram(ShaderProgramType.PLAY);

            camera.render();


            shaderEngine.useProgram(ShaderProgramType.CLEAR);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            shaderEngine.useProgram(ShaderProgramType.PLAY);
            chunk.render();

            shaderEngine.useProgram(ShaderProgramType.CLEAR);


            MockDisplayCore.render();
        }

    }

    @After
    public void destroy() {
        //
        shaderEngine.useProgram(ShaderProgramType.CLEAR);
        shaderEngine.deleteProgram(ShaderProgramType.PLAY);


        chunk.destroy();
        MockDisplayCore.destroy();

    }


}
