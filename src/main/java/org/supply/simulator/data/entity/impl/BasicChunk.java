package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.impl.BasicChunkType;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.statistic.entity.ChunkColors;
import org.supply.simulator.data.statistic.entity.ChunkPositions;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.impl.BasicChunkColors;
import org.supply.simulator.data.statistic.entity.impl.BasicChunkPositions;

import java.util.Iterator;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunk implements Chunk {
    private Long id;
    private ChunkColors chunkColors;
    private ChunkPositions chunkPositions;
    private ChunkType chunkType;

    protected int vertexAttributesId;

    protected int indicesBufferId;
    protected int colorsArrayId;
    protected int positionsArrayId;

    @Override
    public void setType(EntityType type) {
        this.chunkType = (ChunkType)type;
    }


    //TODO remove this method hibernate breaks
    public void setType(ChunkType type) {
        this.chunkType = type;
    }

    //TODO remove this method hibernate breaks
    public ChunkType getType() {
        return this.chunkType;
    }


    public ChunkColors getChunkColors () {
        return chunkColors;
    }

    public void setChunkColors ( ChunkColors chunkColors) {
        this.chunkColors =chunkColors;
    }



    public ChunkPositions getChunkPositions () {
        return chunkPositions;
    }

    public void setChunkPositions ( ChunkPositions chunkPositions) {
        this.chunkPositions =chunkPositions;
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

    @Override
    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Iterator<EntityAttribute> iterator() {
        return null;
    }

    @Override
    public void addAttribute(EntityAttribute attribute) {

    }

    @Override
    public void removeAttribute(Object attributeId) {

    }

    @Override
    public EntityAttribute getAttribute(Object attributeId) {
        return null;
    }

    @Override
    public boolean hasAttribute(Object attributeId) {
        return false;
    }

    @Override
    public void addStatistic(EntityStatistic statistic) {

    }

    @Override
    public void removeStatistic(Object statisticId) {

    }

    @Override
    public EntityStatistic getStatistic(Object statisticId) {
        return null;
    }

    @Override
    public boolean hasStatistic(Object statisticId) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
