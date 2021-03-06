package org.supply.simulator.display.renderer;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.logging.HasLogger;
import org.supply.simulator.util.MapUtils;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alex on 5/6/2016.
 */
public abstract class RendererBase extends HasLogger implements EntityRenderer {

    protected TextureEngine textureEngine;

    protected BasicIndexEngine indexEngine;



    protected int[] locations;

    //////////
    //THESE THREE NEED TO STAY TOGETHER
    // staticIndicesBufferId = indexEngine.get(MapUtils.newEntry(rows,columns))
    //TODO get this outta here
    protected int staticIndicesBufferId = -1;
    protected int rows;
    protected int columns;
    //////////

    //////////
    //THESE TWO NEED TO STAY TOGETHER
    // dynamicIndicesBufferId = indexEngine.get(MapUtils.newEntry(1,max_positions_per_buffer))
    //TODO get this outta here
    protected int dynamicIndicesBufferId = -1;
    protected int max_positions_per_buffer;
    //////////



    public RendererBase() {
        super();
        rows=20;
        columns=20;
        max_positions_per_buffer=1000;
    }



    @Override
    public void build(Collection<Entity> entities) {
        // Create Indices Buffer, uses maxEntities to determine size
        if (staticIndicesBufferId < 0) {
            staticIndicesBufferId = indexEngine.get(MapUtils.newEntry(rows,columns)).getIndexId();
        }

        if (dynamicIndicesBufferId < 0) {
            dynamicIndicesBufferId = indexEngine.get(MapUtils.newEntry(1,max_positions_per_buffer)).getIndexId();
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


    public void setTextureEngine(TextureEngine textureEngine) {
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
