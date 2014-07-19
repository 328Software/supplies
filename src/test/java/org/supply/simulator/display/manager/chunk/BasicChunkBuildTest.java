package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.manager.chunk.impl.*;
import org.supply.simulator.display.simple.*;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicChunkBuildTest extends HasLogger {
    private BasicChunkType chunkType;
    
    private SimpleDisplayCore core;

    BasicChunkRenderable renderable;


    private BasicChunk chunk;
    @Before
    public void createFixture () {
        core = new SimpleDisplayCore();
        core.build("BasicChunkBuildTest");
        chunkType = new BasicChunkType();
        chunkType.setRows(20);
        chunkType.setColumns(20);

        chunk = new BasicChunk();
        chunk.setChunkType(chunkType);
        chunk.setData(getChunkData(chunkType.getRows(), chunkType.getColumns(), 0, 0));
        chunk.setAttributeLocations(new int[] {0,1,2});
        SimpleChunkIndexEngine chunkIndexEngine= new SimpleChunkIndexEngine();
        renderable = chunk.build();
        renderable.setIndicesBufferId(chunkIndexEngine.get(renderable.getChunkType()));

        //OpenGLDebugger.printChunkBuffers(chunk);

        logger.info("Chunk built successfully");
        logger.info("OPENGL BUFFERS:");
        logger.info(renderable.getPositionsArrayId());
        logger.info(renderable.getColorsArrayId());
        logger.info(renderable.getVertexAttributesId());
    }

    @Test
    public void renderChunk () {


    }

    @After
    public  void destroyChunk () {
        renderable.destroy();

        logger.info("Chunk destroyed successfully");
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
