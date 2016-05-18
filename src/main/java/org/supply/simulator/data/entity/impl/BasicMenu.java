package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.AtlasType;

import java.util.Set;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenu implements Menu {
    private Long id;
    private Set<Positions> unitPositions;

    private AtlasType atlasType;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


}
