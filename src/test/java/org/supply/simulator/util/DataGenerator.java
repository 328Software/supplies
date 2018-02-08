package org.supply.simulator.util;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.supply.simulator.badengine.graph.impl.BasicEdgeBuilder;
import org.supply.simulator.badengine.graph.impl.BasicNodeFactory;
import org.supply.simulator.data.entity.Colors;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.*;
import org.supply.simulator.display.assetengine.texture.FontTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.factory.TexturedVertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.supply.simulator.display.factory.TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE;

/**NOTE
 * NOTE (2/4/2018): Moving this to badengine from test because of weird test cannot fine method error
 * NOTE     -Alex
 *
 *
 *
 *
 * Created by Alex on 9/10/2014.
 */
public class DataGenerator {

    TextureEngine textureEngine;
//    private HashMap<String,UnitType> unitTypeMap;
//    private HashMap<String,MenuType> menuTypeMap;
//    private HashMap<int[],ChunkType> chunkTypeMap;

    public DataGenerator () {
//        unitTypeMap = new HashMap<>();
//        menuTypeMap = new HashMap<>();
//        chunkTypeMap = new HashMap<>();
    }

    public BasicChunk createChunk(int chunkRows, int chunkColumns, int offsetX, int offsetY) {
        BasicChunk chunk = new BasicChunk();



//        ChunkType type;
//
//        //CANTOR PAIRING FUNCTION
//        //  Ensures unique key from two numbers
//        double key = .5*(chunkRows+chunkColumns)*(chunkRows+chunkColumns+1)+chunkColumns;

//        ChunkType type;
//        int[] key = new int[]{chunkRows,chunkColumns};
/*
        if (chunkTypeMap.containsKey(key)) {
            type =  chunkTypeMap.get(key);
        } else {
            type = new BasicChunkType();
            type.setColumns(chunkColumns);
            type.setRows(chunkRows);
            chunkTypeMap.put(key,type);
        }*/

        ChunkData pair = getChunkData(chunkRows, chunkColumns, offsetX, offsetY);
        Positions positions = BasicPositions.newTexturedColorPositions(pair.positions);
        Colors colors = new Colors();

//        positions.setValue(pair.positions);
        colors.setValue(pair.colors);




//        chunk.setType(type);
        chunk.setPositions(Collections.singleton(positions));
        chunk.setColors(colors);

        return chunk;
    }

    public static Graph<Node,Edge> fiveNodeGraph() {
        TextureEngine textureEngine = new FontTextureEngine();
        Graph<Node, Edge> g = new SimpleWeightedGraph<>(BasicEdge.class);
        BasicNodeFactory nodeFactory = new BasicNodeFactory();
        nodeFactory.setTextureEngine(textureEngine);
        BasicEdgeBuilder edgeFactory = new BasicEdgeBuilder();


        // MAKE NODES
        /////////////
        List<Node> nodes = nodeFactory.createVertex(5);
        nodes.forEach(n->g.addVertex(n));
        nodes.forEach(n->n.getName());

        // MAKE EDGES
        /////////////
        // 0 -> 1
        edgeFactory.setSource((BasicNode)nodes.get(0));
        edgeFactory.setTarget((BasicNode)nodes.get(1));
        Edge e = edgeFactory.build();
        g.addEdge(nodes.get(0),nodes.get(1),e);
        g.setEdgeWeight(e,5.0);

        // 0 -> 2
        edgeFactory.setSource((BasicNode)nodes.get(0));
        edgeFactory.setTarget((BasicNode)nodes.get(2));
        e = edgeFactory.build();
        g.addEdge(nodes.get(0),nodes.get(2),e);
        g.setEdgeWeight(e,2.0);

        // 1 -> 3
        edgeFactory.setSource((BasicNode)nodes.get(1));
        edgeFactory.setTarget((BasicNode)nodes.get(3));
        e = edgeFactory.build();
        g.addEdge(nodes.get(1),nodes.get(3),e);
        g.setEdgeWeight(e,0.5);

        // 1 -> 4
        edgeFactory.setSource((BasicNode)nodes.get(1));
        edgeFactory.setTarget((BasicNode)nodes.get(4));
        e = edgeFactory.build();
        g.addEdge(nodes.get(1),nodes.get(4),e);
        g.setEdgeWeight(e,1.0);

        return  g;
    }

    public static List<BasicNode> threeNodes () {
        return DataGenerator.threeNodes("v");
    }

