package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.Collection;

import static org.supply.simulator.display.renderer.DrawingUtil.*;

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
            staticDraw(entityList);
        } else {
            dynamicDraw(entityList);
        }
    }




    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }
    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }
}
