package org.supply.simulator.display.manager.chunk.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.HasId;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.ChunkIndexManager;
import org.supply.simulator.display.supplyrenderable.AbstractSupplyRenderable;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunk
        extends AbstractSupplyRenderable
        implements Chunk, SupplyRenderable, HasId<Long> {

    protected Long id;

    public static final int INDICES_PER_VERTEX = 6;

    private boolean isBuilt;
    private boolean isDestroyed;

    private ChunkIndexManager indexManager;

    private BasicChunkData<List<Float>,List<Byte>,List<Integer>> data;

    public BasicChunk () {
        isBuilt =false;
        isDestroyed=true;
    }

    @Override
    public void build() {
        rows = data.getRows();
        columns = data.getColumns();


        if (!indexManager.isIndicesBufferIdStored(rows,columns)) {

            List<Integer> indicesBufferData = indexManager.createIndicesBufferData(rows, columns);

            IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferData.size());
            for(Integer i: indicesBufferData) {
                indicesBuffer.put(i);
            }

            indicesBuffer.flip();

            indicesBufferId = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

            indexManager.storeIndicesBufferId(rows,columns,indicesBufferId);
        } else {
            indicesBufferId = indexManager.getIndicesBufferId(rows,columns);
        }





        vertexAttributesId = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(vertexAttributesId);


        positionsArrayId = GL15.glGenBuffers();
        colorsArrayId = GL15.glGenBuffers();

        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(data.getPositions().size());
        for(Float f: data.getPositions()) {
            verticesFloatBuffer.put(f);
        }
        verticesFloatBuffer.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);


        GL20.glVertexAttribPointer(locations[0], data.POSITION_COUNT, GL11.GL_FLOAT,
                false, data.POSITION_BYTES, data.POSITION_BYTE_OFFSET);


        ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(data.getColors().size());
        for(Byte b: data.getColors()) {
            verticesByteBuffer.put(b);
        }
        verticesByteBuffer.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesByteBuffer, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(locations[1], data.COLOR_COUNT, GL11.GL_UNSIGNED_BYTE,
                true, data.COLOR_BYTES, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL30.glBindVertexArray(0);
        data=null;
        isBuilt =true;
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    @Override
    public void destroy() {
        GL30.glBindVertexArray(vertexAttributesId);

        GL20.glDisableVertexAttribArray(locations[0]);
        GL20.glDisableVertexAttribArray(locations[1]);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(positionsArrayId);

        //  TODO figure out why we are unbinding this buffer twice
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(colorsArrayId);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(indicesBufferId);

        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(vertexAttributesId);

        isDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void render() {

        GL30.glBindVertexArray(vertexAttributesId);
        GL20.glEnableVertexAttribArray(locations[0]);
        GL20.glEnableVertexAttribArray(locations[1]);

        // Bind to the index VBO that has all the information about the order of the vertices
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);

        // Draw the vertices
        GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, rows*columns*INDICES_PER_VERTEX, GL11.GL_UNSIGNED_INT, 0, 0);
        // Put everything back to default (deselect)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(locations[0]);
        GL20.glDisableVertexAttribArray(locations[1]);
        GL30.glBindVertexArray(0);

    }


    public void setData(BasicChunkData<List<Float>,List<Byte>,List<Integer>> data) {
        this.data=data;
    }

    public BasicChunkData<List<Float>, List<Byte>, List<Integer>> getData() {
        return data;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setIndexManager(ChunkIndexManager indexManager) {
        this.indexManager = indexManager;
    }
}
