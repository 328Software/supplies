package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;

/**
 * Created by Brandon on 2/5/2018.
 */
public final class PositionsBackedVertex extends AbstractVertex {
    private Positions positions;
    private final int index;

    public PositionsBackedVertex(Positions positions, int index) {
        this.positions = positions;
        this.index = index;
    }

    @Override
    public int getSize() {
        return positions.getVertexSize();
    }

    @Override
    protected boolean hasColor() {
        return positions.hasColor();
    }

    @Override
    protected boolean isTextured() {
        return positions.isTextured();
    }

    @Override
    protected float[] getValue() {
        return positions.getValue();
    }

    @Override
    protected int getOffset() {
        return index * getSize();
    }
}
