package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;

/**
 * Created by Brandon on 5/8/2016.
 */
public class SimpleTextMenu implements Menu {
    private String text;
    private Long id;
    String textureKey;
    Positions positions;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTextureKey() {
        return textureKey;
    }

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }

    @Override
    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }
}
