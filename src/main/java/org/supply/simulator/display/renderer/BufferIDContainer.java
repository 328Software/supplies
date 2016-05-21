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
//    private int textureId;

    //list of entities using this id bag to render


//
//    public int getTextureId() {
//        return textureId;
//    }
//
//    public void setTextureId(int textureId) {
//        this.textureId = textureId;
//    }

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



}
