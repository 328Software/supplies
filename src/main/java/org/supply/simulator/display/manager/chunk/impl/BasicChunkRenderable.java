package org.supply.simulator.display.manager.chunk.impl;

import org.lwjgl.opengl.*;
import org.supply.simulator.display.renderable.AbstractChunkRenderable;
import org.supply.simulator.display.renderable.ChunkRenderable;

/**
 * Created by Alex on 7/17/2014.
 */
public class BasicChunkRenderable extends AbstractChunkRenderable implements ChunkRenderable {
    boolean isDestroyed;
    public static final int INDICES_PER_VERTEX = 6;


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
    public void render() {

        GL30.glBindVertexArray(vertexAttributesId);
        GL20.glEnableVertexAttribArray(locations[0]);
        GL20.glEnableVertexAttribArray(locations[1]);

        // Bind to the index VBO that has all the information about the order of the vertices
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);

        // Draw the vertices
        GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, chunkType.getRows() * chunkType.getColumns() * INDICES_PER_VERTEX, GL11.GL_UNSIGNED_INT, 0, 0);
        // Put everything back to default (deselect)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(locations[0]);
        GL20.glDisableVertexAttribArray(locations[1]);
        GL30.glBindVertexArray(0);

    }


    public boolean isDestroyed() {
        return isDestroyed;
    }
}
