package org.supply.simulator.display.manager.chunk;

import org.lwjgl.BufferUtils;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkId;
import org.supply.simulator.display.window.Camera;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 6/29/2014.
 */
public class MockChunkManager<K,V extends Chunk> extends AbstractChunkManager<BasicChunkId,BasicChunk> {

    private int chunkRows = 5;
    private int chunkColumns = 5;
    private int totalChunkRows = 2;
    private int totalChunkColumns = 2;

    public MockChunkManager () {
        super();
    }

    @Override
    protected BasicChunk getChunk(BasicChunkId chunkId) {
        return getBasicChunk(chunkRows, chunkColumns, chunkId.getTopLeftRow(), chunkId.getTopLeftColumn());//dummy test data;
    }

    @Override
    protected ArrayList<BasicChunkId> getViewableChunks(Camera view) {
        ArrayList<BasicChunkId> list = new ArrayList<>();

        for (int i =0; i < totalChunkRows*chunkRows; i=i+chunkRows) {
            for (int j =0; j < totalChunkColumns*chunkColumns; j=j+chunkColumns) {
                BasicChunkId id = new BasicChunkId();
                id.setTopLeftRow(i);
                id.setTopLeftColumn(j);
                list.add(id);
            }
        }
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




    public static BasicChunk getBasicChunk(int row, int col, int topLeftX, int topLeftY) {
        BasicChunk chunk = new BasicChunk();
        chunk.setData(getBufferChunk(row, col, topLeftX, topLeftY));
        chunk.setAttributeLocations(new int[] {0,1});
        return chunk;
    }

    public static BasicChunkData<Collection<Float>,Collection<Byte>,Collection<Integer>> getBufferChunk
            (int row, int col, int topLeftX, int topLeftY) {
        BasicChunkData<Collection<Float>,Collection<Byte>,Collection<Integer>> basicDataOut = new BasicChunkData<Collection<Float>,Collection<Byte>,Collection<Integer>>();
        List<Integer> values = new ArrayList<Integer>();

//        ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(4 * row * col *
//                BasicChunkData.POSITION_BYTES);


//        FloatBuffer verticesFloatBuffer = verticesByteBuffer.asFloatBuffer();

//        verticesByteBuffer = BufferUtils.createByteBuffer(4 * row * col *
//                BasicChunkData.COLOR_BYTES);

        List<Float> positions = new ArrayList<Float>();
//        List<Integer> indices = new ArrayList<Integer>();
        List<Byte> colors = new ArrayList<Byte>();

        for(int i = topLeftX; i < +row+topLeftX; i++) {
            for(int j = topLeftY; j < col+topLeftY; j++) {

   //             int offset = (i* col +j)*4;
                int offset = ((i-topLeftX)* col +(j-topLeftY))*4;

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
//                    verticesFloatBuffer.put(vertices[k].getElements().getPositionData());
                    for(Float f: vertices[k].getElements().getPositionData()) {
                        positions.add(f);
                    }
                    for(Byte b: vertices[k].getElements().getColorData())  {
                        colors.add(b);
                    }
//                    verticesByteBuffer.put(vertices[k].getElements().getColorData());
                }
            }
        }
//        verticesFloatBuffer.flip();
//        verticesByteBuffer.flip();

//        int[] indices = new int[values.size()];

//        for(int i = 0; i < values.size();i++) {
//            indices.add()
//            indices[i] = values.get(i);
//        }

//        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
//        indicesBuffer.put(indices);
//        indicesBuffer.flip();

        basicDataOut.setColors(colors);
        basicDataOut.setPositions(positions);
        basicDataOut.setIndices(values);
        basicDataOut.setColumns(col);
        basicDataOut.setRows(row);

        return basicDataOut;
    }


}
