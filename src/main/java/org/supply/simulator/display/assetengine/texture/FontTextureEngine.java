package org.supply.simulator.display.assetengine.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL21;
import org.lwjgl.opengl.GL30;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.AssetEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;

import static java.awt.image.BufferedImage.TYPE_4BYTE_ABGR;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_UNSIGNED_INT_8_8_8_8_REV;
import static org.lwjgl.opengl.GL30.GL_BGRA_INTEGER;

/**
 * Created by Brandon on 5/19/2016.
 */
public class FontTextureEngine extends AbstractAssetEngine<String, TextureHandle>
        implements TextureEngine {
    private static final String DEFAULT_FONT = "font";
    private static final String DEFAULT_DIR = "textures/";
    public static final int CHARACTER_PER_ROW = 32;
    public static final int ROWS = 3;

    private HashMap<String,Atlas> atlasMap;

    public FontTextureEngine() {
        atlasMap = new HashMap<>();
    }

    @Override
    protected TextureHandle createHandle(String key) {
        TextureHandle handle = new TextureHandle();
        String filename;
        String character;
        if(key.matches("^.+_.+$")) {
            String[] keySplit = key.split("_");
            filename = "textures/" + keySplit[0] + ".png";
            character = keySplit[1];
        } else {
            filename = "textures/" + DEFAULT_FONT + ".png";
            character = key;
        }

        Atlas atlas;
        if (atlasMap.containsKey(key)) {
            atlas = atlasMap.get(key);
        } else {
            try {
                atlas = loadPNGTexture2D(filename, GL13.GL_TEXTURE0);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            atlasMap.put(key, atlas);


        }
        float[] subInfo = generateTextureData(character, atlas);
        System.out.println(Arrays.toString(subInfo));

        handle.setAtlas(atlas);
        handle.setSubInfo(subInfo);

        return handle;
    }



    private Atlas loadPNGTexture2D(String filename, int textureUnit) throws IOException {


        Atlas atlas = null;

        // Open the PNG file as an InputStream
        try (InputStream in = ClassLoader.getSystemResourceAsStream(filename)) {
            ByteBuffer buf = null;
            // Link the PNG decoder to this stream
        /*    PNGDecoder decoder = new PNGDecoder(in);

            // Get the width and height of the texture
            int tWidth = decoder.getWidth();
            int tHeight = decoder.getHeight();


            // Decode the PNG file in a ByteBuffer
            buf = ByteBuffer.allocateDirect(
                    4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buf.flip();*/

//            PNGDecoder.Format.RGBA
//            TYPE_INT_ARGB
            BufferedImage read = convert(ImageIO.read(in), TYPE_4BYTE_ABGR);
//            BufferedImage read = ImageIO.read(in);
            byte[] data = ((DataBufferByte)read.getRaster().getDataBuffer()).getData();
            System.out.println("start");/*
            System.out.println(data[0]);
            System.out.println(data[1]);
            System.out.println(data[2]);
            System.out.println(data[3]);*/


            int tWidth = read.getWidth();
            int tHeight = read.getHeight();
            buf = ByteBuffer.allocateDirect(data.length);
            buf.put(data);
            buf.flip();
            // Create a new texture object in memory and bind it
            int texId = GL11.glGenTextures();

//            TYPE_INT_ARGB
//            System.out.println(read.getColorModel().getColorSpace().getType());

//            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, tWidth, tHeight, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV, buf);

            GL13.glActiveTexture(textureUnit);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);

            // All RGB bytes are aligned to each other and each component is 1 byte
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

//            glEnable(GL_BLEND);
//            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            // Upload the texture data and generate mip maps (for scaling)
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, tWidth, tHeight, 0,
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

            atlas = new Atlas();
            atlas.setFileName(filename);
            atlas.setTextureId(texId);
            atlas.setHeight(tHeight);
            atlas.setWidth(tWidth);

        }
        return atlas;
    }

    @Override
    protected void destroyHandle(String key) {
        TextureHandle handle = handleMap.remove(key);

        if (handle.getAtlas().count()==0) {
            GL11.glDeleteTextures(handle.getAtlas().getTextureId());
            atlasMap.remove(key);
        }

    }

    private float[] generateTextureData(String character, Atlas atlas) {



//        int width = atlas.getWidth();
//        int height = atlas.getHeight();

        float charWidth = 1f / CHARACTER_PER_ROW;
        float charHeight = 1f / ROWS;

        int charInt = (int)character.charAt(0) - CHARACTER_PER_ROW;

        System.out.println("charint = " + charInt);
        float x = (float)(charInt % CHARACTER_PER_ROW) / CHARACTER_PER_ROW;
        float y = (float)(charInt / CHARACTER_PER_ROW) / ROWS;
        System.out.println(x);
        System.out.println(y);

        return new float[] {
                x,
                y,
                x+charWidth,
                y+charHeight
        };

    }

    public static BufferedImage convert(BufferedImage src, int bufImgType) {
        BufferedImage img= new BufferedImage(src.getWidth(), src.getHeight(), bufImgType);
        ColorConvertOp xformOp = new ColorConvertOp(null);
        xformOp.filter(src, img);
        return img;
    }
}
