package org.supply.simulator.display.manager.chunk;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.supply.simulator.display.mock.MockShaderEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderHandle;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.mock.MockCamera;

import java.util.Iterator;

/**
 * Created by Alex on 6/28/2014.
 */
public class DAOChunkManagerTest {
    static { //load everything
        new ClassPathXmlApplicationContext("/application-context.xml");
    }
    //    private ;
    private static ChunkManager manager;
    private Camera camera;
    private MockShaderEngine shaderEngine;
    private MockDisplayCore core;

//    public BasicChunkManagerTest() {
//
//    }

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("DAOChunkManagerTest");

        shaderEngine = new MockShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");

        camera = new MockCamera();

//        chunkIndexEngine= new MockChunkIndexEngine<>();
//        chunkIndexEngine.set();

//        BasicDataSource source = new BasicDataSource();
//        source.setUsername("root");
//        source.setUsername("password");
//        source.setUrl("jdbc:mysql://localhost:3306/test");
//        source.setDriverClassName("com.mysql.jdbc.Driver");

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());


        camera.build();
    }

    @Test
    public void render() {
//        while (!Display.isCloseRequested()) {
//            //camera.update();
//
//
//            // Set shader program type to VIEW
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//
//            camera.render();
//
//            // Clear shader program type
//            GL20.glUseProgram(0);
//
//            // Clear bit
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//
//            // Set shader program type to CHUNK
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//
//            // Update visibleChunks with new camera position
//            manager.update(camera);
//            Iterator<BasicChunk> it = manager.iterator();
//            while (it.hasNext())
//            {
//               // it.next().render();
//            }
////        for (BasicChunk chunk: chunkManager.toArray(new BasicChunk[chunkManager.size()])) {
////            chunk.render();
////        }
//            GL20.glUseProgram(0);
//
//            core.render();
//        }

        manager.clear();
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
        core.destroy();
    }


    public void setManager(ChunkManager manager) {
        DAOChunkManagerTest.manager = manager;
    }
}
