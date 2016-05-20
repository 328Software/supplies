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

/**
 * Created by Alex on 5/6/2016.
 */
public abstract class RendererBase extends HasLogger implements  EntityRenderer {

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


    protected HashMap<Atlas,OpenGLBufferIDBag<Entity>> idMap;


    public RendererBase() {
        super();
        idMap = new HashMap<>();
        rows=20;
        columns=20;
    }



    @Override
    public void build(Collection<Entity> entities) {
        // Create Indices Buffer, uses maxEntities to determine size
        if (indicesBufferId < 0) {
            indicesBufferId = indexEngine.get(MapUtils.newEntry(rows,columns)).getIndexId();
        }

        buildEntities(entities);

    }

    //TODO currently not using input to this method
    @Override
    public void render(Collection<Entity> entities) {
        drawEntities(entities);
    }

    @Override
    public void destroy(Collection<Entity> entities) {
        destroyEntities(entities);

    }

    protected abstract void destroyEntities(Collection<Entity> entities);

    @Override
    public void destroyAll() {
        //TODO fill out auto-generated whatever

    }


    protected abstract void buildEntities(Collection<Entity> entityList);
    protected abstract void drawEntities(Collection<Entity> entityList);


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
