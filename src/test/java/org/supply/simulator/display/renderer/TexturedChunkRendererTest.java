package org.supply.simulator.display.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.factory.TextMenuFactory;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.impl.UserCameraInterface;
import org.supply.simulator.util.TextureUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRendererTest {
    private int chunkRows = 50;
    private int chunkColumns = 50;
    private int totalChunkRows = 5;
    private int totalChunkColumns = 5;

    BasicShaderEngine shaderEngine;
    Camera camera;

    MockDisplayCore core;
    Renderer staticRenderer;
    Renderer dynamicRenderer;
    BasicTextureEngine textureEngine;
    DataGenerator dataGenerator;
    UserCameraInterface userCameraInterface;


    ArrayList<Entity> chunks;
    Set<Entity> entities;

//    private BasicChunkType chunkType;

    @Before
    public void create() {
        dataGenerator = new DataGenerator();

        core = new MockDisplayCore();
        userCameraInterface = new UserCameraInterface();

        shaderEngine = new BasicShaderEngine();
        textureEngine = new BasicTextureEngine();
        BasicIndexEngine indexEngine = new BasicIndexEngine();

        dataGenerator.setTextureEngine(textureEngine);

        camera = new Camera();
        camera.setAspectRatio(1);
        camera.setFarPlane(100);
        camera.setNearPlane(0.1f);
        camera.setFieldOfView(60);

        userCameraInterface.setCamera(camera);

        staticRenderer =new Renderer();
        staticRenderer.setDrawStatic(true);
        staticRenderer.setOneEntityPerBuffer(true);

        staticRenderer.setAttributeLocations(new int[] {0,1,2});
        staticRenderer.setTextureEngine(textureEngine);
        staticRenderer.setIndexEngine(indexEngine);
        staticRenderer.setRows(chunkRows);
        staticRenderer.setColumns(chunkColumns);


        dynamicRenderer=new Renderer();
        dynamicRenderer.setDrawStatic(false);
        dynamicRenderer.setOneEntityPerBuffer(true);

        dynamicRenderer.setAttributeLocations(new int[] {0,1,2});
        dynamicRenderer.setTextureEngine(textureEngine);
        dynamicRenderer.setIndexEngine(indexEngine);



    }


    @Test
    public void TexturedChunkRendererTest() {
        core.build("TexturedChunkRendererTest");


        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
        camera.create();

        chunks = new ArrayList<>();
        for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
            for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                BasicChunk chunk = dataGenerator.createChunk(chunkRows, chunkColumns, i, j);

                double num = Math.random();
                if (num<2.5) {
                    chunk.getPositions().stream().findAny().get().setTextureKey("Ground1");
                } else if (num<5.0) {
                    chunk.getPositions().stream().findAny().get().setTextureKey("Ground2");
                } else if (num<7.5) {
                    chunk.getPositions().stream().findAny().get().setTextureKey("Ground3");
                } else if (num<=1.0) {
                    chunk.getPositions().stream().findAny().get().setTextureKey("Ground4");
                }
                TextureUtils.oldApplyTexture(chunk, textureEngine);
                chunks.add(chunk);
            }
        }

        TextMenuFactory textMenuFactory = new TextMenuFactory(-0.15f, .8f, .1f, .05f, "YOU A SUCK");
        textMenuFactory.setTextureEngine(textureEngine);

        entities = new HashSet<>();
        entities.add(textMenuFactory.build());

        staticRenderer.build(chunks);
        dynamicRenderer.build(entities);


        render();
    }

    private void render() {

        while (!Display.isCloseRequested()) {




            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.TEXTURED_MOVABLE).getProgramId());

            camera.update();
            userCameraInterface.refresh();

            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.TEXTURED_MOVABLE).getProgramId());

            staticRenderer.render(chunks);

            GL20.glUseProgram(0);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.TEXTURED_STATIONARY).getProgramId());


            dynamicRenderer.render(entities);

            GL20.glUseProgram(0);


            core.render();
        }

    }

    @After
    public void destroy() {

        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());


        staticRenderer.destroyAll();
        dynamicRenderer.destroyAll();
        core.destroy();

    }

}
