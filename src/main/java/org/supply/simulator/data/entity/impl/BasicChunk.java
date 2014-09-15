package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
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

    @Override
    public ChunkType getChunkType() {
        return chunkType;
    }

    public void setChunkType(ChunkType chunkType) {
        this.chunkType = chunkType;
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
}
