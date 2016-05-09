package org.supply.simulator.display.factory;

import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.impl.BasicMenu;
import org.supply.simulator.data.entity.impl.BasicPositions;
import org.supply.simulator.display.MenuFactory;

import static java.lang.System.arraycopy;
import static org.supply.simulator.display.factory.TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE;

/**
 * Created by Brandon on 5/8/2016.
 */
public class TextMenuFactory implements MenuFactory {
    private final float topLeftX, topLeftY, length, width;
    private final String text;

    public TextMenuFactory(float topLeftX, float topLeftY, float length, float width, String text) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.length = length;
        this.width = width;
        this.text = text;
    }

    @Override
    public Menu build() {
        BasicMenu menu = new BasicMenu();
//        menu.setPositions(getUnitPositions(topLeftX, topLeftY, 0, length, width));
//        MenuType type;

//            if (menuTypeMap.containsKey(text)) {
//                type = menuTypeMap.get(text);
//            } else {
//                type = new BasicMenuType();
//                type.setName(text);
//                menuTypeMap.put(text,type);
//            }

        //TODO WHY do I have to cast it to EntityType?
//        menu.setType((EntityType)type);

        return menu;
    }

    private BasicPositions getUnitPositions(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        TexturedVertex v0 = new TexturedVertex();
        TexturedVertex v1 = new TexturedVertex();
        TexturedVertex v2 = new TexturedVertex();
        TexturedVertex v3 = new TexturedVertex();
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(1, 0, 0); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(0, 1, 0); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(0, 0, 1); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        float[] data = new float[4* TEXTURE_VERTEX_TOTAL_SIZE];

        arraycopy(v0.getElements(),0,data, 0, TEXTURE_VERTEX_TOTAL_SIZE);
        arraycopy(v1.getElements(),0,data, TEXTURE_VERTEX_TOTAL_SIZE, TEXTURE_VERTEX_TOTAL_SIZE);
        arraycopy(v2.getElements(),0,data,2* TEXTURE_VERTEX_TOTAL_SIZE, TEXTURE_VERTEX_TOTAL_SIZE);
        arraycopy(v3.getElements(),0,data,3* TEXTURE_VERTEX_TOTAL_SIZE, TEXTURE_VERTEX_TOTAL_SIZE);

        BasicPositions entityData = new BasicPositions();
        entityData.setValue(data);

        return entityData;
    }
}
