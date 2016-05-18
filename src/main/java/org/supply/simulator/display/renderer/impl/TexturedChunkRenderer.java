package org.supply.simulator.display.renderer.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;
import org.supply.simulator.util.MapUtils;

import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRenderer extends RendererBase<BasicChunk> implements EntityRenderer<BasicChunk> {
    private HashMap<BasicChunk,ChunkIds> chunkIdMap;

    public TexturedChunkRenderer () {
        super();
//        maxEntities =1000;
        chunkIdMap=new HashMap<>();
    }

    @Override
    protected void buildEntities(Collection<BasicChunk> entities) {


        for (BasicChunk chunk : entities) {
            ChunkIds ids = new ChunkIds();
            ids.positionsArrayId = GL15.glGenBuffers();
            ids.vertexAttributesId = GL30.glGenVertexArrays();

            GL30.glBindVertexArray(ids.vertexAttributesId);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ids.positionsArrayId);
            GL20.glVertexAttribPointer(locations[0], POSITION_ELEMENT_COUNT, GL11.GL_FLOAT,
                    false, STRIDE, POSITION_BYTE_OFFSET);
            // Put the color components in attribute list 1
            GL20.glVertexAttribPointer(locations[1], COLOR_ELEMENT_COUNT, GL11.GL_FLOAT,
                    false, STRIDE, COLOR_BYTE_OFFSET);
            // Put the texture coordinates in attribute list 2
//            GL20.glVertexAttribPointer(locations[2], TEXTURE_ELEMENT_COUNT, GL11.GL_FLOAT,
//                    false, stride, TEXTURE_BYTE_OFFSET);

            FloatBuffer verticesFloatBuffer =
                    BufferUtils.createFloatBuffer(
                            chunk.getPositions().stream().mapToInt(
                                    e->e.getValue().length
                            ).sum());

            for(Positions positions: chunk.getPositions()) {
                verticesFloatBuffer.put(positions.getValue());
            }
            verticesFloatBuffer.flip();

//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ids.positionsArrayId);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);

            chunk.setVertexAttributesId(ids.vertexAttributesId);
            chunk.setPositionsArrayId(ids.positionsArrayId);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            GL30.glBindVertexArray(0);
        }


    }

    @Override
    protected void drawEntities(Collection<BasicChunk> entityList) {
        for (BasicChunk chunk : entityList) {
            // Draw the vertices
            GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, 20 * 20 * VERTICES_PER_ENTITY, GL11.GL_UNSIGNED_INT, 0, 0);

        }
    }



    @Override
    protected void setIndicesBufferId() {
        indicesBufferId = ((BasicIndexEngine)indexEngine).get(MapUtils.newEntry(20,20)).getIndexId();
    }

    public class ChunkIds {
        public int positionsArrayId;
        public int vertexAttributesId;
    }
}
