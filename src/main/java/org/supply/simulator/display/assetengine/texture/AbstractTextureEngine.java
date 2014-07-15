package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.logging.HasLogger;

import java.util.HashMap;

/**
 * Created by Alex on 7/15/2014.
 */
public abstract class AbstractTextureEngine<K,V extends TextureHandle> extends HasLogger implements TextureEngine<K,V> {

    protected HashMap<K,TextureHandle> textureDataMap;

    protected AbstractTextureEngine() {
        this.textureDataMap = new HashMap<>();
    }

    @Override
    public TextureHandle get(K key) {

        if (!textureDataMap.containsKey(key)) {
            logger.error("texture not found", key.toString());
        }

        return textureDataMap.get(key);
    }


}
