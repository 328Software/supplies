package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.display.assetengine.AssetHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public interface TextureHandle extends AssetHandle/*, Comparable<TextureHandle>*/ {

    /**
     *
     * @return
     */
    public Integer getTextureId();

    /**
     *
     * @param textureId
     */
    public void setTextureId(Integer textureId);

    /**
     *
     * @return
     *
     */
    public float[] getSubInfo();

    /**
     *
     * @param subInfo
     */
    public void setSubInfo(float[] subInfo);

    public void setAtlasType(AtlasType subAtlasType);
    public AtlasType getAtlasType();




}
