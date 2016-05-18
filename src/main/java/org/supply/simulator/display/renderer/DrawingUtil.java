package org.supply.simulator.display.renderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Unit;

import java.nio.FloatBuffer;
import java.util.Collection;

/**
 * Created by Brandon on 5/11/2016.
 */
public class DrawingUtil {
    protected static Logger logger = LogManager.getLogger(DrawingUtil.class);

    public static void staticDraw(Collection<Entity> entityList, int vertexSize, int verticesPerEntity, int maxEntities) {


    }
    public static void dynamicDraw(Collection<Entity> entityList, int vertexSize, int verticesPerEntity, int maxEntities) {
//        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(vertexSize * maxEntities);
        FloatBuffer verticesFloatBuffer =
                BufferUtils.createFloatBuffer(
                        entityList.stream().mapToInt(c -> c.getPositions().stream().mapToInt(
                                e->e.getValue().length
                        ).sum()).sum());

        for (Entity entity : entityList) {
            for(Positions positions: entity.getPositions()) {
                verticesFloatBuffer.put(positions.getValue());
            }
        }
        int size = verticesFloatBuffer.limit();
        verticesFloatBuffer.flip();

//        try {
//            Thread.sleep(100);//milliseconds
//        } catch(InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_DYNAMIC_DRAW);
        GL11.glDrawElements(GL11.GL_TRIANGLES, //render mode i.e. what kind of primitives are we constructing our image out of
                size, //Number of vertices to render (there's 6 per image)
                GL11.GL_UNSIGNED_INT, //indicates the type of index values in indices
                size * Integer.SIZE * 0);//index into buffer when to start rendering
    }

//    public static void dynamicDraw(Collection<Entity> entityList, int vertexSize, int verticesPerEntity, int maxEntities) {
//        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(vertexSize * maxEntities);
//        for (Entity entity : entityList) {
//            verticesFloatBuffer.put(entity.getPositions().getValue());
//        }
//        verticesFloatBuffer.flip();
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_DYNAMIC_DRAW);
//        GL11.glDrawElements(GL11.GL_TRIANGLES, //render mode i.e. what kind of primitives are we constructing our image out of
//                verticesPerEntity * entityList.size(), //Number of vertices to render (there's 6 per image)
//                GL11.GL_UNSIGNED_INT, //indicates the type of index values in indices
//                verticesPerEntity * Integer.SIZE * 0);//index into buffer when to start rendering
//    }
}
