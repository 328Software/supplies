package org.supply.simulator.display.assetengine.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL21;
import org.lwjgl.opengl.GL30;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.AssetEngine;
import org.supply.simulator.display.assetengine.WeakReferenceEngine;
import org.supply.simulator.logging.HasLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;

import static java.awt.image.BufferedImage.*;
import static java.util.Objects.nonNull;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL12.GL_UNSIGNED_INT_8_8_8_8;
import static org.lwjgl.opengl.GL12.GL_UNSIGNED_INT_8_8_8_8_REV;
import static org.lwjgl.opengl.GL13.GL_COMBINE;
import static org.lwjgl.opengl.GL13.GL_COMBINE_RGB;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL40.glBlendFunci;
import static org.lwjgl.opengl.Util.checkGLError;

/**
 * Created by Brandon on 5/19/2016.
 */
public class FontTextureEngine extends WeakReferenceEngine<String, TextureHandle>
        implements TextureEngine {
    private static final String DEFAULT_DIR = "textures/";
    public static final int ROWS = 3;
    private static final String DEFAULT_FONT = "manaspc";

    private HashMap<String, Atlas> atlasMap;

    public FontTextureEngine() {
        atlasMap = new HashMap<>();
    }

    @Override
    public boolean canHandle(String key) {
        String filename;
        if (key.matches("^.+_.+$")) {
            String[] keySplit = key.split("_");
            filename = "textures/" + keySplit[0] + ".png";
        } else {
            filename = "textures/" + DEFAULT_FONT + ".png";
        }
        return nonNull(this.getClass().getClassLoader().getResource(filename));
    }

    @Override
    protected TextureHandle createHandle(String key) {
        TextureHandle handle = new TextureHandle();
        String filename;
        String character;
        if (key.matches("^.+_.+$")) {
            String[] keySplit = key.split("_");
            filename = "textures/" + keySplit[0] + ".png";
            character = keySplit[1];
        } else {
            filename = "textures/" + DEFAULT_FONT + ".png";
            character = key;
        }

        Atlas atlas;
        if (atlasMap.containsKey(filename)) {
            atlas = atlasMap.get(filename);
        } else {
            try {
                atlas = loadPNGTexture2D(filename, GL13.GL_TEXTURE0);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            atlasMap.put(filename, atlas);
        }
        float[] subInfo = generateTextureData(character, atlas);

        handle.setAtlas(atlas);
        handle.setSubInfo(subInfo);

        return handle;
    }


    private Atlas loadPNGTexture2D(String filename, int textureUnit) throws IOException {


        Atlas atlas = null;

        // Open the PNG file as an InputStream
        try (InputStream in = ClassLoader.getSystemResourceAsStream(filename)) {
            ByteBuffer buf;
            BufferedImage read = ImageIO.read(in);
            //todo fail here if the image isn't 8 bit
            buf = extractAlphaChannelBuffer(read);

            int tWidth = read.getWidth();
            int tHeight = read.getHeight();

            int texId = GL11.glGenTextures();

            GL13.glActiveTexture(textureUnit);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);

            // All RGB bytes are aligned to each other and each component is 1 byte
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


            // Upload the texture data and generate mip maps (for scaling)
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL_RGBA, tWidth, tHeight, 0,
//                    GL_BGRA, GL_UNSIGNED_INT_8_8_8_8, buf);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL_R8, tWidth, tHeight, 0,
                    GL_RED, GL_UNSIGNED_BYTE, buf);

            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

            // Setup the ST coordinate system
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//            glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
//            glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
            // Setup what to do when the texture has to be scaled

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
                    GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
                    GL11.GL_NEAREST_MIPMAP_LINEAR);

            atlas = new Atlas();
            atlas.setFileName(filename);
            atlas.setTextureId(texId);
            atlas.setHeight(tHeight);
            atlas.setWidth(tWidth);

        }
        return atlas;
    }

    private ByteBuffer extractAlphaChannelBuffer(BufferedImage read) {
        ByteBuffer buf;
        DataBufferByte byteData = (DataBufferByte) read.getRaster().getDataBuffer();
        byte[] data = byteData.getData();

        buf = ByteBuffer.allocateDirect(data.length);
        buf.put(data);
        buf.flip();
        return buf;
    }

    @Override
    protected void destroyHandle(String key) {
        logger.info("Destroying handle " + key);
    }

    private float[] generateTextureData(String character, Atlas atlas) {

        float charWidth = 1f / 32;// - (1f / atlas.getWidth());
        float charHeight = 1f / ROWS;// - (4f / atlas.getHeight());

        int charInt = (int) character.charAt(0) - 32;

        float x = new BigDecimal(charInt % 32).setScale(3, BigDecimal.ROUND_HALF_UP).divide(BigDecimal.valueOf(32), BigDecimal.ROUND_HALF_DOWN).floatValue();
        float y = new BigDecimal(charInt / 32).setScale(3, BigDecimal.ROUND_HALF_UP).divide(BigDecimal.valueOf(ROWS), BigDecimal.ROUND_HALF_DOWN).floatValue();

        return new float[]{
                x,
                y,
                x + charWidth,
                y + charHeight
        };

    }

  /*  public static BufferedImage convert(BufferedImage src, int bufImgType) {
        BufferedImage img= new BufferedImage(src.getWidth(), src.getHeight(), bufImgType);
        ColorConvertOp xformOp = new ColorConvertOp(null);
        xformOp.filter(src, img);
        return img;
    }*/
}
