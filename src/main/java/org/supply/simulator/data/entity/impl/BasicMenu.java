package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenu implements Menu {
    private Long id;
    private String textureKey;

    @Override
    public String getTextureKey() {
        return textureKey;
    }

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    private Positions unitPositions;

    public Positions getPositions() {
        return unitPositions;
    }

    public void setPositions(Positions unitPositions) {
        this.unitPositions = unitPositions;
    }
}
