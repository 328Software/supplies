package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.display.assetengine.AbstractAssetHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public class TextureHandle extends AbstractAssetHandle {
    private Atlas atlas;
    private float[] subInfo;




    public Integer getTextureId() {
        return atlas.getTextureId();
    }


    public void setTextureId(Integer textureId) {
        atlas.setTextureId(textureId);
    }


    public float[] getSubInfo() {
        return subInfo;
    }


    public void setSubInfo(float[] subInfo) {
        this.subInfo=subInfo;
    }

    public void setAtlas(Atlas subAtlas) {
        this.atlas = subAtlas;
    }


    public Atlas getAtlas() {
        return atlas;
    }
}
