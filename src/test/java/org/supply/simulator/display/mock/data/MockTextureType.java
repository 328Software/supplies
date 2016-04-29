package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.attribute.entity.TextureType;

/**
 * Created by Alex on 9/14/2014.
 */
public class MockTextureType implements TextureType {
    private Long id;
    private int width;
    private int height;

    private String fileName;

    @Override
    public void setFileName(String fileName) {
        this.fileName=fileName;

    }

    @Override
    public void setWidth(int width) {
        this.width=width;
    }

    @Override
    public void setHeight(int height) {
        this.height=height;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}