package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.display.assetengine.AssetEngine;

/**
 * Created by Brandon on 5/20/2016.
 *
 * todo generify generics
 */
public interface TextureEngine extends AssetEngine<String, TextureHandle> {
    boolean canHandle(String key);
}
