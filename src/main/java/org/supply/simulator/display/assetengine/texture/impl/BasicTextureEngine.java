package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.data.attribute.entity.TexturedEntityType;
import org.supply.simulator.display.assetengine.texture.AbstractTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;

/**
 * Created by Alex on 7/13/2014.
 */
public class BasicTextureEngine
        extends AbstractTextureEngine<TexturedEntityType>
        implements TextureEngine<TexturedEntityType> {
    @Override
    protected String getAtlasFileName(TexturedEntityType key) {
        return key.getTextureType().getFileName();
    }

}
