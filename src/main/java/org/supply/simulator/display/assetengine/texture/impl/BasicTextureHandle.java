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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicTextureHandle)) return false;
        return atlasType.equals(((BasicTextureHandle) o).atlasType);
    }

    @Override
    public int compareTo (Object o) {
        if (this == o) return 0;
        if (!(o instanceof BasicTextureHandle)) return -1;


//        if (this.getAtlasType()==((BasicTextureHandle)o).getAtlasType()) {
//
//            return 0;
//        }


//        int val = ((BasicTextureHandle) o).getTextureHandle().compareTo(this.getTextureHandle());
//
//        if (val==0) {
//            Integer[] otherInfo = ((BasicTextureHandle) o).getSubInfo();
//            for (int i = 0; i< otherInfo.length;i++) {
//                val = atlasType.getSubInfo()[i].compareTo(otherInfo[i]);
//                if (val != 0) {
//                    return val;
//                }
//            }
//            return 0;
//        }

        return -1;
    }
}
