package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.display.renderer.AbstractRenderer;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 7/21/2014.
 */
public class BasicUnitRenderer extends AbstractRenderer<BasicUnit> implements EntityRenderer<BasicUnit> {
//    public static final int VERTICES_PER_ENTITY = 4;
//
//    // The amount of bytes an element has
//    public static final int elementBytes = 4;
//
//    // Elements per parameter
//    public static final int positionElementCount = 4;
//    public static final int colorElementCount = 4;
//    public static final int textureElementCount = 2;
//
//    // Bytes per parameter
//    public static final int positionBytesCount = positionElementCount * elementBytes;
//    public static final int colorByteCount = colorElementCount * elementBytes;
//    public static final int textureByteCount = textureElementCount * elementBytes;
//
//    // Byte offsets per parameter
//    public static final int positionByteOffset = 0;
//    public static final int colorByteOffset = positionByteOffset + positionBytesCount;
//    public static final int textureByteOffset = colorByteOffset + colorByteCount;
//
//    // The amount of elements that a vertex has
//    public static final int elementCount = positionElementCount +
//            colorElementCount + textureElementCount;
//    // The size of a vertex in bytes, like in C/C++: sizeof(Vertex)
//    public static final int stride = positionBytesCount + colorByteCount +
//            textureByteCount;
//
//    private final int ENTITY_MAX =100;
//    private final int BUFFER_ROOM = 50;
//
//    private final int VERTEX_SIZE = 40;
//    private final int INDEX_PER_VERTEX = 6;
//
//    protected int[] locations;
//
//    private int indicesCount;
//
//    //    protected PriorityQueue<Unit> unitQueue;
//    protected ArrayList<Unit> unitList;
////    protected EntityQueue entities;
//
//    protected int indicesBufferId = -1;
//
//    private HashMap<AtlasType,BufferIds> idMap;
//
//    int[] indices = {
//            0, 1, 2,
//            2, 3, 0
//    };
//    private TextureEngine<UnitType> textureEngine;
//
//    public BasicUnitRenderer() {
//        idMap = new HashMap<>();
//        unitList = new ArrayList<>();
//        locations = new int[] {0,1,2};
//    }
//
//
//
//    @Override
//    public void build(Collection<Unit> renderables) {
//        for (Unit renderable : renderables) {
//            renderable.getType().setTextureHandle(textureEngine.get((UnitType)renderable.getType()));
//            unitList.add(renderable);
//
//            if (!idMap.containsValue(renderable.getType().getTextureHandle().getAtlasType())) {
//                BufferIds bufferIds = new BufferIds();
//                bufferIds.textureId = renderable.getType().getTextureHandle().getAtlasType().getTextureId();
//                //entities.getTextureHandle(renderable.getTextureHandle().getSubAtlasType().getAtlasType()).getTextureId();
//                bufferIds.positionsArrayId = GL15.glGenBuffers();
//                bufferIds.vertexAttributesId  = GL30.glGenVertexArrays();
//
//                GL30.glBindVertexArray(bufferIds.vertexAttributesId);
//                GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferIds.positionsArrayId);
//                GL20.glVertexAttribPointer(locations[0], positionElementCount, GL11.GL_FLOAT,
//                        false, stride, positionByteOffset);
//                // Put the color components in attribute list 1
//                GL20.glVertexAttribPointer(locations[1], colorElementCount, GL11.GL_FLOAT,
//                        false, stride, colorByteOffset);
//                // Put the texture coordinates in attribute list 2
//                GL20.glVertexAttribPointer(locations[2], textureElementCount, GL11.GL_FLOAT,
//                        false, stride, textureByteOffset);
//
////                bufferIds.texCoordId=GL15.glGenBuffers();
//
//
//
//                idMap.put(renderable.getType().getTextureHandle().getAtlasType(),bufferIds);
//                GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//            }
//        }
//
//        Collections.sort(unitList);
//        if (indicesBufferId<0) {
//            createIndices(ENTITY_MAX);
//        }
//
//    }
//
//    @Override
//    public void render(Collection<Unit> renderables) {
//        if (renderables.size()>0) renderAtlas(unitList,0);
//
//    }
//
//    private void renderAtlas(ArrayList<Unit> renderables, int atlasIndex) {
//        AtlasType    currentAtlas    = unitList.get(atlasIndex).getType().getTextureHandle().getAtlasType();
//
//        BufferIds bufferIds = idMap.get(currentAtlas);
//        GL30.glBindVertexArray(bufferIds.vertexAttributesId);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferIds.positionsArrayId);
//
//        GL13.glActiveTexture(GL13.GL_TEXTURE0);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, bufferIds.textureId);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
//        GL20.glEnableVertexAttribArray(locations[0]);
//        GL20.glEnableVertexAttribArray(locations[1]);
//        GL20.glEnableVertexAttribArray(locations[2]);
//        GL13.glActiveTexture(GL13.GL_TEXTURE0);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, bufferIds.textureId);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
//
//        //TODO variable buffer size
//        //FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(VERTEX_SIZE*(entities.count(atlasType)+BUFFER_ROOM));
//
//        float tWidth = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D,0,GL11.GL_TEXTURE_WIDTH);
//        float tHeight = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D,0,GL11.GL_TEXTURE_HEIGHT);
//
//        FloatBuffer verticesFloatBuffer = BufferUtils.createFloatBuffer(VERTEX_SIZE * ENTITY_MAX);
//        int count = atlasIndex;
//        while(count<renderables.size()) {
//            if (!renderables.get(count).getType().getTextureHandle().getAtlasType().equals(currentAtlas)) {
//                break;
//            }
//            //TODO this needs to be done on Entity generations, should be in bad engine?
//            float[] data = renderables.get(count).getPositions().getValue();
//            data[8] =(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[0]/tWidth;  //X0
//            data[9] =(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[1]/tHeight; //Y0
//
//            data[18]=(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[0]/tWidth;  //X0
//            data[19]=(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[3]/tHeight; //Y1
//
//            data[28]=(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[2]/tWidth;  //X1
//            data[29]=(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[3]/tHeight; //Y1
//
//            data[38]=(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[2]/tWidth;  //X1
//            data[39]=(float)renderables.get(count).getType().getTextureHandle().getSubInfo()[1]/tHeight; //Y0
//            verticesFloatBuffer.put(data);
//            count = count +1;
//
//        }
//
//        verticesFloatBuffer.flip();
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_DYNAMIC_DRAW);
//
//        GL11.glDrawElements(GL11.GL_TRIANGLES,
//                INDEX_PER_VERTEX * (count-atlasIndex),
//                GL11.GL_UNSIGNED_INT,
//                INDEX_PER_VERTEX * Integer.SIZE * (atlasIndex));
//        atlasIndex=count;
//
//        GL20.glDisableVertexAttribArray(locations[0]);
//        GL20.glDisableVertexAttribArray(locations[1]);
//        GL20.glDisableVertexAttribArray(locations[2]);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//        GL30.glBindVertexArray(0);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//
//        if (atlasIndex <renderables.size()) {
//            renderAtlas(renderables,atlasIndex);
//        }
//
//
//    }
//
//
//    @Override
//    public void destroy(Collection<Unit> renderables) {
//        for (Unit renderable : renderables) {
//            textureEngine.done((UnitType)renderable.getType());
//            unitList.remove(renderable);
//        }
//    }
//
//    @Override
//    public void destroyAll() {
//        for (Unit renderable : unitList) {
//            textureEngine.done((UnitType)renderable.getType());
//        }
//        unitList.clear();
//    }
//
//
//
//    private void createIndices(int numberOfEntities) {
//
//        indicesBufferId = GL15.glGenBuffers();
//        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(6*numberOfEntities);
//
//
//        //TODO WE SHOULDN'T GENERATE THIS ON THE FLY
//        for (int i = 0; i < 1; i++) {
//            for (int j = 0; j < numberOfEntities; j++) {
//                int offset = ((i * numberOfEntities + j) * 4);
//
//                indicesBuffer.put((offset));
//                indicesBuffer.put((offset + 1));
//                indicesBuffer.put((offset + 2));
//                indicesBuffer.put((offset + 2));
//                indicesBuffer.put((offset + 3));
//                indicesBuffer.put((offset));
//            }
//        }
//        indicesCount = indicesBuffer.capacity();
//        indicesBuffer.flip();
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
//        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//    }
//
//
//    @Override
//    public void setTextureEngine(TextureEngine textureEngine) {
//        this.textureEngine =  textureEngine;
//    }
//
//
//
//    private class BufferIds {
//        public BufferIds() {
//            size = 0;
//        }
//
//        //        protected int texCoordId;
//        protected int vertexAttributesId;
//        protected int positionsArrayId;
//        protected int indicesBufferId;
//        protected int textureId;
//        protected int textureWidth;
//        protected int textureHeight;
//
//        protected int size;
//    }
//
//
//    @Override
//    public void setAttributeLocations(int[] locations) {
//        this.locations=locations;
//    }
//
//    @Override
//    public int[] getAttributeLocations() {
//        return locations;
//    }
//
//

}
