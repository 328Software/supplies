package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.attribute.entity.TextureType;

/**
 * Created by Alex on 9/14/2014.
 */
public class MockMenuType implements MenuType {
    private Long id;

    private String name;

    private TextureType textureType;

    private float[] subInfo;

    @Override
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
