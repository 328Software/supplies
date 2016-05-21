package org.supply.simulator.display.renderer.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;
import org.supply.simulator.util.MapUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.Collections;

import static org.supply.simulator.display.renderer.DrawingUtil.POSITION_BYTE_OFFSET;
import static org.supply.simulator.display.renderer.DrawingUtil.createVerticesFloatBuffer;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunkRenderer extends RendererBase implements EntityRenderer {

    // The amount of bytes an element has
    public static final int POSITION_ELEMENT = 4;
    public static final int COLOR_ELEMENT = 1;

    // Elements per parameter
    public static final int POSITION_COUNT = 4;
    public static final int COLOR_COUNT = 4;
    public static final int TEXTURE_COUNT = 2;

    // Bytes per parameter
    public static final int POSITION_BYTES = POSITION_COUNT * POSITION_ELEMENT;
    public static final int COLOR_BYTES = COLOR_COUNT * COLOR_ELEMENT;
    public static final int TEXTURE_BYTE = TEXTURE_COUNT * POSITION_ELEMENT;


    public static final int INDICES_PER_VERTEX = 6;

    private int[] locations;

    {
        locations = new int[]{0, 1};
    }

//    private BasicIndexEngine basicIndexEngine;

    @Override
    public void build(Collection<Entity> renderables) {
        for (Entity entity : renderables) {
            Chunk renderable = (Chunk)entity;

            int vertexAttributesId = GL30.glGenVertexArrays();

            GL30.glBindVertexArray(vertexAttributesId);

            int positionsArrayId = GL15.glGenBuffers();
            int colorsArrayId = GL15.glGenBuffers();

            FloatBuffer verticesFloatBuffer =createVerticesFloatBuffer(Collections.singletonList(renderable));
//                    BufferUtils.createFloatBuffer(
//                            renderable.getPositions().stream().mapToInt(
//                                    e->e.getValue().length
//                            ).sum());
//
//            for(Positions positions: renderable.getPositions()) {
//                verticesFloatBuffer.put(positions.getValue());
//            }
//            verticesFloatBuffer.flip();

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);


            GL20.glVertexAttribPointer(locations[0], POSITION_COUNT, GL11.GL_FLOAT,
                    false, POSITION_BYTES, POSITION_BYTE_OFFSET);


            ByteBuffer verticesByteBuffer = BufferUtils.createByteBuffer(renderable.getColors().getValue().length);
            verticesByteBuffer.put(renderable.getColors().getValue());

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
            renderable.setIndicesBufferId(indexEngine.get(MapUtils.newEntry(rows, columns)).getIndexId());
        }

    }

    @Override
    public void render(Collection<Entity> renderables) {

        for (Entity entity : renderables) {
            Chunk renderable = (Chunk)entity;
            GL30.glBindVertexArray(renderable.getVertexAttributesId());
            GL20.glEnableVertexAttribArray(locations[0]);
            GL20.glEnableVertexAttribArray(locations[1]);

            // Bind to the index VBO that has all the information about the order of the vertices
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, renderable.getIndicesBufferId());

            // Draw the vertices
            GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, rows * columns * INDICES_PER_VERTEX, GL11.GL_UNSIGNED_INT, 0, 0);
            // Put everything back to default (deselect)
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL20.glDisableVertexAttribArray(locations[0]);
            GL20.glDisableVertexAttribArray(locations[1]);
            GL30.glBindVertexArray(0);
        }

    }

    @Override
    public void destroy(Collection<Entity> renderables) {
        for (Entity entity : renderables) {
            Chunk renderable = (Chunk)entity;
            GL30.glBindVertexArray(renderable.getVertexAttributesId());

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            GL15.glDeleteBuffers(renderable.getPositionsArrayId());

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            GL15.glDeleteBuffers(renderable.getColorsArrayId());

            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//            GL15.glDeleteBuffers(staticIndicesBufferId);
            indexEngine.done(MapUtils.newEntry(rows, columns));

            GL30.glBindVertexArray(0);
            GL30.glDeleteVertexArrays(renderable.getVertexAttributesId());


        }


    }

    @Override
    protected void destroyEntities(Collection<Entity> entities) {

    }

    @Override
    public void destroyAll() {

    }

    @Override
    protected void buildEntities(Collection<Entity> entityList) {

    }

    @Override
    protected void drawEntities(Collection<Entity> entityList) {

    }

}
