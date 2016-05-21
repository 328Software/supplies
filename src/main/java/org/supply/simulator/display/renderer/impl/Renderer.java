package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.Atlas;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.Collection;

import static java.util.Objects.nonNull;
import static org.supply.simulator.display.renderer.DrawingUtil.*;
import static org.supply.simulator.display.renderer.DrawingUtil.disableTextureBuffer;

/**
 * Created by Alex on 5/6/2016.
 */
public class Renderer extends RendererBase implements EntityRenderer {

    boolean drawStatic = false;
    boolean oneEntityPerBuffer = false;

    protected void buildEntities(Collection<Entity> entities) {

        //Load texture atlases
        for (Entity entity : entities) {

            if (nonNull(entity.getAtlas())) {
                //Entity is textured;
                Atlas atlas = entity.getAtlas();

                if (!idMap.containsKey(atlas)) {
                    OpenGLBufferIDBag openGLBufferIDBag = allocateOpenGLBuffers(atlas, locations);

                    openGLBufferIDBag.add(entity);

                    idMap.put(atlas, openGLBufferIDBag);
                } else {
                    idMap.get(atlas).add(entity);
                }
            } else {
                //Entity is NOT textured;

            }
        }
    }

    protected void drawEntities(Collection<Entity> entities) {

        for (OpenGLBufferIDBag<Entity> data : idMap.values()) {

            //Prepare to draw block of entities
            //    i.e. bind all the opengl buffers, bind texture
            enableArrayBuffer(data.getPositionsArrayId());
            enableVertexAttribArray(locations, data.getVertexAttributesId());
            enableIndicesBuffer(staticIndicesBufferId);

            if (nonNull(data.getTextureId())) {
                enableTextureBuffer(data.getTextureId());
            }

            //Draw Entities
            if(drawStatic) {
//                staticDraw(entities);
            } else {
                dynamicDraw(entities);
            }

            //Finish drawing block of entities
            //    i.e. unbind all the opengl buffers, unbind texture
            disableVertexAttribArray(locations);
            disableIndicesBuffer();
            disableArrayBuffer();
            disableTextureBuffer();

        }

    }

    protected void destroyEntities(Collection<Entity> entities) {
        for (Entity entity : entities) {
            for(Positions positions : entity.getPositions()) {
                textureEngine.done(positions.getTextureKey());
            }
//            idMap2.get(entity.getTextureKey()).remove(entity);
            //TODO when to delete atlas data??
        }
    }


//    protected void setIndicesBufferId() {
//
//
//        staticIndicesBufferId = (indexEngine).get(MapUtils.newEntry(maxEntities,1)).getIndexId();
//    }

    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }
    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }
}
