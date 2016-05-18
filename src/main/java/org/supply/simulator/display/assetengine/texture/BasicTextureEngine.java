package org.supply.simulator.display.assetengine.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.AssetEngine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static java.util.Objects.nonNull;

/**
 * Created by Alex on 5/8/2016.
 */
public class BasicTextureEngine
        extends AbstractAssetEngine<String, TextureHandle>
        implements AssetEngine<String, TextureHandle>{

            private HashMap<String,Atlas> atlasMap;

            public BasicTextureEngine() {
                atlasMap = new HashMap<>();
            }

            @Override
            protected TextureHandle createHandle(String key) {
                TextureHandle handle = new TextureHandle();
                String fileName = lookupTextureFileName(key);


                Atlas atlas;
                if (atlasMap.containsKey(fileName)) {
                    atlas = atlasMap.get(fileName);
                } else {
                    atlas = loadPNGTexture2D(fileName, GL13.GL_TEXTURE0);
                    atlasMap.put(fileName, atlas);
                }

                handle.setAtlas(atlas);
                handle.setSubInfo(lookupTextureSubInfo(key));


                return handle;
            }



            private Atlas loadPNGTexture2D(String filename, int textureUnit) {

                ByteBuffer buf = null;
                int tWidth = 0;
                int tHeight = 0;

                try {
                    // Open the PNG file as an InputStream
                    InputStream in = ClassLoader.getSystemResourceAsStream(filename);
                    // Link the PNG decoder to this stream
                    PNGDecoder decoder = new PNGDecoder(in);

                    // Get the width and height of the texture
                    tWidth = decoder.getWidth();
                    tHeight = decoder.getHeight();


                    // Decode the PNG file in a ByteBuffer
                    buf = ByteBuffer.allocateDirect(
                            4 * decoder.getWidth() * decoder.getHeight());
                    decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
                    buf.flip();

                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Create a new texture object in memory and bind it
                int texId = GL11.glGenTextures();
                GL13.glActiveTexture(textureUnit);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);

                // All RGB bytes are aligned to each other and each component is 1 byte
                GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

                // Upload the texture data and generate mip maps (for scaling)
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, tWidth, tHeight, 0,
                        GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
                GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

                // Setup the ST coordinate system
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

                // Setup what to do when the texture has to be scaled
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
                        GL11.GL_LINEAR);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
                        GL11.GL_LINEAR_MIPMAP_LINEAR);

                Atlas atlas = new Atlas();
                atlas.setFileName(filename);
                atlas.setTextureId(texId);
                atlas.setHeight(tHeight);
                atlas.setWidth(tWidth);
                return atlas;
            }

            @Override
            protected void destroyHandle(String key) {
                TextureHandle handle = handleMap.remove(key);

                if (handle.getAtlas().count()==0) {
                    GL11.glDeleteTextures(handle.getAtlas().getTextureId());
                    atlasMap.remove(lookupTextureFileName(key));
                }

            }

        private String lookupTextureFileName(String key) {
        return generateTextureData(key).fileName;
    }
            private float[] lookupTextureSubInfo(String key) {
            return generateTextureData(key).subInfo;
     }

            private TextureData generateTextureData(String name) {
        TextureData data = new TextureData();


        if(nonNull(name)) {
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
                case "Ground1":
                    data.fileName = "textures/ground.png";
                    data.subInfo = new float[]{0, 0, 63, 63};
                    break;
                case "Ground2":
                    data.fileName = "textures/ground.png";
                    data.subInfo = new float[]{0, 64, 127, 63};
                    break;
                case "Ground3":
                    data.fileName = "textures/ground.png";
                    data.subInfo = new float[]{0, 64, 63, 127};
                    break;
                case "Ground4":
                    data.fileName = "textures/ground.png";
                    data.subInfo = new float[]{64, 64, 127, 127};
                    break;
                default:
                    logger.error("CANNOT FIND TEXTURE");
                    break;
            }
        }

        return  data;
    }


    protected class TextureData {
        public String fileName;
        public float[] subInfo;
    }

}
