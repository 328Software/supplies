package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.Collection;

import static org.supply.simulator.display.renderer.DrawingUtil.dynamicDraw;

/**
 * Created by Alex on 5/6/2016.
 */
public class Renderer extends RendererBase<Entity> implements EntityRenderer<Entity> {
    boolean drawStatic = false;

    protected void buildEntities(Collection<Entity> entityList) {
        //DO nothing, Menu's positions a moved into memory on the fly!
    }

    protected void drawEntities(Collection<Entity> entityList) {
        if(drawStatic) {

        } else {
            dynamicDraw(entityList, VERTEX_SIZE, VERTICES_PER_ENTITY, maxEntities);
        }
    }

    protected void setIndicesBufferId() {
        indicesBufferId = ((UnitIndexEngine)indexEngine).get(maxEntities).getIndexId();
    }

    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }
}
