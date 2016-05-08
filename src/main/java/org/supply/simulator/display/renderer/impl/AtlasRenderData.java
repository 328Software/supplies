package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;

/**
 * Created by Alex on 5/8/2016.
 */
public class AtlasRenderData extends HasLogger {
    private int vertexAttributesId;
    private int positionsArrayId;
    private int textureId;
    private ArrayList<Entity> list;


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

    public AtlasRenderData() {
        list = new ArrayList<>();
    }

    public void add(Entity entity) {
        if (!list.contains(entity)) {
            list.add(entity);
        } else {
            logger.error("Cannot add entity to screen, already rendered");
        }
    }

    public void remove(Entity entity) {
        if (list.contains(entity)) {
            list.remove(entity);
        } else {
            logger.error("Cannot delete entity from screen, not rendered");
        }
    }

    public ArrayList<Entity> getEntityList() {
        return list;
    }

}
