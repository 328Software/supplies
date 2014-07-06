package org.supply.simulator.display.manager.chunk;

import org.lwjgl.BufferUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.display.window.Camera;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 6/29/2014.
 */
public class MockChunkManager<K,V extends Chunk> extends AbstractChunkManager<Integer,BasicChunk> {

    public MockChunkManager () {
        super();
    }

    @Override
    protected BasicChunk getChunk(Integer chunkId) {
        return getChunk(1000,1000);//dummy test data;
    }

    @Override
    protected ArrayList<Integer> getViewableChunks(Camera view) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        return list;


    }


    @Override
    public Iterator<BasicChunk> iterator() {

        return new MockIterator() ;
    }

    private class MockIterator implements Iterator<BasicChunk> {

            @Override
            public boolean hasNext() {
                return iteratorCount<chunkIds.size();
            }

            @Override
            public BasicChunk next() {
                if (iteratorCount<chunkIds.size()) {
                    BasicChunk chunk = chunks.get(chunkIds.get(iteratorCount));
                    iteratorCount++;
                    return chunk;
                } else {
                    return null;
                }
            }

            @Override
            public void remove() {

            }
    }
















    public static BasicChunk getChunk(int row, int col) {
        BasicChunk chunk = new BasicChunk();
        chunk.setData(getData(row,col));
        chunk.setAttributeLocations(new int[] {0,1});
        return chunk;

    }




    public static BasicChunkData<FloatBuffer, ByteBuffer,IntBuffer> getData (int row, int col) {
        BasicChunkData<FloatBuffer, ByteBuffer,IntBuffer> basicDataOut = new BasicChunkData<FloatBuffer, ByteBuffer, IntBuffer>();
        List<Integer> values = new ArrayList<Integer>();

        ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(4 * row * col *
                BasicChunkData.POSITION_BYTES);


        FloatBuffer verticesFloatBuffer = verticesByteBuffer.asFloatBuffer();

        verticesByteBuffer = BufferUtils.createByteBuffer(4 * row * col *
                BasicChunkData.COLOR_BYTES);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {

                int offset = (i* col +j)*4;

                values.add(offset);
                values.add(offset+1);
                values.add(offset+2);
                values.add(offset+2);
                values.add(offset+3);
                values.add(offset);

//                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)((i*columns+j)/256);
                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;



                float x =-0.5f + 0.1f*i,y=0.5f-0.1f*j,z=-(.1f*(i+j)),length=0.1f;


                // We'll define our quad using 4 vertices of the custom 'TexturedVertex' class
                VertexData v0 = new VertexData();
                //top left
                v0.setXYZ(x, y, /*z+length*/0); v0.setRGB(blackInt, (byte)(blackInt&0xf), (byte)(blackInt&0xf0));// v0.setST(0, 0);
                VertexData v1 = new VertexData();
                //bottom left
                v1.setXYZ(x, y-length, /*z*/0); v1.setRGB(blackInt, (byte)(blackInt&0xf), (byte)(blackInt&0xf0));// v1.setST(0, 1);
                VertexData v2 = new VertexData();
                //bottom right
                v2.setXYZ(x+length, y-length, /*z-length*/0); v2.setRGB(blackInt, (byte)(blackInt&0xf), (byte)(blackInt&0xf0));// v2.setST(1, 1);
                VertexData v3 = new VertexData();
                //top right
                v3.setXYZ(x+length, y, /*z*/0); v3.setRGB(blackInt, (byte)(blackInt&0xf), (byte)(blackInt&0xf0));// v3.setST(1, 0);

                VertexData[] vertices = new VertexData[]  {v0, v1, v2, v3};

                // Put each 'Vertex' in one FloatBuffer

                for (int k = 0; k < vertices.length; k++) {
                    // Add position, color and texture floats to the buffer
                    verticesFloatBuffer.put(vertices[k].getElements().getPositionData());
                    verticesByteBuffer.put(vertices[k].getElements().getColorData());
                }
            }
        }
        verticesFloatBuffer.flip();
        verticesByteBuffer.flip();

        int[] indices = new int[values.size()];

        for(int i = 0; i < indices.length;i++) {
            indices[i] = values.get(i);
        }

        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices);
        indicesBuffer.flip();

        basicDataOut.setColorBuffer(verticesByteBuffer);
        basicDataOut.setPositionsBuffer(verticesFloatBuffer);
        basicDataOut.setIndicesBuffer(indicesBuffer);
        basicDataOut.setSize(row,col);

        return basicDataOut;
    }


}