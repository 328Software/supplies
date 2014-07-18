package org.supply.simulator.display.manager.chunk.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.HasId;
import org.supply.simulator.display.buildable.AbstractChunkBuildable;
import org.supply.simulator.display.buildable.SupplyBuildable;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.ChunkData;
import org.supply.simulator.display.manager.chunk.ChunkRenderable;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.display.renderable.SupplyRenderable;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunk
        extends AbstractChunkBuildable
        implements Chunk<ChunkData<float[], byte[]>>, SupplyBuildable, HasId<Long> {

    protected Long id;

    protected ChunkType chunkType;

    private ChunkData<float[], byte[]> data;

    @Override
    public BasicChunkRenderable build() {

        BasicChunkRenderable chunkRenderable = new BasicChunkRenderable();

        int vertexAttributesId = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(vertexAttributesId);

        int positionsArrayId = GL15.glGenBuffers();
        int colorsArrayId = GL15.glGenBuffers();

        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(data.getPositions().length);
        verticesFloatBuffer.put(data.getPositions());
        verticesFloatBuffer.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);


        GL20.glVertexAttribPointer(locations[0], BasicChunkData.POSITION_COUNT, GL11.GL_FLOAT,
                false, BasicChunkData.POSITION_BYTES, BasicChunkData.POSITION_BYTE_OFFSET);


        ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(data.getColors().length);
        verticesByteBuffer.put(data.getColors());

        verticesByteBuffer.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesByteBuffer, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(locations[1], BasicChunkData.COLOR_COUNT, GL11.GL_UNSIGNED_BYTE,
                true, BasicChunkData.COLOR_BYTES, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        chunkRenderable.setChunkType(chunkType);
        chunkRenderable.setAttributeLocations(locations);
        chunkRenderable.setColorsArrayId(colorsArrayId);
        chunkRenderable.setPositionsArrayId(positionsArrayId);
        chunkRenderable.setVertexAttributesId(vertexAttributesId);


        GL30.glBindVertexArray(0);

        return chunkRenderable;
    }


    @Override
    public Long getId() {
        return id;
    }


    @Override
    public ChunkData<float[], byte[]> getData() {
        return data;
    }


    @Override
    public void setData(ChunkData<float[], byte[]> data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChunkType(ChunkType chunkType) {
        this.chunkType = chunkType;
    }


}
