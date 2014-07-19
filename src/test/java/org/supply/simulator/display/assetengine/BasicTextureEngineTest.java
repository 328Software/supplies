package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.simple.SimpleShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicTextureEngineTest extends HasLogger {
    private BasicTextureEngine engine;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Camera camera;
    private SimpleShaderEngine shaderEngine;

    //Texture variables
    private int textureId;

    private static final int textureWidth = 500;
    private static final int textureHeight = 500;
    @Before
    public void createFixture () {
//        MockDisplayCore.build("BasicTextureEngineTest");
//        logger.info("START BasicTextureEngineTest");
//
//        engine = new BasicTextureEngine();
//        engine.set(0,"textures/alexsface.png");
//
//        if (engine.get(0).getTextureId()!=1) {
//            logger.error("texture id wrong");
//            System.exit(-1);
//        }
//        logger.info("Successfully created texture");
//
//        shaderEngine = new BasicShaderEngine();
//        shaderEngine.set(ShaderProgramType.MENU,"shaders/vertexWithTexture.glsl");
//        shaderEngine.set(ShaderProgramType.MENU,"shaders/fragmentsWithTexture.glsl");
//        camera = new MockCamera();
//        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getProjectionMatrixLocation());
//        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getModelMatrixLocation());
//        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getViewMatrixLocation());
//        camera.build();





    }


    private void drawTexture() {
    }
    public static void getMenuVertexData (int row, int col, int topLeftX, int topLeftY) {
//        List<Float> positions = new ArrayList<Float>();
//        List<Byte> colors = new ArrayList<Byte>();
//        float length = .5f;
//
//        VertexData v0 = new VertexData();
//        //top left
//        v0.setXYZ(0, 0, /*z+length*/0); v0.setRGB((byte)255, (byte)255, (byte)255);// v0.setST(0, 0);
//        VertexData v1 = new VertexData();
//        //bottom left
//        v1.setXYZ(0, 0-length, /*z*/0); v1.setRGB((byte)255, (byte)255, (byte)255);// v1.setST(0, 1);
//        VertexData v2 = new VertexData();
//        //bottom right
//        v2.setXYZ(0+length, 0-length, /*z-length*/0); v2.setRGB((byte)255, (byte)255, (byte)255);// v2.setST(1, 1);
//        VertexData v3 = new VertexData();
//        //top right
//        v3.setXYZ(0+length, 0, /*z*/0); v3.setRGB((byte)255,(byte)255,(byte)255);// v3.setST(1, 0);
//
//        VertexData[] vertices = new VertexData[]  {v0, v1, v2, v3};
//
//        // Put each 'Vertex' in one FloatBuffer
//
//        for (int k = 0; k < vertices.length; k++) {
//            // Add position, color and texture floats to the buffer
//            for(Float f: vertices[k].getElements().getPositionData()) {
//                positions.add(f);
//            }
//            for(Byte b: vertices[k].getElements().getColorData())  {
//                colors.add(b);
//            }
//        }
//


    }

    @Test
    public void TestPlayTexture () {
//
//        while (!Display.isCloseRequested()) {
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//
//            GL11.glViewport(0, 0, WIDTH, HEIGHT);
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//
//
//            // Set shader program type to VIEW
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
//
//            camera.render();
//
//            // Clear shader program type
//            GL20.glUseProgram(0);
//
//            // Clear bit
//
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//
//            // Set shader program type t o CHUNK
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
//
//
//            drawTexture();
//
//
//
//
//            GL20.glUseProgram(0);
//
//
//
//            MockDisplayCore.render();
//        }
//
//        GL20.glUseProgram(0);
//        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
//        MockDisplayCore.destroy();

    }



    @After
    public void destroyObject() {
//
//        MockDisplayCore.destroy();
    }
}
