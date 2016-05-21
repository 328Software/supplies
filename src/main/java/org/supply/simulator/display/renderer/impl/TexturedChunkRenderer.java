package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.renderer.BufferIDContainer;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.*;

import static java.util.Objects.nonNull;
import static org.supply.simulator.display.renderer.DrawingUtil.*;

/**
 * Created by Alex on 5/8/2016.
 */
public class TexturedChunkRenderer extends RendererBase implements EntityRenderer {

    private static final int NON_TEXTURED_ID = -1337;

    boolean drawStatic = false;
    private boolean oneEntityPerBuffer=false;

    //ASSUME STATIC ENTITIES USE 1 ATLAS, FOR NOW
    //Entity -> (positionsArrayID, vertexAttribID)
    protected HashMap<Entity,BufferIDContainer> staticIdMap;

    //AtlasID -> (positionsArrayID, vertexAttribID)
    protected HashMap<Integer,BufferIDContainer> dynamicIdMap;

    //AtlasID -> List of Entities
    protected HashMap<Integer,Set<Positions>> textureUsersMap;


    public TexturedChunkRenderer () {
        super();
        staticIdMap=new HashMap<>();
        dynamicIdMap=new HashMap<>();
        textureUsersMap = new HashMap<>();
    }



    @Override
    protected void buildEntities(Collection<Entity> entityList) {
        for (Entity entity : entityList) {


            if (drawStatic) {
                //BUILD STATIC ENTITIES
                if (!staticIdMap.containsKey(entity)) {
                    BufferIDContainer bufIDs = allocateOpenGLBuffersStatic(entity.getPositions(),locations);

                    staticIdMap.put(entity,bufIDs);
                } else {
                    logger.error("Static entity already built");
                }
            } else {
                //BUILD DYNAMIC ENTITIES
                for (Positions positions : entity.getPositions()) {

                    //ASSIGN KEY BY ATLAS ID
                    int atlasId;
                    if (nonNull(positions.getTextureKey())) {
                        atlasId=textureEngine.get(positions.getTextureKey()).getTextureId();
                    } else {
                        //ALL NON TEXTURED ENTITIES GO INTO ONE BUFFER
                        atlasId=NON_TEXTURED_ID;
                    }


                    if (dynamicIdMap.containsKey(atlasId)) {
                        //ATLAS ALREADY IN USE, ADD TO CONTAINER
                        textureUsersMap.get(atlasId).add(positions);

                    } else {
                        //CREATE BUF IDS FOR NEW ATLAS
                        BufferIDContainer bufIDs = allocateOpenGLBuffersDynamic(locations);
                        //CHANGE TO TREE SET
                        Set<Positions> positionsSet = new HashSet<>();
                        positionsSet.add(positions);
                        textureUsersMap.put(atlasId,positionsSet);
                        dynamicIdMap.put(atlasId,bufIDs);

                    }

                }
            }

        }

    }



    @Override
    protected void drawEntities(Collection<Entity> entityList) {
        if(drawStatic) {
            for (Map.Entry<Entity,BufferIDContainer> entry : staticIdMap.entrySet()) {
                BufferIDContainer ids = entry.getValue();
                Integer atlasId = textureEngine.get(entry.getKey().getPositions().iterator().next().getTextureKey()).getTextureId();

                //Prepare to draw block of entities
                //    i.e. bind all the opengl buffers, bind texture
                enableArrayBuffer(ids.getPositionsArrayId());
                enableVertexAttribArray(locations, ids.getVertexAttributesId());

                enableIndicesBuffer(staticIndicesBufferId);

                if (atlasId>0) {
                    enableTextureBuffer(atlasId);
                }

                //Draw Entities
                staticDraw(entry.getKey().getPositions());

                //Finish drawing block of entities
                //    i.e. unbind all the opengl buffers, unbind texture
                disableVertexAttribArray(locations);
                disableIndicesBuffer();
                disableArrayBuffer();
                disableTextureBuffer();


            }
        } else {
            for (Map.Entry<Integer,BufferIDContainer> entry : dynamicIdMap.entrySet()) {
                BufferIDContainer ids = entry.getValue();
                Integer atlasId = entry.getKey();

                //Prepare to draw block of entities
                //    i.e. bind all the opengl buffers, bind texture
                enableArrayBuffer(ids.getPositionsArrayId());
                enableVertexAttribArray(locations, ids.getVertexAttributesId());

                enableIndicesBuffer(dynamicIndicesBufferId);

                if (atlasId>0) {
                    enableTextureBuffer(atlasId);
                }

                //Draw Entities
                dynamicDraw2(textureUsersMap.get(atlasId));

                //Finish drawing block of entities
                //    i.e. unbind all the opengl buffers, unbind texture
                disableVertexAttribArray(locations);
                disableIndicesBuffer();
                disableArrayBuffer();
                disableTextureBuffer();


            }
        }
    }

    @Override
    protected void destroyEntities(Collection<Entity> entities) {

    }



    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }

    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }


}
