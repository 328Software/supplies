package org.supply.simulator.display.renderer.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.renderer.RendererBase;
import org.supply.simulator.display.renderer.EntityRenderer;

import java.nio.FloatBuffer;
import java.util.Collection;

/**
 * Created by Alex on 7/21/2014.
 */
public class BasicUnitRenderer extends RendererBase<BasicUnit> implements EntityRenderer<BasicUnit> {
    @Override
    protected void buildEntities(Collection<BasicUnit> entityList) {
        //DO nothing, Unit's positions a moved into memory on the fly!
    }

    @Override
    protected void drawEntities(Collection<BasicUnit> entityList) {
        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(VERTEX_SIZE * max_entities);

        for (Entity entity : entityList) {
            //TODO we really need to combine unit and chunk positions to be able to clean this up
            if (entity instanceof Menu) {
                verticesFloatBuffer.put(((Menu)entity).getPositions().getValue());
            } else if (entity instanceof Unit) {
                verticesFloatBuffer.put(((Unit)entity).getPositions().getValue());
            } else {
                logger.error("INVALID entity type");
            }
        }
        verticesFloatBuffer.flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_DYNAMIC_DRAW);


        GL11.glDrawElements(GL11.GL_TRIANGLES, //render mode i.e. what kind of primitives are we constructing our image out of
                VERTICES_PER_ENTITY * entityList.size(), //Number of vertices to render (there's 6 per image)
                GL11.GL_UNSIGNED_INT, //indicates the type of index values in indices
                VERTICES_PER_ENTITY * Integer.SIZE * 0);//index into buffer when to start rendering

    }

    @Override
    protected void setIndicesBufferId() {
        indicesBufferId = ((UnitIndexEngine)indexEngine).get(max_entities).getIndexId();
    }
}
