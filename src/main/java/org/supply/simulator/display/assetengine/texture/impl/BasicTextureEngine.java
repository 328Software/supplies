package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.display.assetengine.texture.AbstractTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
/**
 * Created by Alex on 7/13/2014.
 */
public class BasicTextureEngine
        extends AbstractTextureEngine<EntityType>
        implements TextureEngine<EntityType> {


    @Override
    protected String lookupTextureFileName(EntityType key) {
        return getTextureData(key).fileName;
    }

    @Override
    protected float[] lookupTextureSubInfo(EntityType key) {
        return getTextureData(key).subInfo;
    }

    private TextureData getTextureData(EntityType key) {

        TextureData data=null;
        if (key instanceof MenuType) {
            data = generateTextureData(((MenuType)key).getName());
        } else if (key instanceof UnitType) {
            data = generateTextureData(((UnitType)key).getName());
        } else if (key instanceof ChunkType) {
            logger.warn("have not implemented ChunkType for texture engine");
        } else {
            logger.error("Incorrect EntityType for texture engine");
        }

        return data;
    }

    private TextureData generateTextureData(String name) {
        TextureData data = new TextureData();
        switch (name) {
            case "1":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 11, 20};
                break;
            case "2":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{12, 0, 23, 20};
                break;
            case "3":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{24, 0, 35, 20};
                break;
            case "4":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{36, 0, 47, 20};
                break;
            case "5":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{48, 0, 59, 20};
                break;
            case "6":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{60, 0, 71, 20};
                break;
            case "7":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{72, 0, 83, 20};
                break;
            case "8":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{84, 0, 95, 20};
                break;
            case "9":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{96, 0, 107, 20};
                break;
            case "0":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{108, 0, 119, 20};
                break;
            case "A":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{120, 0, 131, 20};
                break;
            case "B":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{132, 0, 143, 20};
                break;
            case "C":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{144, 0, 155, 20};
                break;
            case "D":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{156, 0, 167, 20};
                break;
            case "E":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{168, 0, 179, 20};
                break;
            case "F":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{180, 0, 191, 20};
                break;
            case "G":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{192, 0, 203, 20};
                break;
            case "H":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{204, 0, 215, 20};
                break;
            case "I":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{216, 0, 227, 20};
                break;
            case "J":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{228, 0, 239, 20};
                break;
            case "K":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{240, 0, 251, 20};
                break;
            case "L":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{252, 0, 263, 20};
                break;
            case "M":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{264, 0, 275, 20};
                break;
            case "N":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{276, 0, 287, 20};
                break;
            case "O":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{288, 0, 299, 20};
                break;
            case "P":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{300, 0, 311, 20};
                break;
            case "Q":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{312, 0, 323, 20};
                break;
            case "R":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{324, 0, 335, 20};
                break;
            case "S":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{336, 0, 347, 20};
                break;
            case "T":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{348, 0, 359, 20};
                break;
            case "U":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{360, 0, 371, 20};
                break;
            case "V":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{372, 0, 383, 20};
                break;
            case "W":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{384, 0, 395, 20};
                break;
            case "X":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{396, 0, 407, 20};
                break;
            case "Y":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{408, 0, 419, 20};
                break;
            case "Z":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{420, 0, 431, 20};
                break;
            case "a":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "b":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "c":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "d":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "e":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "f":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "g":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "h":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "i":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "j":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "k":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "l":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "m":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "n":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "o":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "p":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 20};
                break;
            case "q":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "r":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "s":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "t":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "u":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "v":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "w":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "x":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "y":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case "z":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 0, 40};
                break;
            case " ":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{492, 21, 503, 40};
                break;
            case "textures/text2.png":
                data.fileName = "textures/text2.png";
                data.subInfo = new float[]{0, 0, 640, 400};
                break;
            case "textures/text.png":
                data.fileName = "textures/text.png";
                data.subInfo = new float[]{0, 0, 640, 400};
                break;
            case "textures/alexsface.png":
                data.fileName = "textures/alexsface.png";
                data.subInfo = new float[]{0, 0, 500, 500};
                break;
            case "textures/rect.png":
                data.fileName = "textures/rect.png";
                data.subInfo = new float[]{0, 0, 996, 297};
                break;
            default:logger.error("CANNOT FIND TEXTURE");
                break;
        }

        return  data;
    }

    private class TextureData {
        public String fileName;
        public float[] subInfo;
    }
}
