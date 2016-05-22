package org.supply.simulator.badengine;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.factory.TexturedVertex;

import java.util.*;

import static org.supply.simulator.display.renderer.DrawingUtil.ELEMENT_PER_VERTEX;
import static org.supply.simulator.display.renderer.DrawingUtil.VERTICES_PER_QUAD;


/**
 * Created by Alex on 5/21/2016.
 */
public class ChunkGenerator implements Generator<Entity> {

    public static final float CHUNK_QUAD_SIZE = 0.5f;

    private int  row, col;
    private float topLeftX, topLeftY;


    @Override
    public Entity generate() {
        Entity chunk = new BasicChunk();
        Set<Positions> positionsSet = getPositionsSet();

        chunk.setPositions(positionsSet);
        return chunk;
    }

    protected Set<Positions> getPositionsSet() {
        Set<Positions> positionsSet = new HashSet<>();
        Positions pos = Positions.newTexturedColorPositions();

        pos.setTextureKey("Ground1");
        fillPositionsObject(pos);

        positionsSet.add(pos);
        return positionsSet;
    }

    protected void fillPositionsObject(Positions positions) {
        float[] out = new float[row*col* VERTICES_PER_QUAD * ELEMENT_PER_VERTEX];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {

                float x = topLeftX + CHUNK_QUAD_SIZE * i;
                float y = topLeftY - CHUNK_QUAD_SIZE * j;
//                float z = -(CHUNK_QUAD_SIZE * (i + j));
                float z = 0f;
                float w = 1f;

                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;
//                float colorbyte = (((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));
                float r = blackInt;
                float g = (((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));
                float b = (blackInt&0xaa);
                float a = 1f;

                float[] subInfo = getSubInfo(positions.getTextureKey());

                fillData(out,x,y,z,r,g,b,subInfo[0],subInfo[1],subInfo[2],subInfo[3]);

//                fillData(out,i,j,
//                            x,y,z,w,
//                            r,g,b,a,
//                            subInfo[0],subInfo[1],subInfo[2],subInfo[3]);
            }
        }

        positions.setValue(out);
    }


    public void setOptions(int row, int col, float topLeftX, float topLeftY) {
        this.row=row;
        this.col=col;
        this.topLeftX=topLeftX;
        this.topLeftY=topLeftY;
    }


    private float[] getSubInfo(String key) {
        float[] out = null;
        switch (key) {
            case "Ground1":
                out= new float[]{0/127, 0/127, 63/127, 63/127};
                break;
            case "Ground2":
                out = new float[]{0/127, 64/127, 127/127, 63/127};
                break;
            case "Ground3":
                out = new float[]{0/127, 64/127, 63/127, 127/127};
                break;
            case "Ground4":
                out = new float[]{64/127, 64/127, 127/127, 127/127};
                break;
        }
        return out;
    }

    private void fillData(float[] data,
                          float x, float y, float z,
                          float r, float g, float b,
                          float s0, float s1, float s2, float s3) {
        //We'll define our quad using 4 vertices of the custom 'TexturedVertex' class
        TexturedVertex v0 = new TexturedVertex();
        TexturedVertex v1 = new TexturedVertex();
        TexturedVertex v2 = new TexturedVertex();
        TexturedVertex v3 = new TexturedVertex();

        v0.setXYZ(x, y, /*z+length*/0);
        v0.setRGB(r, g, b);
        v0.setST(s0, s1);

        v1.setXYZ(x, y-CHUNK_QUAD_SIZE, /*z*/0);
        v1.setRGB(r, g, b);
        v1.setST(s0, s3);

        v2.setXYZ(x+CHUNK_QUAD_SIZE, y-CHUNK_QUAD_SIZE, /*z-length*/0);
        v2.setRGB(r, g, b);
        v2.setST(s2, s3);

        v3.setXYZ(x+CHUNK_QUAD_SIZE, y, /*z*/0);
        v3.setRGB(r, g, b);
        v3.setST(s2, s1);

        System.arraycopy(v0.getElements(),0,data, 0,ELEMENT_PER_VERTEX);
        System.arraycopy(v1.getElements(),0,data, ELEMENT_PER_VERTEX,ELEMENT_PER_VERTEX);
        System.arraycopy(v2.getElements(),0,data,2*ELEMENT_PER_VERTEX,ELEMENT_PER_VERTEX);
        System.arraycopy(v3.getElements(),0,data,3*ELEMENT_PER_VERTEX,ELEMENT_PER_VERTEX);

//
    }

    private void fillData(float[] data, int i, int j,
                          float x, float y, float z, float w,
                          float r, float g, float b, float a,
                          float s0, float s1, float s2, float s3) {
        int index = 0;
        data[j*ELEMENT_PER_VERTEX + i*row + index]   = x;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = y;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s0;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s1;

        data[j*ELEMENT_PER_VERTEX + i*row + index++] = x;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = y-CHUNK_QUAD_SIZE;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s0;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s3;

        data[j*ELEMENT_PER_VERTEX + i*row + index++] = x+CHUNK_QUAD_SIZE;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = y-CHUNK_QUAD_SIZE;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s2;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s3;

        data[j*ELEMENT_PER_VERTEX + i*row + index++] = x+CHUNK_QUAD_SIZE;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = y;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s2;
        data[j*ELEMENT_PER_VERTEX + i*row + index++] = s1;
    }




}

