package org.supply.simulator.display.assetengine.texture;

/**
 * Created by Alex on 7/30/2014.
 */
public interface AtlasType {

    public void setHeight(int height);

    public void setWidth(int width);

    public int getHeight();

    public int getWidth();

    public Integer getTextureId();

    public void setTextureId(Integer id);

    public void add();

    public void subtract();

    public Integer count();

    public void setFileName(String fileName);

    public String getFileName();
}
