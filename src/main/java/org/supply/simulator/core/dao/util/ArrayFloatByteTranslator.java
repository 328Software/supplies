package org.supply.simulator.core.dao.util;

import java.nio.ByteBuffer;

/**
 * Created by Brandon on 7/27/2014.
 * Helper class
 */
public class ArrayFloatByteTranslator {
    public float[] bytesToFloats(final byte[] bytes) {
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=0; i < floats.length; i++,j+=4) {
            floats[i] = Float.intBitsToFloat(makeInt(
                    bytes[j],
                    bytes[j+1],
                    bytes[j+2],
                    bytes[j+3]
            ));
        }
        return floats;
    }

    public byte[] floatsToBytes(float[] floats) {
        ByteBuffer b = ByteBuffer.allocate(floats.length*4);
        for(float f: floats) {
            b.putFloat(f);
        }
        return b.array();
    }

    static private int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return (((b3       ) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) <<  8) |
                ((b0 & 0xff)      ));
    }

}
