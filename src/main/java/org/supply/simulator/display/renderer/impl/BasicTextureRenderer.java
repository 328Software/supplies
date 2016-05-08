package org.supply.simulator.display.renderer.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.data.statistic.entity.UnitPositions;
import org.supply.simulator.display.assetengine.texture.AtlasType;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.renderer.AbstractTextureRenderer;
import org.supply.simulator.display.renderer.EntityRenderer;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alex on 5/6/2016.
 */
public class BasicTextureRenderer extends AbstractTextureRenderer<BasicUnit> implements EntityRenderer<BasicUnit>{
    public static final int VERTICES_PER_ENTITY = 4;

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
    public static final int stride = POSITION_BYTES_COUNT + COLOR_BYTE_COUNT +
            TEXTURE_BYTE_COUNT;

    private final int ENTITY_MAX =100;
    private final int BUFFER_ROOM = 50;

    private final int VERTEX_SIZE = 40;
    private final int INDEX_PER_VERTEX = 6;

    protected int indicesBufferId = -1;

    protected HashMap<AtlasType,AtlasData> idMap;

    protected HashMap<EntityType,AtlasData> idMap2;



    @Override
    public void build(Collection<BasicUnit> renderables) {
        if (indicesBufferId < 0) {
            indicesBufferId = indexEngine.get(ENTITY_MAX).getIndexId();
        }



        for (BasicUnit renderable : renderables) {
            TextureHandle texture = textureEngine.get(renderable.getType());

            fillEntityDataWithTextureData(renderable, texture);

            AtlasType atlas = texture.getAtlasType();
            if (!idMap.containsKey(atlas)) {
                AtlasData atlasData = createAtlasData(atlas,locations);

                atlasData.add(renderable);

                idMap.put(atlas,atlasData);
            } else {
                idMap.get(atlas).add(renderable);
            }


        }


    }

    @Override
    public void render(Collection<BasicUnit> renderables) {
        //TODO not using input to this method
        for (AtlasData data : idMap.values()) {
            GL30.glBindVertexArray(data.vertexAttributesId);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, data.positionsArrayId);

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, data.textureId);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
            GL20.glEnableVertexAttribArray(locations[0]);
            GL20.glEnableVertexAttribArray(locations[1]);
            GL20.glEnableVertexAttribArray(locations[2]);
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, data.textureId);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);

            FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(VERTEX_SIZE * ENTITY_MAX);

            for (Entity entity : data.getEntityList()) {
                UnitPositions pos= null;
                //TODO we really need to combine unit and chunk positions to be able to clean this up
                if (entity instanceof Menu) {
                    verticesFloatBuffer.put(((Menu)entity).getPositions().getValue());
                } else if (entity instanceof Unit) {
                    verticesFloatBuffer.put(((Unit)entity).getPositions().getValue());
                } else {
                    logger.error("INVALID entity type");
                }
            }
            verticesFloatBuffer.flip();
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_DYNAMIC_DRAW);


            GL11.glDrawElements(GL11.GL_TRIANGLES,
                    0,
                    GL11.GL_UNSIGNED_INT,
                    INDEX_PER_VERTEX * Integer.SIZE * data.getEntityList().size());

            GL20.glDisableVertexAttribArray(locations[0]);
            GL20.glDisableVertexAttribArray(locations[1]);
            GL20.glDisableVertexAttribArray(locations[2]);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            GL30.glBindVertexArray(0);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);


        }
    }

    @Override
    public void destroy(Collection<BasicUnit> renderables) {
        for (BasicUnit renderable : renderables) {
            textureEngine.done(renderable.getType());
            idMap2.get(renderable.getType()).remove(renderable);
            //TODO when to delete atlas data??
        }

    }

    @Override
    public void destroyAll() {
        //TODO fill out autogeneration whatever

    }

    private void fillEntityDataWithTextureData(Entity entity, TextureHandle texture) {


        float[] data = null;
        //TODO we really need to combine unit and chunk positions to be able to clean this up
        UnitPositions pos= null;

        if (entity instanceof Menu) {
            pos = ((Menu)entity).getPositions();
        } else if (entity instanceof Unit) {
            pos = ((Unit)entity).getPositions();
        } else {
            logger.error("INVALID entity type");
        }


        //TODO this needs to be done on Entity generation, should be in bad engine?
        pos.getValue()[8] =(float)entity.getType().getTextureHandle().getSubInfo()[0]/texture.getAtlasType().getWidth();  //X0
        pos.getValue()[9] =(float)entity.getType().getTextureHandle().getSubInfo()[1]/texture.getAtlasType().getHeight(); //Y0

        pos.getValue()[18]=(float)entity.getType().getTextureHandle().getSubInfo()[0]/texture.getAtlasType().getWidth();  //X0
        pos.getValue()[19]=(float)entity.getType().getTextureHandle().getSubInfo()[3]/texture.getAtlasType().getHeight(); //Y1

        pos.getValue()[28]=(float)entity.getType().getTextureHandle().getSubInfo()[2]/texture.getAtlasType().getWidth();  //X1
        pos.getValue()[29]=(float)entity.getType().getTextureHandle().getSubInfo()[3]/texture.getAtlasType().getHeight(); //Y1

        pos.getValue()[38]=(float)entity.getType().getTextureHandle().getSubInfo()[2]/texture.getAtlasType().getWidth();  //X1
        pos.getValue()[39]=(float)entity.getType().getTextureHandle().getSubInfo()[1]/texture.getAtlasType().getHeight(); //Y0

    }

    private AtlasData createAtlasData(AtlasType atlas, int[] locations) {
        AtlasData ids = new AtlasData();
        ids.textureId = atlas.getTextureId();
        ids.positionsArrayId = GL15.glGenBuffers();
        ids.vertexAttributesId  = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(ids.vertexAttributesId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ids.positionsArrayId);
        GL20.glVertexAttribPointer(locations[0], POSITION_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, stride, POSITION_BYTE_OFFSET);
        // Put the color components in attribute list 1
        GL20.glVertexAttribPointer(locations[1], COLOR_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, stride, COLOR_BYTE_OFFSET);
        // Put the texture coordinates in attribute list 2
        GL20.glVertexAttribPointer(locations[2], TEXTURE_ELEMENT_COUNT, GL11.GL_FLOAT,
                false, stride, TEXTURE_BYTE_OFFSET);


        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        return ids;
    }



    private class AtlasData {
        public AtlasData() {
            size = 0;
            list = new ArrayList<>();
        }


        public void add(Entity entity) {
            if (!list.contains(entity)) {
                list.add(entity);
            } else {
                logger.error("Cannot add entity to screen, already rendered");
            }

        }

        public void remove(Entity entity) {
            if (list.contains(entity)) {
                list.remove(entity);
            } else {
                logger.error("Cannot delete entity from screen, not rendered");
            }
        }

        public ArrayList<Entity> getEntityList() {
            return list;
        }
        //        protected int texCoordId;
        protected int vertexAttributesId;
        protected int positionsArrayId;
        protected int indicesBufferId;
        protected int textureId;

        protected int size;

        private ArrayList<Entity> list;
    }

}
