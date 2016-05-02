package org.supply.simulator.display.assetengine.texture;

/**
 * Created by Alex on 7/30/2014.
 */
public interface AtlasType {

    public Integer getTextureId();

    public void setTextureId(Integer id);

    public void add();

    public void subtract();

    public Integer count();

    public void setFileName(String fileName);

    public String getFileName();
}
