package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.Atlas;

import java.util.Set;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenu implements Menu {
    private Long id;
    private Set<Positions> unitPositions;

    private Atlas atlas;

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

    public Atlas getAtlas() {
        return atlas;
    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }


}
