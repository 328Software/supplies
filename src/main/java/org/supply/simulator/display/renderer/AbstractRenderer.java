package org.supply.simulator.display.renderer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.entity.impl.BasicMenu;
import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.data.statistic.entity.UnitPositions;
import org.supply.simulator.data.statistic.entity.impl.BasicUnitPositions;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.assetengine.texture.AtlasType;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.renderer.impl.AtlasRenderData;
import org.supply.simulator.logging.HasLogger;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alex on 5/6/2016.
 */
public abstract class AbstractRenderer<V extends Entity> extends HasLogger implements  EntityRenderer<V> {

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

    protected final int ENTITY_MAX =100;

    protected final int VERTEX_SIZE = 40;
    protected final int VERTICES_PER_ENTITY = 6;


    protected TextureEngine<EntityType> textureEngine;

    protected UnitIndexEngine indexEngine;

    protected int[] locations;

    protected int indicesBufferId = -1;

    protected HashMap<AtlasType,AtlasRenderData> idMap;

    protected HashMap<EntityType,AtlasRenderData> idMap2;

    public AbstractRenderer() {
        super();
        idMap = new HashMap<>();
        idMap2 = new HashMap<>();

    }




    @Override
    public void build(Collection<V> entities) {
        if (indicesBufferId < 0) {
            indicesBufferId = indexEngine.get(ENTITY_MAX).getIndexId();
        }



        for (V entity : entities) {
            TextureHandle texture = textureEngine.get(entity.getType());

            fillEntityDataWithTextureData(entity, texture);

            AtlasType atlas = texture.getAtlasType();
            if (!idMap.containsKey(atlas)) {
                AtlasRenderData atlasRenderData = createAtlasData(atlas,locations);

                atlasRenderData.add(entity);

                idMap.put(atlas, atlasRenderData);
            } else {
                idMap.get(atlas).add(entity);
            }


        }


    }

    //TODO currently not using input to this method
    @Override
    public void render(Collection<V> entities) {
        int count=0;
        for (AtlasRenderData data : idMap.values()) {
            GL30.glBindVertexArray(data.getVertexAttributesId());
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, data.getPositionsArrayId());

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, data.getTextureId());
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
            GL20.glEnableVertexAttribArray(locations[0]);
            GL20.glEnableVertexAttribArray(locations[1]);
            GL20.glEnableVertexAttribArray(locations[2]);
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, data.getTextureId());
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


            GL11.glDrawElements(GL11.GL_TRIANGLES, //render mode i.e. what kind of primitives are we constructing our image out of
                    VERTICES_PER_ENTITY *data.getEntityList().size(), //Number of vertices to render (theres 6 per image)
                    GL11.GL_UNSIGNED_INT, //indicates the type of index values in indices
                    VERTICES_PER_ENTITY * Integer.SIZE * 0);

            GL20.glDisableVertexAttribArray(locations[0]);
            GL20.glDisableVertexAttribArray(locations[1]);
            GL20.glDisableVertexAttribArray(locations[2]);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            GL30.glBindVertexArray(0);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);


            count=count+1;
        }
    }

    @Override
    public void destroy(Collection<V> entities) {
        for (V entity : entities) {
            textureEngine.done(entity.getType());
            idMap2.get(entity.getType()).remove(entity);
            //TODO when to delete atlas data??
        }

    }

    @Override
    public void destroyAll() {
        //TODO fill out autogeneration whatever

    }

    protected void fillEntityDataWithTextureData(Entity entity, TextureHandle texture) {


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


        //TODO can this be done on Entity generation?
        pos.getValue()[8] =(float)texture.getSubInfo()[0]/texture.getAtlasType().getWidth();  //X0
        pos.getValue()[9] =(float)texture.getSubInfo()[1]/texture.getAtlasType().getHeight(); //Y0

        pos.getValue()[18]=(float)texture.getSubInfo()[0]/texture.getAtlasType().getWidth();  //X0
        pos.getValue()[19]=(float)texture.getSubInfo()[3]/texture.getAtlasType().getHeight(); //Y1

        pos.getValue()[28]=(float)texture.getSubInfo()[2]/texture.getAtlasType().getWidth();  //X1
        pos.getValue()[29]=(float)texture.getSubInfo()[3]/texture.getAtlasType().getHeight(); //Y1

        pos.getValue()[38]=(float)texture.getSubInfo()[2]/texture.getAtlasType().getWidth();  //X1
        pos.getValue()[39]=(float)texture.getSubInfo()[1]/texture.getAtlasType().getHeight(); //Y0

    }

    protected AtlasRenderData createAtlasData(AtlasType atlas, int[] locations) {
        AtlasRenderData ids = new AtlasRenderData();
        ids.setTextureId(atlas.getTextureId());
        ids.setPositionsArrayId(GL15.glGenBuffers());
        ids.setVertexAttributesId(GL30.glGenVertexArrays());

        GL30.glBindVertexArray(ids.getVertexAttributesId());
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ids.getPositionsArrayId());
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


    @Override
    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine =  textureEngine;
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
    public void setIndexEngine(IndexEngine indexEngine) {
        //TODO this casting is not good!
        this.indexEngine=(UnitIndexEngine)indexEngine;

    }
}
