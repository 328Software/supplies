package org.supply.simulator.display.renderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.Atlas;
import org.supply.simulator.display.renderer.impl.OpenGLBufferIDBag;

import java.nio.FloatBuffer;
import java.util.Collection;

import static java.util.Objects.nonNull;

/**
 * Created by Brandon on 5/11/2016.
 */
public class DrawingUtil {
    protected static Logger logger = LogManager.getLogger(DrawingUtil.class);

    // The amount of bytes an element has
    public static final int ELEMENT_BYTES = 4;

    // Elements per parameter
    public static final int POSITION_ELEMENT_COUNT = 4;
    public static final int COLOR_ELEMENT_COUNT = 4;
    public static final int TEXTURE_ELEMENT_COUNT = 2;

    // Bytes per parameter
    public static final int POSITION_BYTES_COUNT = POSITION_ELEMENT_COUNT * ELEMENT_BYTES;
    public static final int COLOR_BYTE_COUNT = COLOR_ELEMENT_COUNT * ELEMENT_BYTES;
    public static final int TEXTURE_BYTE_COUNT = TEXTURE_ELEMENT_COUNT * ELEMENT_BYTES;

    // Byte offsets per parameter
    public static final int POSITION_BYTE_OFFSET = 0;
    public static final int COLOR_BYTE_OFFSET = POSITION_BYTE_OFFSET + POSITION_BYTES_COUNT;
    public static final int TEXTURE_BYTE_OFFSET = COLOR_BYTE_OFFSET + COLOR_BYTE_COUNT;

    // The amount of elements that a vertex has
    // The size of a vertex in bytes, like in C/C++: sizeof(Vertex)
    public static final int STRIDE = POSITION_BYTES_COUNT + COLOR_BYTE_COUNT +
            TEXTURE_BYTE_COUNT;

    public static final int VERTICES_PER_ENTITY = 6;

    /**
     * Static Build
     *
     * First loads entity data into OpenGL memory as STATIC then draws
     * Assumes appropriate OpenGL buffers are enabled
     *
     *
     * @param entityList
     */
    public static void staticBuild(Collection<Entity> entityList) {
//        FloatBuffer verticesFloatBuffer= createVerticesFloatBuffer(entityList);
//
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER,verticesFloatBuffer,GL15.GL_STATIC_DRAW);
//
//        drawElements(verticesFloatBuffer.limit(),0);
    }

    /**
     * Static Draw
     *
     * Draws entity data thats already loaded into OpenGL Memory
     * Assumes appropriate OpenGL buffers are enabled
     *
     *
     * @param entityList
     */
    public static void staticDraw(Collection<Entity> entityList) {
//        int numberOfEntities = entityList.stream().mapToInt(c -> c.getPositions().stream().mapToInt(
//                e->e.getValue().length
//        ).sum()).sum();
//
//
//        GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES,
//                numberOfEntities * VERTICES_PER_ENTITY,
//                GL11.GL_UNSIGNED_INT,
//                0,
//                0);
    }

    /**
     * Dynamic Draw
     *
     * First loads entity data into OpenGL memory as DYNAMIC then draws
     * Assumes appropriate OpenGL buffers are enabled
     *
     *
     * @param entityList
     */
    public static void dynamicDraw(Collection<Entity> entityList) {
//        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(vertexSize * maxEntities);

        FloatBuffer verticesFloatBuffer= createVerticesFloatBuffer(entityList);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER,verticesFloatBuffer,GL15.GL_DYNAMIC_DRAW);

        drawElements(verticesFloatBuffer.limit(),0);

    }

    /**
     * Draw Elements
     *
     * Calls OpenGL procedure glDrawElements which causes whatever image that is
     *  stored in the current active buffer to be rendered onto the screen
     *  Assumes appropriate OpenGL buffers are enabled and initialized
     *
     * @param size number of vertices to render
     * @param index index into buffer when to start rendering
     */
    public static void drawElements(int size,int index) {
        GL11.glDrawElements(GL11.GL_TRIANGLES, //render mode i.e. what kind of primitives are we constructing our image out of
                size, //Number of vertices to render (there's 6 per image right now)
                GL11.GL_UNSIGNED_INT, //indicates the type of index values in indices
                index * Integer.SIZE );//index into buffer when to start rendering
    }

    /**
     * Create Vertices Float Buffer
     *
     *
     * loads entity data into a float buffer
     *
     *
     *
     * @param entities
     */
    public static FloatBuffer createVerticesFloatBuffer (Collection<Entity> entities) {
        FloatBuffer verticesFloatBuffer =
                BufferUtils.createFloatBuffer(
                        entities.stream().mapToInt(c -> c.getPositions().stream().mapToInt(
                                e->e.getValue().length
                        ).sum()).sum());

        for (Entity entity : entities) {
            for(Positions positions: entity.getPositions()) {
                verticesFloatBuffer.put(positions.getValue());
            }
        }
        verticesFloatBuffer.flip();
        return verticesFloatBuffer;
    }

    /**
     * Allocate OpenGL Buffers
     *
     * Creates pointers to OpenGL memory and nicely stores them in a OpenGLBufferIDBag
     *
     * @param atlas method will retrieve TextureId from atlas to store in the bag
     * @param locations these attribute locations (for shaders) will be bound to the new array buffer
     *
     * @return OpenGLBufferIDBag id bag of pointers into OpenGL memory
     */
    public static OpenGLBufferIDBag allocateOpenGLBuffers(Atlas atlas, int[] locations) {
        OpenGLBufferIDBag ids = new OpenGLBufferIDBag();
        ids.setTextureId(atlas.getTextureId());
        ids.setPositionsArrayId(GL15.glGenBuffers());
        ids.setVertexAttributesId(GL30.glGenVertexArrays());

        GL30.glBindVertexArray(ids.getVertexAttributesId());
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ids.getPositionsArrayId());

        GL20.glVertexAttribPointer(locations[0], POSITION_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, STRIDE, POSITION_BYTE_OFFSET);
        GL20.glVertexAttribPointer(locations[1], COLOR_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, STRIDE, COLOR_BYTE_OFFSET);
        GL20.glVertexAttribPointer(locations[2], TEXTURE_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, STRIDE, TEXTURE_BYTE_OFFSET);


        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        return ids;
    }

    public static void bindBufferIds(OpenGLBufferIDBag ids,int indicesBufferId, int[] locations) {
        enableArrayBuffer(ids.getPositionsArrayId());
        enableVertexAttribArray(locations, ids.getVertexAttributesId());
        enableIndicesBuffer(indicesBufferId);

        if (nonNull(ids.getTextureId())) {
            enableTextureBuffer(ids.getTextureId());
        }

    }

    public static void unBindBufferIds(int[] locations) {
        disableVertexAttribArray(locations);

        disableIndicesBuffer();
        disableArrayBuffer();
        disableTextureBuffer();
    }

    public static void enableVertexAttribArray(int[] locations, int id) {
        GL30.glBindVertexArray(id);
        for (int attributeLocation : locations) {
            GL20.glEnableVertexAttribArray(attributeLocation);
        }

    }

    public static void disableVertexAttribArray(int[] locations) {
        for (int attributeLocation : locations) {
            GL20.glDisableVertexAttribArray(attributeLocation);
        }
        GL30.glBindVertexArray(0);
    }

    public static void enableTextureBuffer (int id ) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
    }


    public static void enableArrayBuffer (int id) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
    }

    public static void enableIndicesBuffer (int id) {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,id);
    }


    public static void disableTextureBuffer () {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public static void disableArrayBuffer () {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public static void disableIndicesBuffer () {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

    }
}
