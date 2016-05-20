package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.Atlas;
import org.supply.simulator.display.renderer.BufferIDContainer;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.Collection;
import java.util.HashMap;

import static java.util.Objects.nonNull;
import static org.supply.simulator.display.renderer.DrawingUtil.*;
import static org.supply.simulator.display.renderer.DrawingUtil.*;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRenderer extends RendererBase implements EntityRenderer {

    boolean drawStatic = false;
    private boolean oneEntityPerBuffer=false;

    protected HashMap dynamIdMap;

    protected HashMap<Entity,BufferIDContainer> staticIdMap;

    protected HashMap<Integer,BufferIDContainer> dynamicIdMap;



    @Override
    protected void buildEntities(Collection<Entity> entityList) {
        if (drawStatic) {
            for (Entity entity : entityList) {
                for (Positions positions : entity.getPositions()) {
                    if (nonNull(positions.getTextureKey())) {





                    }
                }
            }
        } else {







        }
    }

    @Override
    protected void drawEntities(Collection<Entity> entityList) {
//        if(drawStatic) {
//            staticDraw(entityList);
//        } else {
//            dynamicDraw(entityList);
//        }
    }

    @Override
    protected void destroyEntities(Collection<Entity> entities) {

    }



    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }

    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }
}
