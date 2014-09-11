package org.supply.simulator.display.manager.chunk.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;
import org.supply.simulator.badengine.terrain.impl.CheckeredTestTerrain;
import org.supply.simulator.badengine.terrain.impl.SimpleTerrainGenerator;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.data.attribute.entity.impl.ChunkType;
import org.supply.simulator.data.entity.impl.Chunk;
import org.supply.simulator.data.statistic.entity.impl.ChunkColors;
import org.supply.simulator.data.statistic.entity.impl.ChunkPositions;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderable.chunk.impl.BasicChunkRenderable;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Brandon on 7/8/2014.
 */
public class CheckerTestChunkManager extends AbstractManager<BasicChunkRenderer> implements Manager<BasicChunkRenderer> {
    private int chunkRows = 32;//chunkType.rows();
    private int chunkColumns =32;// chunkType.columns();
    private int totalChunkRows = 25;
    private int totalChunkColumns = 25;

    private ChunkDAO chunkDAO;
    private SessionFactory sessionFactory;
    private boolean isFirst;

    public CheckerTestChunkManager () {
        super();
        isFirst = true;
        visibleRenderables = new ArrayList<BasicChunkRenderable>();

    }
//
//    @Override
//    protected Collection<BasicChunkRenderable> getRenderablesToAdd(Camera camera) {
//        return null;
//    }

    @Override
    protected Collection<BasicChunkRenderable> getRenderablesToRemove(Camera camera) {
        return new ArrayList<BasicChunkRenderable>();
    }

    @Override /*@Transactional(value = "chunk",propagation = Propagation.REQUIRES_NEW)*/
    protected java.util.Collection<BasicChunkRenderable> getRenderablesToAdd(Camera view) {
        Collection<BasicChunkRenderable> newChunks = new ArrayList<BasicChunkRenderable>();
        if (isFirst) {
            isFirst=false;
            int count = 0;
            ChunkType type = new ChunkType();
            type.setColumns(chunkColumns);
            type.setRows(chunkRows);
            SimpleTerrainGenerator generator = new SimpleTerrainGenerator();

            for(TerrainChunk terrainChunk: generator.generate().partition()) {

//            for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
//                for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                logger.info("creating chunk " + (count++));
                Chunk chunk = new Chunk();
                ChunkPositions positions = new ChunkPositions();
                ChunkColors colors = new ChunkColors();
                positions.setValue(terrainChunk.getPositions());
                colors.setValue(terrainChunk.getColors());
                chunk.setChunkType(type);
                chunk.setChunkColors(colors);
                chunk.setChunkPositions(positions);

                BasicChunkRenderable renderable = new BasicChunkRenderable();
                renderable.setEntity(chunk);
                newChunks.add(renderable);
                storeChunk(chunk);
//                }
//            }
            }
        }
        return newChunks;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void storeChunk(Chunk chunk) {
        sessionFactory.getCurrentSession().saveOrUpdate(chunk);
    }

    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


//    class ChunkManagerTerrain extends CheckeredTestTerrain {
//
//        @Override
//        public TerrainChunk getChunkData
//                (int row, int col, int topLeftX, int topLeftY) {
//            TerrainChunk basicDataOut = new TerrainChunk();
//
//            float[] positions = new float[row*col*VertexData.positionElementCount*4];
//            byte[] colors = new byte[row*col*VertexData.colorElementCount*4];
//
//            int index = 0;
//            for(int i = topLeftX; i < +row+topLeftX; i++) {
//                for(int j = topLeftY; j < col+topLeftY; j++) {
//
////                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)((i*columns+j)/256);
//                    byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;
//
//                    float x =-0.5f + 0.1f*i,y=0.5f-0.1f*j,z,zy,zxy,zx,length=0.1f;
//                    z = 2*length*((float) Math.exp(-(
//                                    (Math.pow(i-(topLeftX+row/2),2)/50) +
//                                            (Math.pow(j-(topLeftY+col/2),2)/50)))
//                    );
//                    zy = 2*length*((float) Math.exp(-(
//                                    (Math.pow(i-(topLeftX+row/2),2)/50) +
//                                            (Math.pow(j+1-(topLeftY+col/2),2)/50)))
//                    );
//                    zxy = 2*length*((float) Math.exp(-(
//                                    (Math.pow(i+1-(topLeftX+row/2),2)/50) +
//                                            (Math.pow(j+1-(topLeftY+col/2),2)/50)))
//                    );
//                    zx = 2*length*((float) Math.exp(-(
//                                    (Math.pow(i+1-(topLeftX+row/2),2)/50) +
//                                            (Math.pow(j-(topLeftY+col/2),2)/50)))
//                    );
////                        z = 0;
//                    byte colorbyte = (byte)(((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));
//
//                    // We'll define our quad using 4 vertices of the custom 'TexturedVertex' class
//                    VertexData v0 = new VertexData();
//                    //top left
//                    v0.setXYZ(x, y, /*z+length*/z); v0.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v0.setST(0, 0);
//                    VertexData v1 = new VertexData();
//                    //bottom left
//                    v1.setXYZ(x, y-length, /*z*/zy); v1.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v1.setST(0, 1);
//                    VertexData v2 = new VertexData();
//                    //bottom right
//                    v2.setXYZ(x+length, y-length, /*z-length*/zxy); v2.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v2.setST(1, 1);
//                    VertexData v3 = new VertexData();
//                    //top right
//                    v3.setXYZ(x+length, y, /*z*/zx); v3.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v3.setST(1, 0);
//
//                    VertexData[] vertices = new VertexData[]  {v0, v1, v2, v3};
//
//                    // Put each 'Vertex' in one FloatBuffer
//
//                    for (int k = 0; k < vertices.length; k++) {
//                        // Add position, color and texture floats to the buffer
//                        float[] pos = vertices[k].getElements().getPositionData();
////                    for(Float f: ) {
//                        positions[index] = pos[0];
//                        positions[index+1] = pos[1];
//                        positions[index+2] = pos[2];
//                        positions[index+3] = pos[3];
//                        byte[] cols = vertices[k].getElements().getColorData();
////                    for(Byte b: )  {
//                        colors[index] = cols[0];
//                        colors[index+1] = cols[1];
//                        colors[index+2] = cols[2];
//                        colors[index+3] = cols[3];
//                        index +=4;
////                    }
////                    System.out.println(Arrays.toString(positions));
////                    System.out.println(Arrays.toString(colors));
//                    }
//                }
//            }
//
//
//            basicDataOut.setColors(colors);
//            basicDataOut.setPositions(positions);
//
//            return basicDataOut;
//        }
//    }
}
