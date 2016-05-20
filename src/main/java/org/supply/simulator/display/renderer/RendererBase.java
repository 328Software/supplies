package org.supply.simulator.display.renderer;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.texture.Atlas;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.renderer.impl.OpenGLBufferIDBag;
import org.supply.simulator.logging.HasLogger;
import org.supply.simulator.util.MapUtils;

import java.util.Collection;
import java.util.HashMap;

import static java.util.Objects.nonNull;
import static org.supply.simulator.display.renderer.DrawingUtil.*;

/**
 * Created by Alex on 5/6/2016.
 */
public abstract class RendererBase<V extends Entity> extends HasLogger implements  EntityRenderer<V> {

    protected BasicTextureEngine textureEngine;

    protected BasicIndexEngine indexEngine;



    protected int[] locations;

    //////////
    //THESE THREE NEED TO STAY TOGETHER
    // indicesBufferId = indexEngine.get(MapUtils.newEntry(rows,columns))
    //TODO get this outta here
    protected int indicesBufferId = -1;
    protected int rows;
    protected int columns;
    //////////


    protected HashMap<Atlas,OpenGLBufferIDBag<V>> idMap;


    public RendererBase() {
        super();
        idMap = new HashMap<>();
        rows=20;
        columns=20;
    }



    @Override
    public void build(Collection<V> entities) {
        // Create Indices Buffer, uses maxEntities to determine size
        if (indicesBufferId < 0) {
            indicesBufferId = indexEngine.get(MapUtils.newEntry(rows,columns)).getIndexId();
        }

        // Do any OpenGL pre-work before rendering
        buildEntities(entities);

        //Load texture atlases
        for (V entity : entities) {

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

    //TODO currently not using input to this method
    @Override
    public void render(Collection<V> entities) {
        for (OpenGLBufferIDBag<V> data : idMap.values()) {

            //Prepare to draw block of entities
            //    i.e. bind all the opengl buffers, bind texture
            enableArrayBuffer(data.getPositionsArrayId());
            enableVertexAttribArray(locations, data.getVertexAttributesId());
            enableIndicesBuffer(indicesBufferId);

            if (nonNull(data.getTextureId())) {
                enableTextureBuffer(data.getTextureId());
            }

            //Draw Entities
            drawEntities(data.getEntityList());

            //Finish drawing block of entities
            //    i.e. unbind all the opengl buffers, unbind texture
            disableVertexAttribArray(locations);
            disableIndicesBuffer();
            disableArrayBuffer();
            disableTextureBuffer();

        }
    }

    @Override
    public void destroy(Collection<V> entities) {
        for (V entity : entities) {
            for(Positions positions : entity.getPositions()) {
                textureEngine.done(positions.getTextureKey());
            }
//            idMap2.get(entity.getTextureKey()).remove(entity);
            //TODO when to delete atlas data??
        }

    }

    @Override
    public void destroyAll() {
        //TODO fill out auto-generated whatever

    }


    protected abstract void buildEntities(Collection<V> entityList);
    protected abstract void drawEntities(Collection<V> entityList);


    public void setTextureEngine(BasicTextureEngine textureEngine) {
        this.textureEngine =  textureEngine;
    }

    @Override
    public void setAttributeLocations(int[] locations) {
        this.locations=locations;
    }

    @Override
    public int[] getAttributeLocations() {
        return locations;
    }

    @Override
    public void setIndexEngine(BasicIndexEngine indexEngine) {
        this.indexEngine=indexEngine;

    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
