package org.supply.simulator.data.entity;

import org.supply.simulator.core.container.AttributeContainer;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.display.assetengine.texture.Atlas;

import java.util.Set;

public abstract class AbstractUnit implements Entity{

    private Long id;

    private Atlas atlas;

    private AttributeContainer atts;

//    @Override
//    public String getTextureKey() {
//        return textureKey;
//    }

//    @Override
    public void addAttribute(EntityAttribute attribute) {

    }

    public Atlas getAtlas() {
        return atlas;
    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }

    @Override
    public Long getId() {
        return id;
    }

    //    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
