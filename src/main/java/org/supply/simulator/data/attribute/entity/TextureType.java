package org.supply.simulator.data.attribute.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface TextureType extends EntityAttribute<Long> {

    public void setFileName(String fileName);


    public void setWidth(int width);

    public void setHeight(int height);

    public String getFileName();


    public int getWidth();

    public int getHeight();

}
