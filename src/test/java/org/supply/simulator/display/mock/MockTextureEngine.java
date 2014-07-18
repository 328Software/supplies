package org.supply.simulator.display.mock;

import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public class MockTextureEngine implements TextureEngine<Integer>{
    @Override
    public TextureHandle get(Integer key) {
        return null;
    }
}
