package org.supply.simulator.badengine;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.factory.TexturedVertex;

import java.util.*;

import static org.supply.simulator.display.factory.TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE;
import static org.supply.simulator.display.renderer.DrawingUtil.ELEMENT_PER_VERTEX;
import static org.supply.simulator.display.renderer.DrawingUtil.VERTICES_PER_QUAD;


/**
 * Created by Alex on 5/21/2016.
 */
public class ChunkGenerator implements Generator<Entity> {

    private float op_quad_size;
    private int op_row, op_col;
    private float op_topLeftX, op_topLeftY;


    @Override
    public Entity generate() {
        Entity chunk = new BasicChunk();
        Set<Positions> positionsSet = new HashSet<>();
        for(int i = 0; i < op_row; i++) {
            for(int j = 0; j < op_col; j++) {
                String textureKey = getGroundTextureKey();
                float[] stInfo = getSTInfo(textureKey);
                float[] value =
                        getQuadFloatArrayComplex(op_topLeftX+i*op_quad_size,op_topLeftY-j*op_quad_size,0f,1f, //X,Y,Z,W
                        1f,1f,1f,1f, //R,G,B,A
                        stInfo[0],stInfo[1],stInfo[2],stInfo[3], //ST info
                        op_quad_size); //QUAD WIDTH AND LENGTH


                Positions positions = Positions.newTexturedColorPositions(value);
                positions.setTextureKey(textureKey);
                positionsSet.add(positions);
            }
        }
        chunk.setPositions(positionsSet);
        return chunk;
    }

    private static float[] getSTInfo(String key) {
        float[] out = null;
        switch (key) {
            case "Ground1":
                out= new float[]{0f, 0f, .5f, .5f};
                break;
            case "Ground2":
                out = new float[]{0f, .5f, .5f, 1f};
                break;
            case "Ground3":
                out = new float[]{0f, .5f, .5f, 1f};
                break;
            case "Ground4":
                out = new float[]{.5f, .5f, 1f, 1f};
                break;
        }
        return out;
    }

    private static String getGroundTextureKey() {
        String out = "";
        double num = Math.random();
        if (num<.25) {
            out="Ground1";
        } else if (num<.5) {
            out="Ground2";
        } else if (num<.75) {
            out="Ground3";
        } else if (num<=1.0) {
            out="Ground4";
        }
        return out;
    }

    private static float[] getQuadFloatArrayComplex(float x, float y, float z, float w,
                                                   float r, float g, float b, float a,
                                                   float s0, float s1, float s2, float s3,float quad_size) {
        float[] data = new float[4* TEXTURE_VERTEX_TOTAL_SIZE];
        TexturedVertex v0 = new TexturedVertex();
        TexturedVertex v1 = new TexturedVertex();
        TexturedVertex v2 = new TexturedVertex();
        TexturedVertex v3 = new TexturedVertex();

        v0.setXYZW(x, y, /*z+length*/0,w);
        v0.setRGBA(r, g, b,a);
        v0.setST(s0, s1);

        v1.setXYZW(x, y- quad_size, /*z*/0,w);
        v1.setRGBA(r, g, b,a);
        v1.setST(s0, s3);

        v2.setXYZW(x+ quad_size, y- quad_size, /*z-length*/0,w);
        v2.setRGBA(r, g, b,a);
        v2.setST(s2, s3);

        v3.setXYZW(x+ quad_size, y, /*z*/0,w);
        v3.setRGBA(r, g, b,a);
        v3.setST(s2, s1);

        System.arraycopy(v0.getElements(),0,data, 0,ELEMENT_PER_VERTEX);
        System.arraycopy(v1.getElements(),0,data, ELEMENT_PER_VERTEX,ELEMENT_PER_VERTEX);
        System.arraycopy(v2.getElements(),0,data,2*ELEMENT_PER_VERTEX,ELEMENT_PER_VERTEX);
        System.arraycopy(v3.getElements(),0,data,3*ELEMENT_PER_VERTEX,ELEMENT_PER_VERTEX);

        return data;
    }



    public void setOptions(int row, int col, float topLeftX, float topLeftY, float quad_size) {
        this.op_row =row;
        this.op_col =col;
        this.op_topLeftX =topLeftX;
        this.op_topLeftY =topLeftY;
        this.op_quad_size =quad_size;
    }


    /**
     * Packs many quads into 1 positions object.
     *
     * @param textureKey
     */
    protected static Positions createMultiQuadPositions(String textureKey, int row, int col, float topLeftX, float topLeftY, float quad_size) {
        float[] data = new float[row*col* VERTICES_PER_QUAD * ELEMENT_PER_VERTEX];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {

                float x = topLeftX + quad_size * i;
                float y = topLeftY - quad_size * j;
//                float z = -(op_quad_size * (i + j));
                float z = 0f;
                float w = 1f;

                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;
//                float colorbyte = (((i/op_col%2|j/op_row%2)==0||(i/op_col%2&j/op_row%2)==1)?(blackInt&0xf):(blackInt&0xff));
                float r = blackInt;
                float g = (((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));
                float b = (blackInt&0xaa);
                float a = 1f;

                float[] stInfo = getSTInfo(textureKey);


                int index = 0;
                data[j*ELEMENT_PER_VERTEX + i*row + index]   = x;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = y;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[0];
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[1];

                data[j*ELEMENT_PER_VERTEX + i*row + index++] = x;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = y- quad_size;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[0];
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[3];

                data[j*ELEMENT_PER_VERTEX + i*row + index++] = x+ quad_size;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = y- quad_size;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[2];
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[3];

                data[j*ELEMENT_PER_VERTEX + i*row + index++] = x+ quad_size;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = y;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = z;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = w;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = r;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = b;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = g;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = a;
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[2];
                data[j*ELEMENT_PER_VERTEX + i*row + index++] = stInfo[1];
            }
        }

        Positions positions = Positions.newTexturedColorPositions(data);
        positions.setTextureKey(textureKey);
//        positions.setValue(data);
        return positions;
    }


}

