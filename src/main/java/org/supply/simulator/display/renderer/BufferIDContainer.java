package org.supply.simulator.display.renderer;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Alex on 5/8/2016.
 */
public class BufferIDContainer extends HasLogger {

    private int vertexAttributesId;
    private int positionsArrayId;
    private int textureId;

    //list of entities using this id bag to render
    private Set<Positions> posSet;


    public int getTextureId() {
        return textureId;
    }

    public void setTextureId(int textureId) {
        this.textureId = textureId;
    }

    public int getVertexAttributesId() {
        return vertexAttributesId;
    }

    public void setVertexAttributesId(int vertexAttributesId) {
        this.vertexAttributesId = vertexAttributesId;
    }

    public int getPositionsArrayId() {
        return positionsArrayId;
    }

    public void setPositionsArrayId(int positionsArrayId) {
        this.positionsArrayId = positionsArrayId;
    }

    public BufferIDContainer() {
        posSet = new TreeSet<>();
    }

    public void add(Positions positions) {
        posSet.add(positions);
    }

    public void remove(Positions positions) {
        posSet.remove(positions);
    }

    public float[] getValue() {
        int totalSize = posSet.stream().mapToInt(p -> p.getValue().length).sum();

        int current = 0;
        float[] all = new float[totalSize];
        for(Positions positions : posSet) {
            System.arraycopy(positions.getValue(), 0, all, current, positions.getValue().length);
            current += positions.getValue().length;
        }

        return all;
    }

}
