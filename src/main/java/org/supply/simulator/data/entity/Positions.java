package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;
import org.supply.simulator.display.renderer.DrawingUtil;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Alex on 9/14/2014.
 */
public class Positions implements HasId<Long>, HasValue<float[]> {
    public static final Comparator<Map.Entry<Integer, Integer>> ENTRY_COMPARATOR = Map.Entry.<Integer, Integer>comparingByKey().thenComparing(Map.Entry.<Integer, Integer>comparingByValue());
    public static final int NUMBER_OF_VERTICES = 4;

    private final boolean textured, color;
    private Long id;
    private final float[] value;
    private final Vertex[] vertices;

    private final int vertexSize;

    private String textureKey;

    public static Positions newTexturedPositions(float[] data) {
        return new Positions(true, false, data);
    }

    public static Positions newColorPositions(float[] data) {
        return new Positions(false, true, data);
    }

    public static Positions newTexturedColorPositions(float[] data) {
        return new Positions(true, true, data);
    }

    public static Positions newTexturedPositions() {
        return new Positions(true, false, null);
    }

    public static Positions newColorPositions() {
        return new Positions(false, true, null);
    }

    public static Positions newTexturedColorPositions() {
        return new Positions(true, true, null);
    }


    private Positions(boolean textured, boolean color, float[] data) {
        this.textured = textured;
        this.color = color;

        new TreeMap<Map.Entry<Integer, Integer>, Integer>(ENTRY_COMPARATOR);

        vertexSize = getVertexSize(textured, color);

        if(data == null) {
            value = new float[vertexSize * NUMBER_OF_VERTICES];
        } else {
            if (data.length != vertexSize * NUMBER_OF_VERTICES) {
                throw new RuntimeException();
            }
            value = data;
        }

        vertices = new Vertex[NUMBER_OF_VERTICES];
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            vertices[i] = new Vertex(this, i);
        }
    }

    public boolean isTextured() {
        return textured;
    }

    public boolean hasColor() {
        return color;
    }

    public int[] getVertexAttributeLocations () {
        int[] locations=null;
        if (this.isTextured()&&this.hasColor()) {
            locations=new int[]{0,1,2};
        } else if (this.isTextured()||this.hasColor()){
            locations=new int[]{0,1};
        }
        return locations;
    }

    public Vertex getVertex(int index) {
        return vertices[index];
    }

    @Override
    public float[] getValue() {
        return value;
    }

//    public void setValue(float[] value) {
//        this.value = value;
//    }

    @Override
    public Long getId() {
        return id;
    }

//    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTextureKey() {
        return textureKey;
    }

    public int getVertexSize() {
        return vertexSize;
    }

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }

    private int getVertexSize(boolean textured, boolean colored) {
        return DrawingUtil.POSITION_ELEMENT_COUNT +
                (colored ? DrawingUtil.COLOR_ELEMENT_COUNT : 0) + (textured ? DrawingUtil.TEXTURE_ELEMENT_COUNT : 0);
    }


}
