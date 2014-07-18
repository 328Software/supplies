package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.manager.chunk.impl.*;
import org.supply.simulator.display.mock.*;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;

/**
 * Created by Alex on 7/3/2014.
 */
public class ChunkCameraShaderTest {

    private BasicChunkType chunkType;


//    MockCamera camera;
    MockShaderEngine shaderEngine;
    BasicChunk chunk;
    MockCamera camera;
    MockDisplayCore core;
    BasicChunkRenderable renderable;

    @Before
    public void create() {
        core = new MockDisplayCore();
        chunkType = new BasicChunkType();
        chunkType.setColumns(20);
        chunkType.setRows(20);
        core.build("ChunkCameraShaderTest");

        shaderEngine = new MockShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");




        camera = new MockCamera();
//        camera.setRows(rows);
//        camera.setColumns(columns);
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.build();

        chunk = new BasicChunk();
        chunk.setData(getChunkData(chunkType.getRows(), chunkType.getColumns(), 0, 0));
        chunk.setAttributeLocations(new int[] {0,1,2});
        chunk.setChunkType(chunkType);

        MockChunkIndexEngine chunkIndexEngine= new MockChunkIndexEngine();
        renderable=chunk.build();
        renderable.setIndicesBufferId(chunkIndexEngine.get(renderable.getChunkType()));
        //OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {

            //camera.update();


            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.render();


            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
            renderable.render();

            GL20.glUseProgram(0);


            core.render();
        }

    }

    @After
    public void destroy() {
        //
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

        renderable.destroy();
        core.destroy();

    }

    public static BasicChunkData<float[], byte[]> getChunkData
            (int row, int col, int topLeftX, int topLeftY) {
        BasicChunkData<float[], byte[]> basicDataOut = new FloatArrayPositionByteArrayColorChunkData();

        float[] positions = new float[row*col* VertexData.positionElementCount*4];
        byte[] colors = new byte[row*col*VertexData.colorElementCount*4];

        int index = 0;
        for(int i = topLeftX; i < +row+topLeftX; i++) {
            for(int j = topLeftY; j < col+topLeftY; j++) {

//                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)((i*columns+j)/256);
                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;

                float x =-0.5f + 0.1f*i,y=0.5f-0.1f*j,z=-(.1f*(i+j)),length=0.1f;

                byte colorbyte = (byte)(((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));

                // We'll define our quad using 4 vertices of the custom 'TexturedVertex' class
                VertexData v0 = new VertexData();
                //top left
                v0.setXYZ(x, y, /*z+length*/0); v0.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v0.setST(0, 0);
                VertexData v1 = new VertexData();
                //bottom left
                v1.setXYZ(x, y-length, /*z*/0); v1.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v1.setST(0, 1);
                VertexData v2 = new VertexData();
                //bottom right
                v2.setXYZ(x+length, y-length, /*z-length*/0); v2.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v2.setST(1, 1);
                VertexData v3 = new VertexData();
                //top right
                v3.setXYZ(x+length, y, /*z*/0); v3.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v3.setST(1, 0);

                VertexData[] vertices = new VertexData[]  {v0, v1, v2, v3};

                // Put each 'Vertex' in one FloatBuffer

                for (int k = 0; k < vertices.length; k++) {
                    // Add position, color and texture floats to the buffer
                    float[] pos = vertices[k].getElements().getPositionData();
                    positions[index] = pos[0];
                    positions[index+1] = pos[1];
                    positions[index+2] = pos[2];
                    positions[index+3] = pos[3];
                    byte[] cols = vertices[k].getElements().getColorData();
                    colors[index] = cols[0];
                    colors[index+1] = cols[1];
                    colors[index+2] = cols[2];
                    colors[index+3] = cols[3];
                    index +=4;

                }
            }
        }


        basicDataOut.setColors(colors);
        basicDataOut.setPositions(positions);

        return basicDataOut;
    }


}
