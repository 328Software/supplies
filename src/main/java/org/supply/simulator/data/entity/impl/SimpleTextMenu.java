package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;

/**
 * Created by Brandon on 5/8/2016.
 */
public class SimpleTextMenu implements Menu {
    private String text;
    private Long id;
    String textureKey;

    @Override
    public String getTextureKey() {
        return textureKey;
    }

    public void setTextureKey(String textureKey) {

    }
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Positions getPositions() {
        return null;
    }

    @Override
    public void setPositions(Positions positions) {

    }
}
