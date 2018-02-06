package org.supply.simulator.data.entity.impl;

import org.apache.commons.collections4.list.FixedSizeList;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 2/6/2018.
 */
public class QuadPositions<V extends Vertex> implements Positions {
    private final List<V> vertices;

    public QuadPositions(V vertex1, V vertex2, V vertex3, V vertex4) {
         vertices = FixedSizeList.fixedSizeList(new ArrayList<>(4));
         vertices.set(0, vertex1);
         vertices.set(0, vertex2);
         vertices.set(0, vertex3);
         vertices.set(0, vertex4);
    }

    @Override
    public float[] getValue() {
        return new float[0];
    }

    @Override
    public boolean isTextured() {
        return false;
    }

    @Override
    public boolean hasColor() {
        return false;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public int[] getVertexAttributeLocations() {
        return new int[0];
    }

    @Override
    public Vertex getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public String getTextureKey() {
        return null;
    }

    @Override
    public int getVertexSize() {
        return 0;
    }
}
