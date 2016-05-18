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

/**
 * Created by Brandon on 5/11/2016.
 */
public class DrawingUtil {
    protected static Logger logger = LogManager.getLogger(DrawingUtil.class);

    public static void staticBuild(Collection<Entity> entityList) {


    }

    /**
     * Static Draw
     *
     * Draws entity data thats already loaded into OpenGL Memory
     * Assumes appropriate OpenGL buffers are enabled
     *
     *
     * @param entityList
     * @param vertexSize
     * @param verticesPerEntity
     * @param maxEntities
     */
    public static void staticDraw(Collection<Entity> entityList, int vertexSize, int verticesPerEntity, int maxEntities) {


    }

    /**
     * Dynamic Draw
     *
     * First loads entity data into OpenGL memory then draws
     * Assumes appropriate OpenGL buffers are enabled
     *
     *
     * @param entityList
     * @param vertexSize
     * @param verticesPerEntity
     * @param maxEntities
     */
    public static void dynamicDraw(Collection<Entity> entityList, int vertexSize, int verticesPerEntity, int maxEntities) {
//        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(vertexSize * maxEntities);

        FloatBuffer verticesFloatBuffer= createVerticesFloatBuffer(entityList);
        int size = verticesFloatBuffer.limit();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_DYNAMIC_DRAW);

        //        try {
//            Thread.sleep(100);//milliseconds
//        } catch(InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
        GL11.glDrawElements(GL11.GL_TRIANGLES, //render mode i.e. what kind of primitives are we constructing our image out of
                size, //Number of vertices to render (there's 6 per image)
                GL11.GL_UNSIGNED_INT, //indicates the type of index values in indices
                size * Integer.SIZE * 0);//index into buffer when to start rendering
    }

    /**
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
     *
     * Creates pointers to OpenGL memory and nicely stores them in a OpenGLBufferIDBag
     *
     *
     * @param atlas this class will retrieve the TextureId from the atlas to store in the bag
     * @param locations these attribute locations (for shaders) will be bound to the new array buffer
     * @return
     */
    public static OpenGLBufferIDBag allocateOpenGLBuffers(Atlas atlas, int[] locations) {
        OpenGLBufferIDBag ids = new OpenGLBufferIDBag();
        ids.setTextureId(atlas.getTextureId());
        ids.setPositionsArrayId(GL15.glGenBuffers());
        ids.setVertexAttributesId(GL30.glGenVertexArrays());

        GL30.glBindVertexArray(ids.getVertexAttributesId());
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ids.getPositionsArrayId());

        //TODO where do we store these constants used below, we shouldn't be referencing RendererBase
        GL20.glVertexAttribPointer(locations[0], RendererBase.POSITION_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, RendererBase.STRIDE, RendererBase.POSITION_BYTE_OFFSET);
        GL20.glVertexAttribPointer(locations[1], RendererBase.COLOR_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, RendererBase.STRIDE, RendererBase.COLOR_BYTE_OFFSET);
        GL20.glVertexAttribPointer(locations[2], RendererBase.TEXTURE_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, RendererBase.STRIDE, RendererBase.TEXTURE_BYTE_OFFSET);


        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        return ids;
    }

    public static void bindBufferIds(OpenGLBufferIDBag ids,int indicesBufferId, int locations) {


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