    public static List<BasicNode>  threeNodes(String v) {
        List<BasicNode> l = new ArrayList<>();

        Positions p1 = getUnitPositions(0.0f, 0.8f, 0f, .25f, .25f);

        Positions p2= getUnitPositions(-0.5f, .0f, 0f, .25f, .25f);

        Positions p3 = getUnitPositions(.5f, .0f, 0f, .25f, .25f);

        BasicNode v1 = new BasicNode();
        v1.setName(v+"1");
        v1.setPositions(Collections.singleton(p1));
        l.add(v1);

        BasicNode v2 = new BasicNode();
        v2.setName(v+"2");
        v2.setPositions(Collections.singleton(p2));
        l.add(v2);

        BasicNode v3 = new BasicNode();
        v3.setName(v+"3");
        v3.setPositions(Collections.singleton(p3));
        l.add(v3);

        return l;
    }


    public BasicUnit createUnit(float topLeftX, float topLeftY, float topLeftZ, float length, float width, String name) {
        BasicUnit unit = new BasicUnit();
        BasicPositions positions = getUnitPositions(topLeftX, topLeftY, topLeftZ, length, width);
        positions.setTextureKey(name);
        unit.setPositions(Collections.singleton(positions));

        TextureUtils.oldApplyTexture(unit, textureEngine);

        return unit;
    }

    private static BasicPositions getUnitPositions(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        TexturedVertex v0 = new TexturedVertex();
        TexturedVertex v1 = new TexturedVertex();
        TexturedVertex v2 = new TexturedVertex();
        TexturedVertex v3 = new TexturedVertex();
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(0, 0, 0); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(0, 0, 0); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(0, 0, 0); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(0, 0, 0); v3.setST(1, 0);

        float[] data = new float[4* TEXTURE_VERTEX_TOTAL_SIZE];

        System.arraycopy(v0.getElements(),0,data, 0,TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE);
        System.arraycopy(v1.getElements(),0,data, TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE,TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE);
        System.arraycopy(v2.getElements(),0,data,2*TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE,TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE);
        System.arraycopy(v3.getElements(),0,data,3*TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE,TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE);

        BasicPositions entityData = BasicPositions.newTexturedColorPositions(data);
//        entityData.setValue(data);

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

    public static Graph<BasicNode, BasicEdge> createGraph()
    {
        BasicEdge e1;
        BasicEdge e2;
        BasicEdge e3;

        BasicNode v1;
        BasicNode v2;
        BasicNode v3;
        BasicEdge k1;
        BasicEdge k2;
        BasicEdge k3;

        BasicNode n1;
        BasicNode n2;
        BasicNode n3;
        //System.out.println("hey");
        Graph<BasicNode, BasicEdge> g = new SimpleWeightedGraph<>(BasicEdge.class);

//        g.



        List<BasicNode> nodes = DataGenerator.threeNodes("n");
        List<BasicNode> nodes2 = DataGenerator.threeNodes("n");

        nodes.stream().forEach(v ->  g.addVertex(v));
        nodes2.stream().forEach(v ->  g.addVertex(v));
//        nodes.stream().forEach(v ->  System.out.println(v.getName()));
        e1 = new BasicEdge();
        e1.setName("e1");
        e2 = new BasicEdge();
        e2.setName("e2");
        e3 = new BasicEdge();
        e3.setName("e3");

        v1 = nodes.get(0);
        v2 = nodes.get(1);
        v3 = nodes.get(2);

        g.addEdge(v1,v2,e1);
        g.setEdgeWeight(e1,5.0);
//        g.addEdge(v2,v3,e2);
//        g.setEdgeWeight(e2,10.0);
        g.addEdge(v1,v3,e3);
        g.setEdgeWeight(e3,2.0);

        k1 = new BasicEdge();
        k1.setName("k1");
        k2 = new BasicEdge();
        k2.setName("k2");
        k3 = new BasicEdge();
        k3.setName("k3");

        n1 = nodes2.get(0);
        n2 = nodes2.get(1);
        n3 = nodes2.get(2);

        g.addEdge(n1,n2,k1);
        g.setEdgeWeight(k1,0.5);
//        g.addEdge(v2,v3,e2);
//        g.setEdgeWeight(e2,10.0);
        g.addEdge(n1,n3,k3);
        g.setEdgeWeight(k3,1.0);

        g.addEdge(v3,n1,k2);
        g.setEdgeWeight(k2,3);

        return g;
//    return null;
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


    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
