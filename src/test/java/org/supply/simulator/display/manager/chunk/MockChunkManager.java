package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkIndexManager;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 6/29/2014.
 */
public class MockChunkManager<V extends Chunk> extends AbstractChunkManager<BasicChunk> {

    private int chunkRows = 5;
    private int chunkColumns = 5;
    private int totalChunkRows = 4;
    private int totalChunkColumns = 4;

    private boolean isFirst;

    public MockChunkManager () {
        super();
        isFirst = true;
        chunkCollection = new ArrayList<BasicChunk>();
        indexManager = new BasicChunkIndexManager();

    }

    @Override
    protected void updateChunks(Camera view) {
        if (isFirst) {
            isFirst=false;
            for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
                for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                    BasicChunk chunk = new BasicChunk();
                    chunk.setAttributeLocations(new int[]{0,1});
                    chunk.setData(getChunkData(chunkRows,chunkColumns,i,j));
                    chunkCollection.add(chunk);
                }
            }
        }
    }


    public static BasicChunkData<List<Float>,List<Byte>,List<Integer>> getChunkData
            (int row, int col, int topLeftX, int topLeftY) {
        BasicChunkData<List<Float>,List<Byte>,List<Integer>> basicDataOut = new BasicChunkData<List<Float>,List<Byte>,List<Integer>>();
        List<Integer> values = new ArrayList<Integer>();

        List<Float> positions = new ArrayList<Float>();
        List<Byte> colors = new ArrayList<Byte>();

        for(int i = topLeftX; i < +row+topLeftX; i++) {
            for(int j = topLeftY; j < col+topLeftY; j++) {

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
                    for(Float f: vertices[k].getElements().getPositionData()) {
                        positions.add(f);
                    }
                    for(Byte b: vertices[k].getElements().getColorData())  {
                        colors.add(b);
                    }
                }
            }
        }


        basicDataOut.setColors(colors);
        basicDataOut.setPositions(positions);
    
        basicDataOut.setColumns(col);
        basicDataOut.setRows(row);

        return basicDataOut;
    }


}
