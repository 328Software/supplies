package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.attribute.entity.impl.BasicChunkType;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.assetengine.indices.impl.ChunkIndexEngine;
import org.supply.simulator.display.renderer.AbstractRenderer;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRenderer extends AbstractRenderer<BasicChunk> implements EntityRenderer<BasicChunk> {
    public BasicChunkType getChunkType() {
        return chunkType;
    }

    public void setChunkType(BasicChunkType chunkType) {
        this.chunkType = chunkType;
    }

    private BasicChunkType chunkType;

    @Override
    protected void setIndicesBufferId() {
        indicesBufferId=((ChunkIndexEngine)indexEngine).get(chunkType).getIndexId();
    }
}
