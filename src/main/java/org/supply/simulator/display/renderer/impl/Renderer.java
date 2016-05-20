package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.Collection;

import static org.supply.simulator.display.renderer.DrawingUtil.dynamicDraw;
import static org.supply.simulator.display.renderer.DrawingUtil.staticDraw;

/**
 * Created by Alex on 5/6/2016.
 */
public class Renderer extends RendererBase<Entity> implements EntityRenderer<Entity> {

    boolean drawStatic = false;
    boolean oneEntityPerBuffer = false;

    protected void buildEntities(Collection<Entity> entityList) {
        //DO nothing, Menu's positions a moved into memory on the fly!
    }

    protected void drawEntities(Collection<Entity> entityList) {
        if(drawStatic) {
            staticDraw(entityList);
        } else {
            dynamicDraw(entityList);
        }
    }



//    protected void setIndicesBufferId() {
//
//
//        indicesBufferId = (indexEngine).get(MapUtils.newEntry(maxEntities,1)).getIndexId();
//    }

    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }
    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }
}
