package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.renderer.RendererBase;
import org.supply.simulator.display.renderer.EntityRenderer;

import java.util.Collection;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRenderer extends RendererBase<BasicChunk> implements EntityRenderer<BasicChunk> {
    public TexturedChunkRenderer () {
        super();
//        max_entities =1000;
    }

    @Override
    protected void buildEntities(Collection<BasicChunk> entities) {

    }

    @Override
    protected void drawEntities(Collection<BasicChunk> entityList) {

    }

    @Override
    protected void setIndicesBufferId() {

    }

//    public BasicChunkType getChunkType() {
//        return chunkType;
//    }
//
//    public void setChunkType(BasicChunkType chunkType) {
//        this.chunkType = chunkType;
//    }
//
//    private BasicChunkType chunkType;
//
//
//    @Override
//    protected void setIndicesBufferId() {
//        indicesBufferId=((ChunkIndexEngine)indexEngine).get(chunkType).getIndexId();
//    }
}
