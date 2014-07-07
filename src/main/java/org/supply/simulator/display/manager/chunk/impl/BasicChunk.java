package org.supply.simulator.display.manager.chunk.impl;

import org.lwjgl.opengl.*;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.supplyrenderable.AbstractSupplyRenderable;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunk
        extends AbstractSupplyRenderable
        implements Chunk, SupplyRenderable {

    public static final int INDICES_PER_VERTEX = 6;

    private boolean isBuilt;
    private boolean isDestroyed;

    private BasicChunkData<FloatBuffer,ByteBuffer,IntBuffer> data;

    public BasicChunk () {
        isBuilt =false;
        isDestroyed=true;
    }

    public void setData(BasicChunkData<FloatBuffer,ByteBuffer,IntBuffer> data) {
        this.data=data;

    }


    @Override
    public void build() {
        rows = data.getRows();
        columns = data.getColumns();

        //TODO THE BIG QUESTION: do we reuse indicesBufferIds?
        indicesBufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, data.getIndices(),GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        vertexAttributesId = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(vertexAttributesId);


        positionsArrayId = GL15.glGenBuffers();
        colorsArrayId = GL15.glGenBuffers();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data.getPositions(), GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(locations[0], data.POSITION_COUNT, GL11.GL_FLOAT,
                false, data.POSITION_BYTES, data.POSITION_BYTE_OFFSET);


        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data.getColors(), GL15.GL_STATIC_DRAW);

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
}
