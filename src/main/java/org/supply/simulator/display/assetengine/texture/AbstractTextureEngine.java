package org.supply.simulator.display.assetengine.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicAtlasType;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureHandle;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * Created by Alex on 9/14/2014.
 */
public abstract class AbstractTextureEngine<K extends EntityType>
        extends AbstractAssetEngine<K,TextureHandle>
        implements TextureEngine<K>  {

    private HashMap<String,AtlasType> atlasMap;

    protected AbstractTextureEngine() {
        atlasMap = new HashMap<>();
    }

    @Override
    protected TextureHandle createHandle(K key) {
        TextureHandle handle = new BasicTextureHandle();
        String fileName = lookupTextureFileName(key);


        AtlasType atlasType;
        if (atlasMap.containsKey(fileName)) {
            atlasType = atlasMap.get(fileName);
        } else {
            atlasType = loadPNGTexture2D(fileName, GL13.GL_TEXTURE0);
            atlasMap.put(fileName,atlasType);
        }

        handle.setAtlasType(atlasType);
        handle.setSubInfo(lookupTextureSubInfo(key));


        return handle;
    }

    private BasicAtlasType loadPNGTexture2D(String filename, int textureUnit) {

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

        BasicAtlasType atlasType = new BasicAtlasType();
        atlasType.setFileName(filename);
        atlasType.setTextureId(texId);
        atlasType.setHeight(tHeight);
        atlasType.setWidth(tWidth);
        return atlasType;
    }

    @Override
    protected void destroyHandle(K key) {
        TextureHandle handle = handleMap.remove(key);

        if (handle.getAtlasType().count()==0) {
            GL11.glDeleteTextures(handle.getAtlasType().getTextureId());
            atlasMap.remove(lookupTextureFileName(key));
        }

    }

    protected abstract String lookupTextureFileName(K key);
    protected abstract float[] lookupTextureSubInfo(K key);


}
