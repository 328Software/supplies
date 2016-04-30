package org.supply.simulator.display.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.mock.data.MockChunkType;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.renderable.chunk.ChunkRenderable;
import org.supply.simulator.display.renderable.chunk.impl.BasicChunkRenderable;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;

import java.util.ArrayList;

/**
 * Created by Alex on 7/3/2014.
 */
public class BasicChunkRendererTest {

    private int chunkRows = 20;
    private int chunkColumns = 20;
    private int totalChunkRows = 30;
    private int totalChunkColumns = 30;

    BasicShaderEngine shaderEngine;
    MockCamera  camera;

    MockDisplayCore core;
    BasicChunkRenderer renderer;
    DataGenerator dataGenerator;

    ArrayList<ChunkRenderable> chunks;

    private MockChunkType chunkType;

//    private Vector3f modelPos;
//    private Vector3f modelAngle;
//    private Vector3f modelScale = null;
//    private Vector3f cameraPos = null;
//    private Vector3f cameraAngle = null;
//    private Matrix4f projectionMatrix;
//    private Matrix4f viewMatrix;
//    private Matrix4f modelMatrix;
//    private FloatBuffer matrix44Buffer;
    @Before
    public void create() {
        dataGenerator = new DataGenerator();
        chunkType = new MockChunkType();
        chunkType.setColumns(30);
        chunkType.setRows(20);
        core = new MockDisplayCore();;

        core.build("BasicChunkRendererTest");

        shaderEngine = new BasicShaderEngine();

        camera = new MockCamera();
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.start();

        renderer=new BasicChunkRenderer();
        renderer.setAttributeLocations(new int[] {0,1,2});
        renderer.setChunkIndexEngine(new BasicChunkIndexEngine());

        chunks = new ArrayList<>();
        for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
            for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {



                ChunkRenderable renderable = new BasicChunkRenderable();
                renderable.setEntity(dataGenerator.createChunk(chunkRows, chunkColumns, i, j));
                chunks.add(renderable);
            }
        }

        renderer.build(chunks);

       // OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {




            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.update();

            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            renderer.render(chunks);

            GL20.glUseProgram(0);


            core.render();
        }

    }

    @After
    public void destroy() {
        //
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());


        renderer.destroyAll();
        core.destroy();

    }

    



}
