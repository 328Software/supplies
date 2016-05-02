package org.supply.simulator.badengine.terrain.impl;

import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Brandon on 7/20/2014.
 */
public class CheckeredVariableElevationTerrain extends CheckeredTestTerrain {

    public CheckeredVariableElevationTerrain() throws IOException {
        super();
    }

    public CheckeredVariableElevationTerrain(Path file) throws IOException {
        super(file);
    }

    public CheckeredVariableElevationTerrain(File file) throws IOException {
        super(file);
    }

    @Override
    public TerrainChunk getChunkData
            (int row, int col, int topLeftX, int topLeftY) {
        TerrainChunk basicDataOut = new TerrainChunk();

        float[] positions = new float[row*col*VertexData.positionElementCount*4];
        byte[] colors = new byte[row*col*VertexData.colorElementCount*4];

        int index = 0;
        for(int i = topLeftX; i < +row+topLeftX; i++) {
            for(int j = topLeftY; j < col+topLeftY; j++) {

//                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)((i*columns+j)/256);
                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;

                float x =-0.5f + 0.1f*i,y=0.5f-0.1f*j,z,zy,zxy,zx,length=0.1f;
                z = 2*length*((float) Math.exp(-(
                        (Math.pow(i-(topLeftX+row/2),2)/50) +
                                (Math.pow(j-(topLeftY+col/2),2)/32)))
                );
                zy = 2*length*((float) Math.exp(-(
                        (Math.pow(i-(topLeftX+row/2),2)/50) +
                                (Math.pow(j+1-(topLeftY+col/2),2)/32)))
                );
                zxy = 2*length*((float) Math.exp(-(
                        (Math.pow(i+1-(topLeftX+row/2),2)/50) +
                                (Math.pow(j+1-(topLeftY+col/2),2)/32)))
                );
                zx = 2*length*((float) Math.exp(-(
                        (Math.pow(i+1-(topLeftX+row/2),2)/50) +
                                (Math.pow(j-(topLeftY+col/2),2)/32)))
                );
//                        z = 0;
                byte colorbyte = (byte)(((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));

                // We'll define our quad using 4 vertices of the custom 'TexturedVertex' class
                VertexData v0 = new VertexData();
                //top left
                v0.setXYZ(x, y, /*z+length*/z); v0.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v0.setST(0, 0);
                VertexData v1 = new VertexData();
                //bottom left
                v1.setXYZ(x, y-length, /*z*/zy); v1.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v1.setST(0, 1);
                VertexData v2 = new VertexData();
                //bottom right
                v2.setXYZ(x+length, y-length, /*z-length*/zxy); v2.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v2.setST(1, 1);
                VertexData v3 = new VertexData();
                //top right
                v3.setXYZ(x+length, y, /*z*/zx); v3.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v3.setST(1, 0);

                VertexData[] vertices = new VertexData[]  {v0, v1, v2, v3};

                // Put each 'Vertex' in one FloatBuffer

                for (int k = 0; k < vertices.length; k++) {
                    // Add position, color and texture floats to the buffer
                    float[] pos = vertices[k].getElements().getPositionData();
//                    for(Float f: ) {
                    positions[index] = pos[0];
                    positions[index+1] = pos[1];
                    positions[index+2] = pos[2];
                    positions[index+3] = pos[3];
                    byte[] cols = vertices[k].getElements().getColorData();
//                    for(Byte b: )  {
                    colors[index] = cols[0];
                    colors[index+1] = cols[1];
                    colors[index+2] = cols[2];
                    colors[index+3] = cols[3];
                    index +=4;
//                    }
//                    System.out.println(Arrays.toString(positions));
//                    System.out.println(Arrays.toString(colors));
                }
            }
        }


        basicDataOut.setColors(colors);
        basicDataOut.setPositions(positions);

        return basicDataOut;
    }

//
//    @Override
//    public TerrainChunk getChunkData
//            (int row, int col, int topLeftX, int topLeftY) {
//        TerrainChunk basicDataOut = new TerrainChunk();
//
//        float[] positions = new float[row*col*VertexData.positionElementCount*4];
//        byte[] colors = new byte[row*col*VertexData.colorElementCount*4];
//
//        int index = 0;
//        for(int i = topLeftX; i < +row+topLeftX; i++) {
//            for(int j = topLeftY; j < col+topLeftY; j++) {
//
////                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)((i*columns+j)/256);
//                byte blackInt = (i%2|j%2)==0||(i%2&j%2)==1?(byte)0:(byte)-1;
//
//                float x =-0.5f + 0.1f*i,y=0.5f-0.1f*j,z,length=0.1f;
//                z = (float) Math.exp(-(((x-topLeftX)/2)+((y-topLeftY)/2)));
//
//                byte colorbyte = (byte)(((i/col%2|j/row%2)==0||(i/col%2&j/row%2)==1)?(blackInt&0xf):(blackInt&0xff));
//
//                // We'll define our quad using 4 vertices of the custom 'TexturedVertex' class
//                VertexData v0 = new VertexData();
//                //top left
//                v0.setXYZ(x, y, /*z+length*/z); v0.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v0.setST(0, 0);
//                VertexData v1 = new VertexData();
//                //bottom left
//                v1.setXYZ(x, y-length, /*z*/z); v1.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v1.setST(0, 1);
//                VertexData v2 = new VertexData();
//                //bottom right
//                v2.setXYZ(x+length, y-length, /*z-length*/z); v2.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v2.setST(1, 1);
//                VertexData v3 = new VertexData();
//                //top right
//                v3.setXYZ(x+length, y, /*z*/z); v3.setRGB(blackInt, colorbyte, (byte)(blackInt&0xaa));// v3.setST(1, 0);
//
//                VertexData[] vertices = new VertexData[]  {v0, v1, v2, v3};
//
//                // Put each 'Vertex' in one FloatBuffer
//
//                for (int k = 0; k < vertices.length; k++) {
//                    // Add position, color and texture floats to the buffer
//                    float[] pos = vertices[k].getElements().getPositionData();
////                    for(Float f: ) {
//                    positions[index] = pos[0];
//                    positions[index+1] = pos[1];
//                    positions[index+2] = pos[2];
//                    positions[index+3] = pos[3];
//                    byte[] cols = vertices[k].getElements().getColorData();
////                    for(Byte b: )  {
//                    colors[index] = cols[0];
//                    colors[index+1] = cols[1];
//                    colors[index+2] = cols[2];
//                    colors[index+3] = cols[3];
//                    index +=4;
////                    }
////                    System.out.println(Arrays.toString(positions));
////                    System.out.println(Arrays.toString(colors));
//                }
//            }
//        }
//
//
//        basicDataOut.setColors(colors);
//        basicDataOut.setPositions(positions);
//
//        return basicDataOut;
//    }
}
