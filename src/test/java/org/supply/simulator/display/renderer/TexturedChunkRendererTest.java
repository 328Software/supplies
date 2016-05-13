package org.supply.simulator.display.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.TexturedChunkRenderer;
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
    TexturedChunkRenderer renderer;
    BasicTextureEngine textureEngine;
    DataGenerator dataGenerator;
    UserCameraInterface userCameraInterface;


    ArrayList<BasicChunk> chunks;

//    private BasicChunkType chunkType;

    @Before
    public void create() {
        dataGenerator = new DataGenerator();

        core = new MockDisplayCore();
        userCameraInterface = new UserCameraInterface();

        core.build("TexturedChunkRendererTest");

        shaderEngine = new BasicShaderEngine();
        textureEngine = new BasicTextureEngine();

        camera = new Camera();
        camera.setAspectRatio(1);
        camera.setFarPlane(100);
        camera.setNearPlane(0.1f);
        camera.setFieldOfView(60);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.create();

        userCameraInterface.setCamera(camera);
//        BasicChunkType type = new BasicChunkType();
//        //where do we get this.
//        type.setRows(20);
//        type.setColumns(20);

        renderer=new TexturedChunkRenderer();
        renderer.setAttributeLocations(new int[] {0,1,2});
        renderer.setTextureEngine(textureEngine);
        renderer.setIndexEngine(new BasicIndexEngine());
//        renderer.setChunkType(type);

        chunks = new ArrayList<>();
        for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
            for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                BasicChunk chunk = dataGenerator.createChunk(chunkRows, chunkColumns, i, j);

                double num = Math.random();
                if (num<2.5) {
                    chunk.setTextureKey("Ground1");
                } else if (num<5.0) {
                    chunk.setTextureKey("Ground2");
                } else if (num<7.5) {
                    chunk.setTextureKey("Ground3");
                } else if (num<=1.0) {
                    chunk.setTextureKey("Ground4");
                }
                chunks.add(chunk);
            }
        }
        renderer.build(chunks);
    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {




            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.update();
            userCameraInterface.refresh();

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

        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());


        renderer.destroyAll();
        core.destroy();

    }

}
