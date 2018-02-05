package org.supply.simulator.display.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.badengine.temp.DataGenerator;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.impl.UserCameraInterface;

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
    Camera camera;

    MockDisplayCore core;
    BasicChunkRenderer renderer;
    DataGenerator dataGenerator;
    UserCameraInterface userCameraInterface;

    ArrayList<Entity> chunks;

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
        dataGenerator.setTextureEngine(new BasicTextureEngine());
//        chunkType = new BasicChunkType();
//        chunkType.setColumns(30);
//        chunkType.setRows(20);
        core = new MockDisplayCore();
        userCameraInterface = new UserCameraInterface();

        core.build("BasicChunkRendererTest");

        shaderEngine = new BasicShaderEngine();

//        camera = new MockCameraImpl();
        camera = new Camera();
        camera.setAspectRatio(1);
        camera.setFarPlane(100);
        camera.setNearPlane(0.1f);
        camera.setFieldOfView(60);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
        camera.create();

        userCameraInterface.setCamera(camera);

        renderer=new BasicChunkRenderer();
        renderer.setAttributeLocations(new int[] {0,1,2});
        renderer.setIndexEngine(new BasicIndexEngine());
        renderer.setRows(chunkRows);
        renderer.setColumns(chunkColumns);

        chunks = new ArrayList<>();
        for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
            for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                chunks.add(dataGenerator.createChunk(chunkRows, chunkColumns, i, j));
            }
        }

        renderer.build(chunks);

       // OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {




            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());

            camera.update();
            userCameraInterface.refresh();

            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());

            renderer.render(chunks);

            GL20.glUseProgram(0);


            core.render();
        }

    }

    @After
    public void destroy() {
        //
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());


        renderer.destroyAll();
        core.destroy();

    }

    



}
