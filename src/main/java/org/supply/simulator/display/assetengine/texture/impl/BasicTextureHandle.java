package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.display.assetengine.AbstractAssetHandle;
import org.supply.simulator.display.assetengine.texture.AtlasType;
import org.supply.simulator.display.assetengine.texture.TextureHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicTextureHandle extends AbstractAssetHandle implements TextureHandle {
    private AtlasType atlasType;
    private float[] subInfo;



    @Override
    public Integer getTextureId() {
        return atlasType.getTextureId();
    }

    @Override
    public void setTextureId(Integer textureId) {
        atlasType.setTextureId(textureId);
    }

    @Override
    public float[] getSubInfo() {
        return subInfo;
    }

    @Override
    public void setSubInfo(float[] subInfo) {
        this.subInfo=subInfo;
    }

    @Override
    public void setAtlasType(AtlasType subAtlasType) {
        this.atlasType =subAtlasType;
    }

    @Override
    public AtlasType getAtlasType() {
        return atlasType;
    }
}
