package org.supply.simulator.data.attribute.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface TexturedEntityType<I> extends EntityType<I> {

    public TextureType getTextureType();

    public void setTextureType(TextureType textureType);

    public float[] getSubInfo();

    public void setSubInfo(float[] subInfo);

}
