package org.supply.simulator.display.renderer.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.ChunkIndexEngine;
import org.supply.simulator.display.renderer.AbstractRenderer;
import org.supply.simulator.display.renderer.OldAbstractRenderer;
import org.supply.simulator.display.renderer.EntityRenderer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Collection;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunkRenderer extends AbstractRenderer<Chunk> implements EntityRenderer<Chunk> {

    // The amount of bytes an element has
    public static final int POSITION_ELEMENT = 4 ;
    public static final int COLOR_ELEMENT = 1;

    // Elements per parameter
    public static final int POSITION_COUNT = 4;
    public static final int COLOR_COUNT = 4;
    public static final int TEXTURE_COUNT = 2;

    // Bytes per parameter
    public static final int POSITION_BYTES = POSITION_COUNT * POSITION_ELEMENT;
    public static final int COLOR_BYTES = COLOR_COUNT * COLOR_ELEMENT;
    public static final int TEXTURE_BYTE = TEXTURE_COUNT * POSITION_ELEMENT;

    // Byte offsets per parameter
    public static final int POSITION_BYTE_OFFSET = 0;
    public static final int COLOR_BYTE_OFFSET = POSITION_BYTE_OFFSET + POSITION_COUNT;
    public static final int TEXTURE_BYTE_OFFSET = COLOR_BYTE_OFFSET + COLOR_COUNT;

    public static final int STRIDE = POSITION_BYTES + COLOR_BYTES + TEXTURE_BYTE;

    public static final int INDICES_PER_VERTEX = 6;

    private int[] locations;

    {
        locations = new int[] { 0, 1 };
    }

    private ChunkIndexEngine chunkIndexEngine;

    @Override
    public void build(Collection<Chunk> renderables) {
        for (Chunk renderable : renderables) {

            int vertexAttributesId = GL30.glGenVertexArrays();

            GL30.glBindVertexArray(vertexAttributesId);

            int positionsArrayId = GL15.glGenBuffers();
            int colorsArrayId = GL15.glGenBuffers();

            FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(renderable.getChunkPositions().getValue().length);
            verticesFloatBuffer.put(renderable.getChunkPositions().getValue());
            verticesFloatBuffer.flip();

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);


            GL20.glVertexAttribPointer(locations[0], POSITION_COUNT, GL11.GL_FLOAT,
                    false, POSITION_BYTES, POSITION_BYTE_OFFSET);


            ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(renderable.getChunkColors().getValue().length);
            verticesByteBuffer.put(renderable.getChunkColors().getValue());

            verticesByteBuffer.flip();

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsArrayId);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesByteBuffer, GL15.GL_STATIC_DRAW);

            GL20.glVertexAttribPointer(locations[1], COLOR_COUNT, GL11.GL_UNSIGNED_BYTE,
                    true, COLOR_BYTES, 0);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);


            GL30.glBindVertexArray(0);

            renderable.setVertexAttributesId(vertexAttributesId);
            renderable.setPositionsArrayId(positionsArrayId);
            renderable.setColorsArrayId(colorsArrayId);
            renderable.setIndicesBufferId(chunkIndexEngine.get(renderable.getType()).getIndexId());
        }

    }

    @Override
    public void render(Collection<Chunk> renderables) {

        for (Chunk renderable : renderables) {
            GL30.glBindVertexArray(renderable.getVertexAttributesId());
            GL20.glEnableVertexAttribArray(locations[0]);
            GL20.glEnableVertexAttribArray(locations[1]);

            // Bind to the index VBO that has all the information about the order of the vertices
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, renderable.getIndicesBufferId());

            // Draw the vertices
            GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, renderable.getType().getRows() * renderable.getType().getColumns() * INDICES_PER_VERTEX, GL11.GL_UNSIGNED_INT, 0, 0);
            // Put everything back to default (deselect)
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL20.glDisableVertexAttribArray(locations[0]);
            GL20.glDisableVertexAttribArray(locations[1]);
            GL30.glBindVertexArray(0);
        }

    }

    @Override
    public void destroy(Collection<Chunk> renderables) {
        for (Chunk renderable : renderables) {
            GL30.glBindVertexArray(renderable.getVertexAttributesId());

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            GL15.glDeleteBuffers(renderable.getPositionsArrayId());

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            GL15.glDeleteBuffers(renderable.getColorsArrayId());

            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//            GL15.glDeleteBuffers(indicesBufferId);
            chunkIndexEngine.done(renderable.getType());

            GL30.glBindVertexArray(0);
            GL30.glDeleteVertexArrays(renderable.getVertexAttributesId());


        }


    }

    @Override
    public void destroyAll() {

    }

    @Override
    protected void setIndicesBufferId() {

    }

    @Override
    public void setAttributeLocations(int[] locations) {
        this.locations=locations;
    }

    @Override
    public int[] getAttributeLocations() {
        return locations;
    }

    @Override
    public void setIndexEngine(IndexEngine chunkIndexEngine) {
        this.chunkIndexEngine = (ChunkIndexEngine)chunkIndexEngine;
    }
}
