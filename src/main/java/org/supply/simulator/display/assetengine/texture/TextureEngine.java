package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.display.assetengine.AssetEngine;

/**
 * Created by Alex on 7/13/2014.
 */
public interface TextureEngine<K extends EntityType> extends AssetEngine<K,TextureHandle> {

    @Override
    public TextureHandle get(K key);
}