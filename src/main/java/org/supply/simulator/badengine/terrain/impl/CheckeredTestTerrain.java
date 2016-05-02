package org.supply.simulator.badengine.terrain.impl;

import org.supply.simulator.badengine.terrain.Terrain;
import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;
import org.supply.simulator.badengine.terrain.impl.io.AbstractFileSeekingTerrain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Brandon on 7/20/2014.
 */
public class CheckeredTestTerrain extends AbstractFileSeekingTerrain implements Terrain {
    private int vertexRows = 8;//chunkType.rows();
    private int vertexColumns = 8;// chunkType.columns();
    private int chunkRows = 2;
    private int chunkColumns = 2;

    public CheckeredTestTerrain() throws IOException {
        super();
    }

    public CheckeredTestTerrain(Path file) throws IOException {
        super(file);
    }

    public CheckeredTestTerrain(File file) throws IOException {
        super(file);
    }

    @Override
    public int getVertexRows() {
        return vertexRows;
    }

    @Override
    public int getVertexColumns() {
        return vertexColumns;
    }

    @Override
    public int getChunkRows() {
        return chunkRows;
    }

    @Override
    public int getChunkColumns() {
        return chunkColumns;
    }



//    float[] positions = new float[row*col*VertexData.positionElementCount*4];
//    byte[] colors =      new byte[row*col*VertexData.colorElementCount*4   ];
    @Override
    protected int getChunkRepSizeInBytes() {
        return getChunkColorRepSizeInBytes()+getChunkPositionRepSizeInBytes();
    }

    @Override
    protected int getChunkColorRepSizeInBytes() {
        return getVertexRows()*getVertexColumns()*VertexData.colorElementCount*4;
    }

    @Override
    protected int getChunkPositionRepSizeInBytes() {
        return 4*getVertexRows()*getVertexColumns()*VertexData.positionElementCount*4;
    }

    @Override
    protected float getVertexDistanceHorizontal() {
        return 0.1f;
    }

    @Override
    protected float getVertexDistanceVertical() {
        return 0.1f;
    }

    @Override
    protected float getStartX() {
        return -0.5f;
    }

    @Override
    protected float getStartY() {
        return 0.5f;
    }

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

                float x =getStartX() + 0.1f*i,y=getStartY()-0.1f*j,z=-(.1f*(i+j)),length=getVertexDistanceHorizontal();

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

    public static class VertexData {
        // Vertex data
        private float[] xyzw = new float[] {0f, 0f, 0f, 1f};
        private byte[] rgba = new byte[] {1, 1, 1, 0};

        // Elements per parameter
        public static final int positionElementCount = 4;
        public static final int colorElementCount = 4;


        // Setters
        public void setXYZ(float x, float y, float z) {
            this.setXYZW(x, y, z, 1f);
        }

        public void setRGB(byte r, byte g, byte b) {
            this.setRGBA(r, g, b, (byte)0);
        }

        public void setXYZW(float x, float y, float z, float w) {
            this.xyzw = new float[] {x, y, z, w};
        }

        public void setRGBA(byte r, byte g, byte b, byte a) {
            this.rgba = new byte[] {r, g, b, a};
        }

        // Getters
        public PositionColorPair getElements() {
            float[] flout = new float[VertexData.positionElementCount];
            byte[] bout = new byte[VertexData.colorElementCount];
            int i = 0;

            // Insert XYZW elements
            flout[i++] = this.xyzw[0];
            flout[i++] = this.xyzw[1];
            flout[i++] = this.xyzw[2];
            flout[i] = this.xyzw[3];
            // Insert RGBA elementsv

            i = 0;

            bout[i++] = this.rgba[0];
            bout[i++] = this.rgba[1];
            bout[i++] = this.rgba[2];
            bout[i] = this.rgba[3];

            return new PositionColorPair(flout, bout);
        }



        public class PositionColorPair {
            private float[] positionData;
            private byte[] colorData;

            public PositionColorPair(float[] positionData, byte[] colorData) {
                this.positionData = positionData;
                this.colorData = colorData;
            }

            public float[] getPositionData() {
                return positionData;
            }

            public byte[] getColorData() {
                return colorData;
            }
        }

    }
}
