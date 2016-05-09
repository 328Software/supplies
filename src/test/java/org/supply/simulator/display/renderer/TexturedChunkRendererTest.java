package org.supply.simulator.display.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.impl.UserCameraInterface;

import java.util.ArrayList;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRendererTest {
    private int chunkRows = 20;
    private int chunkColumns = 20;
    private int totalChunkRows = 30;
    private int totalChunkColumns = 30;

    BasicShaderEngine shaderEngine;
    Camera camera;

    MockDisplayCore core;
    EntityRenderer renderer;
    BasicTextureEngine textureEngine;
    DataGenerator dataGenerator;
    UserCameraInterface userCameraInterface;


    ArrayList<Chunk> chunks;

//    private BasicChunkType chunkType;

    @Before
    public void create() {
//        dataGenerator = new DataGenerator();
//
//        core = new MockDisplayCore();
//        userCameraInterface = new UserCameraInterface();
//
//        core.build("TexturedChunkRendererTest");
//
//        shaderEngine = new BasicShaderEngine();
//        textureEngine = new BasicTextureEngine();
//
//        camera = new Camera();
//        camera.setAspectRatio(1);
//        camera.setFarPlane(100);
//        camera.setNearPlane(0.1f);
//        camera.setFieldOfView(60);
//
//        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
//        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
//        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
//        camera.create();
//
//        userCameraInterface.setCamera(camera);
//
//        renderer=new TexturedChunkRenderer();
//        renderer.setAttributeLocations(new int[] {0,1,2});
//        renderer.setTextureEngine(textureEngine);
//        renderer.setIndexEngine(new ChunkIndexEngine());
//
//        chunks = new ArrayList<>();
//        for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
//            for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
//                chunks.add(dataGenerator.createChunk(chunkRows, chunkColumns, i, j));
//            }
//        }
//
//        renderer.build(chunks);
//
//        // OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
//        while (!Display.isCloseRequested()) {
//
//
//
//
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//
//            camera.update();
//            userCameraInterface.refresh();
//
//            GL20.glUseProgram(0);
//
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//
//            renderer.render(chunks);
//
//            GL20.glUseProgram(0);
//
//
//            core.render();
//        }

    }

    @After
    public void destroy() {
        //
//        GL20.glUseProgram(0);
//        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//
//
//        renderer.destroyAll();
//        core.destroy();

    }

}
