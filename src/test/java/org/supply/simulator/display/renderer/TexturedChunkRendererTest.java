package org.supply.simulator.display.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.badengine.ChunkGenerator;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.assetengine.texture.FontTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngineComposite;
import org.supply.simulator.util.DataGenerator;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.impl.UserCameraInterface;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRendererTest {
    private int CHUNK_ROWS =5;
    private int CHUNK_COLUMNS =5;
    private int totalChunkRows = 2;
    private int totalChunkColumns = 2;

    BasicShaderEngine shaderEngine;
    Camera camera;

    MockDisplayCore core;
    Renderer staticRenderer;
    TextureEngine textureEngine;
    DataGenerator dataGenerator;
    ChunkGenerator chunkGenerator;
    UserCameraInterface userCameraInterface;


    ArrayList<Entity> chunks;
    Set<Entity> entities;

//    private BasicChunkType chunkType;

    @Before
    public void create() {
        dataGenerator = new DataGenerator();
        chunkGenerator = new ChunkGenerator();

        core = new MockDisplayCore();
        userCameraInterface = new UserCameraInterface();

        shaderEngine = new BasicShaderEngine();
        textureEngine = new TextureEngineComposite(new BasicTextureEngine(), new FontTextureEngine());
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
        staticRenderer.setRows(CHUNK_ROWS);
        staticRenderer.setColumns(CHUNK_COLUMNS);




    }

    @Test
    public void TexturedChunkRendererTest2() {
        core.build("TexturedChunkRendererTest2");


        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
        camera.create();

        chunks = new ArrayList<>();
        chunkGenerator.setOptions(CHUNK_ROWS,CHUNK_COLUMNS,-.5f,.5f,.5f);
        chunks.add(chunkGenerator.generate());

        staticRenderer.build(chunks);


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




            core.render();
        }

    }

    @After
    public void destroy() {

        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());


        staticRenderer.destroyAll();
        core.destroy();

    }

}
