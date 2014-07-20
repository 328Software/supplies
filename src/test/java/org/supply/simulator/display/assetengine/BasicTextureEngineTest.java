package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureHandle;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureType;
import org.supply.simulator.display.simple.SimpleCamera;
import org.supply.simulator.display.simple.SimpleDisplayCore;
import org.supply.simulator.display.simple.SimpleShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicTextureEngineTest extends HasLogger {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private BasicTextureEngine textureEngine;

    private SimpleDisplayCore core;
    private SimpleCamera camera;
    private SimpleShaderEngine shaderEngine;

    //Texture variables
    private TextureHandle textureHandle;

    private static final int textureWidth = 500;
    private static final int textureHeight = 500;
    @Before
    public void createFixture () {
        core = new SimpleDisplayCore();
        core.build("BasicTextureEngineTest");
        shaderEngine = new SimpleShaderEngine();
        camera = new SimpleCamera();
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getViewMatrixLocation());
        camera.build();



        textureEngine = new BasicTextureEngine();

        BasicTextureType textureType = new BasicTextureType();
        textureType.setFileName("textures/alexsface.png");

        textureHandle = textureEngine.get(textureType);

        List<Float> positions = new ArrayList<Float>();





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

        while (!Display.isCloseRequested()) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

            GL11.glViewport(0, 0, WIDTH, HEIGHT);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


            // Set shader program type to VIEW
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());

            camera.render();

            // Clear shader program type
            GL20.glUseProgram(0);

            // Clear bit

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Set shader program type t o CHUNK
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());


            drawTexture();




            GL20.glUseProgram(0);



            core.render();
        }

        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
        core.destroy();

    }

//    public void build() {
//////        rows = data.getRows()
//////        columns = data.getColumns();
//////
//////        indicesBufferId = indexManager.get(ChunkType.MEDIUM_T).getIndicesId();
//////        if (!indexEngine.isIndicesBufferIdStored(rows,columns)) {
//////
//////            List<Integer> indicesBufferData = indexEngine.createIndicesBufferData(2, 2);
//////
//////
//////            IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferData.size());
//////            for(Integer i: indicesBufferData) {
//////                indicesBuffer.put(i);
//////            }
//////
//////            indicesBuffer.flip();
//////
//////            indicesBufferId = GL15.glGenBuffers();
//////            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
//////            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
//////            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//////
//////            indexEngine.storeIndicesBufferId(rows,columns,indicesBufferId);
//////        } else {
//////            indicesBufferId = indexEngine.getIndicesBufferId(rows,columns);
//////        }
////
////
////        vertexAttributesId = GL30.glGenVertexArrays();
////
////        GL30.glBindVertexArray(vertexAttributesId);
////
////        positionsArrayId = GL15.glGenBuffers();
////        colorsArrayId = GL15.glGenBuffers();
////
////        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(data.getPositions().length);
//////        for(Float f: data.getPositions()) {
////            verticesFloatBuffer.put(data.getPositions());
//////        }
////        verticesFloatBuffer.flip();
////
////        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
////        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);
////
////
////        GL20.glVertexAttribPointer(locations[0], BasicChunkData.POSITION_COUNT, GL11.GL_FLOAT,
////                false, BasicChunkData.POSITION_BYTES, BasicChunkData.POSITION_BYTE_OFFSET);
////
////        GL20.glVertexAttribPointer(locations[2], BasicChunkData.TEXTURE_COUNT, GL11.GL_FLOAT,false,
////                BasicChunkData.STRIDE,
////                BasicChunkData.TEXTURE_BYTE_OFFSET);
////
////
////        ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(data.getColors().length);
//////        for(Byte b: data.getColors()) {
////            verticesByteBuffer.put(data.getColors());
//////        }
////        verticesByteBuffer.flip();
////
////        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsArrayId);
////        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesByteBuffer, GL15.GL_STATIC_DRAW);
////
////        GL20.glVertexAttribPointer(locations[1], BasicChunkData.COLOR_COUNT, GL11.GL_UNSIGNED_BYTE,
////                true, BasicChunkData.COLOR_BYTES, 0);
////
////        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
////
////        GL30.glBindVertexArray(0);
////        data=null;
////        isBuilt =true;
////    }
////
////    @Override
////    public void destroy() {
////        GL30.glBindVertexArray(vertexAttributesId);
////
////        GL20.glDisableVertexAttribArray(locations[0]);
////        GL20.glDisableVertexAttribArray(locations[1]);
////
////        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
////        GL15.glDeleteBuffers(positionsArrayId);
////
////        //  TODO figure out why we are unbinding this buffer twice
////        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
////        GL15.glDeleteBuffers(colorsArrayId);
////
////        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
////        GL15.glDeleteBuffers(indicesBufferId);
////
////        GL30.glBindVertexArray(0);
////        GL30.glDeleteVertexArrays(vertexAttributesId);
////
////        isDestroyed = true;
////    }
////
////    @Override
////    public void render() {
////
//////        GL30.glBindVertexArray(vertexAttributesId);
//////        GL20.glEnableVertexAttribArray(locations[0]);
//////        GL20.glEnableVertexAttribArray(locations[1]);
//////        GL20.glEnableVertexAttribArray(locations[2]);
//////
//////        // Bind to the index VBO that has all the information about the order of the vertices
//////        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
//////
//////        // Draw the vertices
//////        GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, rows * columns * INDICES_PER_VERTEX, GL11.GL_UNSIGNED_INT, 0, 0);
//////        // Put everything back to default (deselect)
//////        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//////        GL20.glDisableVertexAttribArray(locations[0]);
//////        GL20.glDisableVertexAttribArray(locations[1]);
//////        GL20.glDisableVertexAttribArray(locations[2]);
//////        GL30.glBindVertexArray(0);
////
////    }


}
