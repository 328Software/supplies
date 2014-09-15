package org.supply.simulator.data.attribute.entity.impl;

import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.TextureType;
import org.supply.simulator.data.attribute.entity.UnitType;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnitType implements UnitType {

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float[] getSubInfo() {
        return subInfo;
    }

    @Override
    public TextureType getTextureType() {
        return textureType;
    }

    @Override
    public void setTextureType(TextureType textureType) {
        this.textureType = textureType;

    }

        @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSubInfo(float[] subInfo) {
        this.subInfo =subInfo;
    }


}
