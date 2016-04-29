package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.display.assetengine.texture.AbstractTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;

/**
 * Created by Alex on 7/13/2014.
 */
public class BasicTextureEngine
        extends AbstractTextureEngine<UnitType>
        implements TextureEngine<UnitType> {
    @Override
    protected String getAtlasFileName(UnitType key) {
        return key.getTextureType().getFileName();
    }

//    private HashMap<TextureType,AtlasType> atlasMap;
//
//    @Override
//    protected TextureHandle createHandle(UnitType key) {
//        TextureHandle handle = new BasicTextureHandle();
//
//        AtlasType atlasType;
//        if (atlasMap.containsKey(key.getTextureType())) {
//            atlasType = atlasMap.get(key.getTextureType());
//        } else {
//            atlasType = new BasicAtlasType();
//            atlasType.setTextureId(loadPNGTexture2D(key.getTextureType().getFileName(), GL13.GL_TEXTURE0););
//            atlasMap.put(key.getTextureType(),atlasType);
//        }
//
//        handle.setAtlasType(atlasType);
//
//
//        return handle;
//    }
//
//
//    private int loadPNGTexture2D(String filename, int textureUnit) {
//        ByteBuffer buf = null;
//        int tWidth = 0;
//        int tHeight = 0;
//
//        try {
//            // Open the PNG file as an InputStream
//            InputStream in = ClassLoader.getSystemResourceAsStream(filename);
//            // Link the PNG decoder to this stream
//            PNGDecoder decoder = new PNGDecoder(in);
//
//            // Get the width and height of the texture
//            tWidth = decoder.getWidth();
//            tHeight = decoder.getHeight();
//
//
//            // Decode the PNG file in a ByteBuffer
//            buf = ByteBuffer.allocateDirect(
//                    4 * decoder.getWidth() * decoder.getHeight());
//            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
//            buf.flip();
//
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Create a new texture object in memory and bind it
//        int texId = GL11.glGenTextures();
//        GL13.glActiveTexture(textureUnit);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);
//
//        // All RGB bytes are aligned to each other and each component is 1 byte
//        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
//
//        // Upload the texture data and generate mip maps (for scaling)
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, tWidth, tHeight, 0,
//                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
//        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
//
//        // Setup the ST coordinate system
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//
//        // Setup what to do when the texture has to be scaled
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
//                GL11.GL_LINEAR);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
//                GL11.GL_LINEAR_MIPMAP_LINEAR);
//
//        return texId;
//    }
//
//    @Override
//    protected void destroyHandle(UnitType key) {
//        TextureHandle handle = handleMap.remove(key);
//
//        if (handle.getAtlasType().count()==0) {
//            GL11.glDeleteTextures(handle.getAtlasType().getTextureId());
//            atlasMap.remove(key.getTextureType());
//        }
//
//    }
}
