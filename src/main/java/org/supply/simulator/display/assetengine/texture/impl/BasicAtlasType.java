package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.display.assetengine.texture.AtlasType;

/**
 * Created by Alex on 7/30/2014.
 */
public class BasicAtlasType implements AtlasType {

    private String fileName;
    private Integer id;
    protected Integer count=0;
    private int height;
    private int width;

    @Override
    public void add() {
        count = count +1;
    }

    @Override
    public void subtract() {
        count = count -1;
    }

    @Override
    public Integer count() {
        return count;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName=fileName;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public Integer getTextureId() {
        return id;
    }

    @Override
    public void setTextureId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicAtlasType)) return false;
        return false;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
