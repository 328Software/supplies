package org.supply.simulator.display.extra;

import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.attribute.entity.impl.BasicChunkType;
import org.supply.simulator.data.attribute.entity.impl.BasicMenuType;
import org.supply.simulator.data.attribute.entity.impl.BasicUnitType;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.data.entity.impl.BasicMenu;
import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.data.statistic.entity.UnitPositions;
import org.supply.simulator.data.statistic.entity.impl.BasicUnitPositions;
import org.supply.simulator.display.mock.data.MockChunkColors;
import org.supply.simulator.display.mock.data.MockChunkPositions;

import java.util.HashMap;

/**
 * Created by Alex on 9/10/2014.
 */
public class DataGenerator {

    private HashMap<String,EntityType> typeMap;
    public DataGenerator () {
        typeMap = new HashMap<>();
    }

    public BasicChunk createChunk(int chunkRows, int chunkColumns, int offsetX, int offsetY) {
        //TODO store chunktype in map too?
        BasicChunkType type = new BasicChunkType();
        type.setColumns(chunkColumns);
        type.setRows(chunkRows);

        ChunkData pair = getChunkData(chunkRows, chunkColumns, offsetX, offsetY);
        MockChunkPositions positions = new MockChunkPositions();
        MockChunkColors colors = new MockChunkColors();

        positions.setValue(pair.positions);
        colors.setValue(pair.colors);


        BasicChunk chunk = new BasicChunk();
        chunk.setType(type);
        chunk.setChunkPositions(positions);
        chunk.setChunkColors(colors);

        return chunk;
    }

    public BasicUnit createUnit(float topLeftX, float topLeftY, float topLeftZ, float length, float width, String name) {
        BasicUnit unit = new BasicUnit();
        unit.setUnitPositions(getUnitPositions(topLeftX, topLeftY, topLeftZ,  length,  width));

        UnitType type;
        if (typeMap.containsKey(name)) {
            type = (UnitType)typeMap.get(name);
        } else {
            type = new BasicUnitType();
            type.setName(name);
            typeMap.put(name,type);
        }

        unit.setType(type);
        return unit;
    }

    public BasicUnit createUnit(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        return createUnit( topLeftX, topLeftY, topLeftZ,  length,  width,"textures/alexsface.png");
    }

    public BasicMenu createMenu(float topLeftX, float topLeftY, float topLeftZ, float length, float width, String name) {
        BasicMenu menu = new BasicMenu();
        menu.setPositions(getUnitPositions(topLeftX, topLeftY, topLeftZ, length, width));
        MenuType type;

        if (typeMap.containsKey(name)) {
            type = (BasicMenuType)typeMap.get(name);
        } else {
            type = new BasicMenuType();
            type.setName(name);
            typeMap.put(name,type);
        }

        //TODO WHY do I have to cast it to EntityType?
        menu.setType((EntityType)type);

        return menu;
    }

    private UnitPositions getUnitPositions(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        TexturedVertex v0 = new TexturedVertex();
        TexturedVertex v1 = new TexturedVertex();
        TexturedVertex v2 = new TexturedVertex();
        TexturedVertex v3 = new TexturedVertex();
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(1, 0, 0); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(0, 1, 0); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(0, 0, 1); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        float[] data = new float[4*TexturedVertex.elementCount];

        System.arraycopy(v0.getElements(),0,data,0*TexturedVertex.elementCount,TexturedVertex.elementCount);
        System.arraycopy(v1.getElements(),0,data,1*TexturedVertex.elementCount,TexturedVertex.elementCount);
        System.arraycopy(v2.getElements(),0,data,2*TexturedVertex.elementCount,TexturedVertex.elementCount);
        System.arraycopy(v3.getElements(),0,data,3*TexturedVertex.elementCount,TexturedVertex.elementCount);

        UnitPositions entityData = new BasicUnitPositions();
        entityData.setValue(data);

        return entityData;
    }

    private ChunkData getChunkData
            (int row, int col, int topLeftX, int topLeftY) {

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

        return new ChunkData(positions,colors);
    }

    private class ChunkData {
        public float[] positions;
        public byte[] colors;

