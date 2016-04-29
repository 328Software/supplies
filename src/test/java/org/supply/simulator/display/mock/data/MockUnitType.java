package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.attribute.entity.TextureType;
import org.supply.simulator.data.attribute.entity.UnitType;

/**
 * Created by Alex on 9/7/2014.
 */
public class MockUnitType implements UnitType {

    private Long id;

    private String name;

    private float[] subInfo;

    private TextureType textureType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public float[] getSubInfo() {
        return subInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSubInfo(float[] subInfo) {
        this.subInfo = subInfo;
    }

    @Override
    public TextureType getTextureType() {
        return textureType;
    }

    @Override
    public void setTextureType(TextureType textureType) {
        this.textureType=textureType;

    }

}
