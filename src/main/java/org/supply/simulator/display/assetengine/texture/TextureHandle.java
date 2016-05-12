package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.display.assetengine.AbstractAssetHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public class TextureHandle extends AbstractAssetHandle {
    private AtlasType atlasType;
    private float[] subInfo;




    public Integer getTextureId() {
        return atlasType.getTextureId();
    }


    public void setTextureId(Integer textureId) {
        atlasType.setTextureId(textureId);
    }


    public float[] getSubInfo() {
        return subInfo;
    }


    public void setSubInfo(float[] subInfo) {
        this.subInfo=subInfo;
    }

    public void setAtlasType(AtlasType subAtlasType) {
        this.atlasType =subAtlasType;
    }


    public AtlasType getAtlasType() {
        return atlasType;
    }
}
