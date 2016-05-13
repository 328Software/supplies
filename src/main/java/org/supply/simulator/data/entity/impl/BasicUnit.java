package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.display.assetengine.texture.AtlasType;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnit implements Unit {
    private Long id;
    private Positions unitPositions;
    private String textureKey;
    private AtlasType atlasType;

    @Override
    public String getTextureKey() {
        return textureKey;
    }

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }
    public Positions getPositions() {
        return unitPositions;
    }

    public void setPositions(Positions unitPositions) {
        this.unitPositions = unitPositions;
    }

    @Override
    public AtlasType getAtlasType() {
        return atlasType;
    }

    @Override
    public void setAtlasType(AtlasType atlasType) {
        this.atlasType=atlasType;
    }

    @Override
    public Long getId() {
        return id;
    }

//    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
