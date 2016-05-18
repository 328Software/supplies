package org.supply.simulator.display.renderer.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;
import org.supply.simulator.util.MapUtils;

import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.HashMap;

import static org.supply.simulator.display.renderer.DrawingUtil.dynamicDraw;
import static org.supply.simulator.display.renderer.DrawingUtil.staticBuild;
import static org.supply.simulator.display.renderer.DrawingUtil.staticDraw;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRenderer extends RendererBase<Entity> implements EntityRenderer<Entity> {

    boolean drawStatic = false;
    boolean oneEntityPerBuffer = false;

    protected void buildEntities(Collection<Entity> entityList) {

        if (drawStatic) {
            staticBuild(entityList);
        }

        //Do nothing for dynamic draw, building is done on the fly!

    }

    protected void drawEntities(Collection<Entity> entityList) {
        if(drawStatic) {
            staticDraw(entityList, VERTEX_SIZE, VERTICES_PER_ENTITY, maxEntities);
        } else {
            dynamicDraw(entityList, VERTEX_SIZE, VERTICES_PER_ENTITY, maxEntities);
        }
    }




    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }
    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }
}
