package org.supply.simulator.display.simple;

import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkType;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Alex on 7/19/2014.
 */
public class SimpleChunkManager implements ChunkManager<SimpleChunkRenderable> {
    private int chunkRows = 20;
    private int chunkColumns = 20;
    private int totalChunkRows = 5;
    private int totalChunkColumns = 5;


    private boolean isFirst;

    protected Collection<SimpleChunkRenderable> visibleChunks;


    protected SimpleChunkIndexEngine indexEngine;

    @Override
    public void update(Camera view) {

        ////////////ADD CHUNKS
        Collection<SimpleChunkRenderable> renderables = getChunksToAdd(view);

        for (SimpleChunkRenderable chunkRenderable: renderables) {
            chunkRenderable.setIndicesBufferId(indexEngine.get(chunkRenderable.getChunkType()));
        }

        addAll(renderables);
        ////////////


        ////////////REMOVE CHUNKS
        //todo - what mechanism signals what chunks should be destroyed? the camera? can it take a chunk and tell return whether or not it is in frame? whose responsibility is it?
        renderables = getChunksToRemove(view);

        for (SimpleChunkRenderable chunkRenderable: renderables) {
            chunkRenderable.destroy();
        }

        removeAll(renderables);
        ////////////



    }




    @Override
    public int size() {
        return visibleChunks.size();
    }

    @Override
    public boolean isEmpty() {
        return visibleChunks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return visibleChunks.contains(o);
    }

    @Override
    public Object[] toArray() {
        return visibleChunks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[]) visibleChunks.toArray();
        return a;
    }

    @Override
    public void clear() {
        for (SimpleChunkRenderable chunk: visibleChunks) {
            chunk.destroy();
        }
        visibleChunks.clear();
    }

    @Override
    public Iterator<SimpleChunkRenderable> iterator() {
        return visibleChunks.iterator();
    }

    @Override
    public boolean add(SimpleChunkRenderable v) {
        return visibleChunks.add(v);
    }

    @Override
    public boolean remove(Object o) {
        return visibleChunks.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return visibleChunks.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends SimpleChunkRenderable> c) {
        return visibleChunks.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return visibleChunks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return visibleChunks.retainAll(c);
    }

    @Override
    public void setIndexEngine(ChunkIndexEngine indexEngine) {
        this.indexEngine = (SimpleChunkIndexEngine)indexEngine;
    }




    public SimpleChunkManager () {
        super();
        isFirst = true;
        visibleChunks = new ArrayList<SimpleChunkRenderable>();
    }


    protected Collection<SimpleChunkRenderable> getChunksToAdd(Camera view) {
        ArrayList<SimpleChunkRenderable> chunks = new ArrayList<>();
        if (isFirst) {
            isFirst=false;

            for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
                for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                    BasicChunkType type = new BasicChunkType();
                    type.setColumns(chunkColumns);
                    type.setRows(chunkRows);
                    SimpleChunk chunk = new SimpleChunk();
                    chunk.setAttributeLocations(new int[]{0,1,2});
                    chunk.setData(getChunkData(chunkRows, chunkColumns, i, j));
                    chunk.setChunkType(type);
                    SimpleChunkRenderable renderable = chunk.build();
                    chunks.add(renderable);
                }
            }
        }
        return chunks;
    }


    protected Collection<SimpleChunkRenderable> getChunksToRemove(Camera view) {
        return new ArrayList<SimpleChunkRenderable>();
    }

    public static BasicChunkData<float[], byte[]> getChunkData
            (int row, int col, int topLeftX, int topLeftY) {
        BasicChunkData<float[], byte[]> basicDataOut = new BasicChunkData<float[], byte[]>();

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

    public void setChunkSizes(int chunkRows,int chunkColumns,int totalChunkRows,int totalChunkColumns) {
        this.chunkRows = chunkRows;
        this.chunkColumns = chunkColumns;
        this.totalChunkRows = totalChunkRows;
        this.totalChunkColumns = totalChunkColumns;
    }

    public void setChunkRows(int chunkRows) {
        this.chunkRows = chunkRows;
    }

    public void setChunkColumns(int chunkColumns) {
        this.chunkColumns = chunkColumns;
    }

    public void setTotalChunkRows(int totalChunkRows) {
        this.totalChunkRows = totalChunkRows;
    }

    public void setTotalChunkColumns(int totalChunkColumns) {
        this.totalChunkColumns = totalChunkColumns;
    }

}