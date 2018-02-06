package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;
import org.supply.simulator.display.renderer.DrawingUtil;

import java.util.Comparator;
import java.util.Map;


/**
 * Created by Alex on 9/14/2014.
 */
public class BasicPositions implements Positions {
    public static final Comparator<Map.Entry<Integer, Integer>> ENTRY_COMPARATOR = Map.Entry.<Integer, Integer>comparingByKey().thenComparing(Map.Entry.<Integer, Integer>comparingByValue());
    public static final int NUMBER_OF_VERTICES = 4;

    private final boolean textured, color;
    private Long id;
    private final float[] value;
    private final Vertex[] vertices;

    private String textureKey;

    public static BasicPositions newTexturedPositions(float[] data) {
        return new BasicPositions(true, false, data);
    }

    public static BasicPositions newColorPositions(float[] data) {
        return new BasicPositions(false, true, data);
    }

    public static BasicPositions newTexturedColorPositions(float[] data) {
        return new BasicPositions(true, true, data);
    }

    public static BasicPositions newTexturedPositions() {
        return new BasicPositions(true, false, null);
    }

    public static BasicPositions newColorPositions() {
        return new BasicPositions(false, true, null);
    }

    public static BasicPositions newTexturedColorPositions() {
        return new BasicPositions(true, true, null);
    }


    private BasicPositions(boolean textured, boolean color, float[] data) {
        this.textured = textured;
        this.color = color;

        int vertexSize = getVertexSize();

        if(data == null) {
            value = new float[vertexSize * NUMBER_OF_VERTICES];
        } else {
            if (data.length != vertexSize * NUMBER_OF_VERTICES) {
                System.out.println("data length is " + data.length);
                System.out.println(vertexSize * NUMBER_OF_VERTICES);
                throw new RuntimeException();
            }
            value = data;
        }

        vertices = new Vertex[NUMBER_OF_VERTICES];
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            vertices[i] = new BasicVertex(this, i);
        }
    }

    @Override
    public boolean isTextured() {
        return textured;
    }

    @Override
    public boolean hasColor() {
        return color;
    }

    @Override
    public Vertex getVertex(int index) {
        return vertices[index];
    }

    @Override
    public float[] getValue() {
        return value;
    }

    public void setValue(float[] value) {
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    @Override
    public Long getId() {
        return id;
    }

//    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTextureKey() {
        return textureKey;
    }

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }

}
