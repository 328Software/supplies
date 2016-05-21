package org.supply.simulator.display.manager.chunk;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Alex on 6/28/2014.
 */
public class DAOChunkManagerTest {
    static { //load everything
        new ClassPathXmlApplicationContext("/application-context.xml");
    }
    //    private ;
//    private static ChunkManager manager;
//    private SimpleCamera camera;
//    private SimpleShaderEngine shaderEngine;
//    private SimpleDisplayCore core;

//    public BasicChunkManagerTest() {
//
//    }

//    @Before
    public void create() {
//        core = new SimpleDisplayCore();
//        core.build("DAOChunkManagerTest");
//
//        shaderEngine = new SimpleShaderEngine();
//        shaderEngine.set(ShaderProgramType.UNTEXTURED_MOVABLE,"shaders/vertex.glsl");
//        shaderEngine.set(ShaderProgramType.UNTEXTURED_MOVABLE,"shaders/fragments.glsl");
//
//        camera = new SimpleCamera();
//
//        manager = new DAOWiredChunkManager();
//        manager.setIndexEngine(new SimpleChunkIndexEngine());
//
////        BasicDataSource source = new BasicDataSource();
////        source.setUsername("root");
////        source.setUsername("password");
////        source.setUrl("jdbc:mysql://localhost:3306/test");
////        source.setDriverClassName("com.mysql.jdbc.Driver");
//
//        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
//        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
//        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
//
//
//        camera.build();
    }

    @Test
    public void doNothing() {

    }

//    @Test
    public void render() {
//        while (!Display.isCloseRequested()) {
//            //camera.update();
//
//
//            // Set shader program type to VIEW
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());
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
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());
//
//            // Update visibleChunks with new camera position
//            manager.update(camera);
//            Iterator<BasicChunkRenderable> it = manager.iterator();
//            while (it.hasNext()) {
//                it.next().render();
//            }
//
//            GL20.glUseProgram(0);
//
//            core.render();
//        }

//        manager.clear();
//        GL20.glUseProgram(0);
//        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());
//        core.destroy();
    }


//    public void setManager(ChunkManager manager) {
//        DAOChunkManagerTest.manager = manager;
//    }
}
