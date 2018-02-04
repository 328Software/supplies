package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.renderer.BufferIDContainer;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.renderer.RendererBase;

import java.util.*;

import static java.util.Objects.nonNull;
import static org.supply.simulator.display.renderer.DrawingUtil.*;
import static org.supply.simulator.display.renderer.DrawingUtil.disableTextureBuffer;

/**
 * Created by Alex on 5/6/2016.
 */
public class Renderer extends RendererBase implements EntityRenderer {
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


    public Renderer () {
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
                    BufferIDContainer bufIDs = allocateOpenGLBuffersStatic(entity.getPositions());

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
                        BufferIDContainer bufIDs
                                = allocateOpenGLBuffersDynamic(Collections.singleton(positions));
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

                //todo fix this laziness, idk know how
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
                dynamicDraw(textureUsersMap.get(atlasId));

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
        for (Entity entity : entities) {
            if (drawStatic) {


            } else {


            }
        }

    }



    public void setDrawStatic(boolean drawStatic) {
        this.drawStatic = drawStatic;
    }

    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
        this.oneEntityPerBuffer = oneEntityPerBuffer;
    }

//    boolean drawStatic = false;
//    boolean oneEntityPerBuffer = false;
//
//    protected void buildEntities(Collection<Entity> entities) {
//
//        //Load texture atlases
//        for (Entity entity : entities) {
//
//            if (nonNull(entity.getAtlas())) {
//                //Entity is textured;
//                Atlas atlas = entity.getAtlas();
//
//                if (!idMap.containsKey(atlas)) {
//                    OpenGLBufferIDBag openGLBufferIDBag = allocateOpenGLBuffers(atlas, locations);
//
//                    openGLBufferIDBag.add(entity);
//
//                    idMap.put(atlas, openGLBufferIDBag);
//                } else {
//                    idMap.get(atlas).add(entity);
//                }
//            } else {
//                //Entity is NOT textured;
//
//            }
//        }
//    }
//
//    protected void drawEntities(Collection<Entity> entities) {
//
//        for (OpenGLBufferIDBag<Entity> data : idMap.values()) {
//
//            //Prepare to draw block of entities
//            //    i.e. bind all the opengl buffers, bind texture
//            enableArrayBuffer(data.getPositionsArrayId());
//            enableVertexAttribArray(locations, data.getVertexAttributesId());
//            enableIndicesBuffer(staticIndicesBufferId);
//
//            if (nonNull(data.getTextureId())) {
//                enableTextureBuffer(data.getTextureId());
//            }
//
//            //Draw Entities
//            if(drawStatic) {
////                staticDraw(entities);
//            } else {
//                dynamicDraw(entities);
//            }
//
//            //Finish drawing block of entities
//            //    i.e. unbind all the opengl buffers, unbind texture
//            disableVertexAttribArray(locations);
//            disableIndicesBuffer();
//            disableArrayBuffer();
//            disableTextureBuffer();
//
//        }
//
//    }
//
//    protected void destroyEntities(Collection<Entity> entities) {
//        for (Entity entity : entities) {
//            for(Positions positions : entity.getPositions()) {
//                textureEngine.done(positions.getTextureKey());
//            }
////            idMap2.get(entity.getTextureKey()).remove(entity);
//            //TODO when to delete atlas data??
//        }
//    }
//
//
////    protected void setIndicesBufferId() {
////
////
////        staticIndicesBufferId = (indexEngine).get(MapUtils.newEntry(maxEntities,1)).getIndexId();
////    }
//
//    public void setDrawStatic(boolean drawStatic) {
//        this.drawStatic = drawStatic;
//    }
//    public void setOneEntityPerBuffer(boolean oneEntityPerBuffer) {
//        this.oneEntityPerBuffer = oneEntityPerBuffer;
//    }
}
