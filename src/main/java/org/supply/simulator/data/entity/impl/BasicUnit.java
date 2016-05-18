package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.display.assetengine.texture.AtlasType;

import java.util.Set;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnit implements Unit {
    private Long id;
    private Set<Positions> unitPositions;
    private AtlasType atlasType;

//    @Override
//    public String getTextureKey() {
//        return textureKey;
//    }

    public Set<Positions> getPositions() {
        return unitPositions;
    }

    public void setPositions(Set<Positions> unitPositions) {
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
