package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;
import org.supply.simulator.display.renderer.DrawingUtil;

/**
 * Created by Brandon on 2/6/2018.
 */
public interface Positions extends HasId<Positions, Long>, HasValue<float[]> {
    boolean isTextured();

    boolean hasColor();

    Vertex getVertex(int index);

    String getTextureKey();

    default int[] getVertexAttributeLocations() {
        int[] locations = null;
        if (this.isTextured() && this.hasColor()) {
            locations = new int[]{0, 1, 2};
        } else if (this.isTextured() || this.hasColor()) {
            locations = new int[]{0, 1};
        }
        return locations;
    }

    default int getVertexSize() {
        return DrawingUtil.POSITION_ELEMENT_COUNT +
                (hasColor() ? DrawingUtil.COLOR_ELEMENT_COUNT : 0) + (isTextured() ? DrawingUtil.TEXTURE_ELEMENT_COUNT : 0);
    }
}
