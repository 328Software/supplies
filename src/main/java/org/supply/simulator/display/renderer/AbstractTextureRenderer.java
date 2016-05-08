package org.supply.simulator.display.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.data.statistic.entity.UnitPositions;
import org.supply.simulator.data.statistic.entity.impl.BasicUnitPositions;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.assetengine.texture.AtlasType;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alex on 5/6/2016.
 */
public abstract class AbstractTextureRenderer <V extends Entity> extends HasLogger implements  EntityRenderer<V> {



    protected TextureEngine<EntityType> textureEngine;

    protected UnitIndexEngine indexEngine;

    protected int[] locations;


    @Override
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
    public void setIndexEngine(IndexEngine indexEngine) {
        //TODO this casting is not good!
        this.indexEngine=(UnitIndexEngine)indexEngine;

    }
}
