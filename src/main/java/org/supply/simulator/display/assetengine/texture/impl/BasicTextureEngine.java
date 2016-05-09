package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.display.assetengine.texture.AbstractTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;

/**
 * Created by Alex on 5/8/2016.
 */
public class BasicTextureEngine
        extends AbstractTextureEngine<String>
        implements TextureEngine<String> {
    @Override
    protected String lookupTextureFileName(String key) {
        return generateTextureData(key).fileName;
    }

    @Override
    protected float[] lookupTextureSubInfo(String key) {
        return generateTextureData(key).subInfo;
    }

}