        public ChunkData(float[] positions, byte[] colors) {
            this.positions=positions;
            this.colors=colors;
        }
    }

//    private TextureData getTextureData(String name) {
//        TextureData type  = new TextureData();
//        String fileName = "";
//
//
//        switch (name) {
//            case "1":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,11,20};
//                break;
//            case "2":fileName="textures/text2.png";
//                type.subInfo = new float[]{12,0,23,20};
//                break;
//            case "3":fileName="textures/text2.png";
//                type.subInfo = new float[]{24,0,35,20};
//                break;
//            case "4":fileName="textures/text2.png";
//                type.subInfo = new float[]{36,0,47,20};
//                break;
//            case "5":fileName="textures/text2.png";
//                type.subInfo = new float[]{48,0,59,20};
//                break;
//            case "6":fileName="textures/text2.png";
//                type.subInfo = new float[]{60,0,71,20};
//                break;
//            case "7":fileName="textures/text2.png";
//                type.subInfo = new float[]{72,0,83,20};
//                break;
//            case "8":fileName="textures/text2.png";
//                type.subInfo = new float[]{84,0,95,20};
//                break;
//            case "9":fileName="textures/text2.png";
//                type.subInfo = new float[]{96,0,107,20};
//                break;
//            case "0":fileName="textures/text2.png";
//                type.subInfo = new float[]{108,0,119,20};
//                break;
//            case "A":fileName="textures/text2.png";
//                type.subInfo = new float[]{120,0,131,20};
//                break;
//            case "B":fileName="textures/text2.png";
//                type.subInfo = new float[]{132,0,143,20};
//                break;
//            case "C":fileName="textures/text2.png";
//                type.subInfo = new float[]{144,0,155,20};
//                break;
//            case "D":fileName="textures/text2.png";
//                type.subInfo = new float[]{156,0,167,20};
//                break;
//            case "E":fileName="textures/text2.png";
//                type.subInfo = new float[]{168,0,179,20};
//                break;
//            case "F":fileName="textures/text2.png";
//                type.subInfo = new float[]{180,0,191,20};
//                break;
//            case "G":fileName="textures/text2.png";
//                type.subInfo = new float[]{192,0,203,20};
//                break;
//            case "H":fileName="textures/text2.png";
//                type.subInfo = new float[]{204,0,215,20};
//                break;
//            case "I":fileName="textures/text2.png";
//                type.subInfo = new float[]{216,0,227,20};
//                break;
//            case "J":fileName="textures/text2.png";
//                type.subInfo = new float[]{228,0,239,20};
//                break;
//            case "K":fileName="textures/text2.png";
//                type.subInfo = new float[]{240,0,251,20};
//                break;
//            case "L":fileName="textures/text2.png";
//                type.subInfo = new float[]{252,0,263,20};
//                break;
//            case "M":fileName="textures/text2.png";
//                type.subInfo = new float[]{264,0,275,20};
//                break;
//            case "N":fileName="textures/text2.png";
//                type.subInfo = new float[]{276,0,287,20};
//                break;
//            case "O":fileName="textures/text2.png";
//                type.subInfo = new float[]{288,0,299,20};
//                break;
//            case "P":fileName="textures/text2.png";
//                type.subInfo = new float[]{300,0,311,20};
//                break;
//            case "Q":fileName="textures/text2.png";
//                type.subInfo = new float[]{312,0,323,20};
//                break;
//            case "R":fileName="textures/text2.png";
//                type.subInfo = new float[]{324,0,335,20};
//                break;
//            case "S":fileName="textures/text2.png";
//                type.subInfo = new float[]{336,0,347,20};
//                break;
//            case "T":fileName="textures/text2.png";
//                type.subInfo = new float[]{348,0,359,20};
//                break;
//            case "U":fileName="textures/text2.png";
//                type.subInfo = new float[]{360,0,371,20};
//                break;
//            case "V":fileName="textures/text2.png";
//                type.subInfo = new float[]{372,0,383,20};
//                break;
//            case "W":fileName="textures/text2.png";
//                type.subInfo = new float[]{384,0,395,20};
//                break;
//            case "X":fileName="textures/text2.png";
//                type.subInfo = new float[]{396,0,407,20};
//                break;
//            case "Y":fileName="textures/text2.png";
//                type.subInfo = new float[]{408,0,419,20};
//                break;
//            case "Z":fileName="textures/text2.png";
//                type.subInfo = new float[]{420,0,431,20};
//                break;
//            case "a":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "b":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "c":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "d":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "e":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "f":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "g":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "h":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "i":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "j":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "k":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "l":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "m":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "n":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "o":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "p":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,20};
//                break;
//            case "q":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "r":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "s":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "t":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "u":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "v":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "w":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "x":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "y":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case "z":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,0,40};
//                break;
//            case " ":fileName="textures/text2.png";
//                type.subInfo = new float[]{492,21,503,40};
//                break;
//            case "textures/text2.png":fileName="textures/text2.png";
//                type.subInfo = new float[]{0,0,640,400};
//                break;
//            case "textures/text.png":fileName="textures/text.png";
//                type.subInfo = new float[]{0,0,640,400};
//                break;
//            case "textures/alexsface.png":fileName="textures/alexsface.png";
//                type.subInfo = new float[]{0,0,500,500};
//                break;
//
//
//
//            default:
//                break;
//        }
////        type.subInfo[0] = type.subInfo[0]/640;
////        type.subInfo[1] = type.subInfo[1]/400;
////        type.subInfo[2] = type.subInfo[2]/640;
////        type.subInfo[3] = type.subInfo[3]/400;
//
//        if (textureMap.containsKey(fileName)) {
//            type.textureType= textureMap.get(fileName);
//        } else {
//            type.textureType= new MockTextureType();
//            type.textureType.setFileName(fileName);
//            textureMap.put(fileName,type.textureType);
//        }
//
//        return type;
//    }
//
//
//    private class TextureData {
//        public MockTextureType textureType;
//        public float[] subInfo;
//    }
}
