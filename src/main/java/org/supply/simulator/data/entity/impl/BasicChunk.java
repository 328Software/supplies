package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.data.entity.Colors;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.Atlas;

import java.util.Set;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunk implements Chunk {
    private Long id;
    private Colors colors;
    private Set<Positions> positions;
    private Atlas atlas;

    protected int vertexAttributesId;

    protected int indicesBufferId;
    protected int colorsArrayId;
    protected int positionsArrayId;
    String textureKey;

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }



    public Set<Positions> getPositions() {
        return positions;
    }

    public void setPositions(Set<Positions> positions) {
        this.positions = positions;
    }

//    @Override
    public void addAttribute(EntityAttribute attribute) {

    }

    public Atlas getAtlas() {
        return atlas;

    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }

    public void setVertexAttributesId(int vertexAttributesId) {
        this.vertexAttributesId = vertexAttributesId;
    }

    public void setIndicesBufferId(int indicesBufferId) {
        this.indicesBufferId = indicesBufferId;
    }

    public void setColorsArrayId(int colorsArrayId) {
        this.colorsArrayId = colorsArrayId;

    }

    public int getVertexAttributesId() {
        return vertexAttributesId;
    }

    public int getIndicesBufferId() {
        return indicesBufferId;
    }

    public int getColorsArrayId() {
        return colorsArrayId;
    }

    public int getPositionsArrayId() {
        return positionsArrayId;
    }


    public void setPositionsArrayId(int positionsArrayId) {
        this.positionsArrayId = positionsArrayId;

    }

//    @Override

    @Override
    public Long getId() {
        return id;
    }

   /* public Long getId() {

        return id;
    }*/

    public void setId(Long id) {
        this.id = id;
    }
}
