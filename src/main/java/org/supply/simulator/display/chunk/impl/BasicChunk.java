package org.supply.simulator.display.chunk.impl;

import org.lwjgl.opengl.*;
import org.lwjgl.util.Renderable;
import org.supply.simulator.display.chunk.Chunk;
import org.supply.simulator.display.renderableinfo.HasRenderableInfo;
import org.supply.simulator.display.renderableinfo.HasRenderableInfoAbstract;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunk
        extends    HasRenderableInfoAbstract
        implements Chunk, Renderable, HasRenderableInfo {

    public static final int VERTICES_PER_CHUNK = 100;
    public static final int INDICES_PER_VERTEX = 6;

    private BasicChunkData<FloatBuffer,ByteBuffer> data;

    public void setData(BasicChunkData<FloatBuffer,ByteBuffer> data) {
        this.data=data;
    }

   // public void setEntityDAO(){};


    @Override
    public void build() {
        rows = data.getRows();
        columns = data.getColumns();

        vertexAttributesId = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(vertexAttributesId);


        positionsArrayId = GL15.glGenBuffers();
        colorsArrayId = GL15.glGenBuffers();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data.getPositionsBuffer(), GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(locations[0], data.POSITION_COUNT, GL11.GL_FLOAT,
                false, data.POSITION_BYTES, data.POSITION_BYTE_OFFSET);


        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsArrayId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data.getColorBuffer(), GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(locations[1], data.COLOR_COUNT, GL11.GL_UNSIGNED_BYTE,
                true, data.COLOR_BYTES, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL30.glBindVertexArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        data=null;
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

//    private void updateEntities() {

//    }
}
