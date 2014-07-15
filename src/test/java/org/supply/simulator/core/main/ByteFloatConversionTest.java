package org.supply.simulator.core.main;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Created by Brandon on 7/14/2014.
 */
public class ByteFloatConversionTest {


    @Test
    public void testOne() {
        float[] f = {14.2f, 11.17f};
        System.out.println(Arrays.toString(
                bytesToFloats(floatsToBytes(f))));


    }


    protected float[] bytesToFloats(byte[] bytes) {
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=i; i < bytes.length; i+=4,j++) {
            byte[] floatBytes = new byte[4];
            floatBytes[0] = bytes[i];
            floatBytes[1] = bytes[i+1];
            floatBytes[2] = bytes[i+2];
            floatBytes[3] = bytes[i+3];
            floats[j] = ByteBuffer.wrap(floatBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
        }
        return floats;
    }

    protected byte[] floatsToBytes(float[] floats) {
        ByteBuffer b = ByteBuffer.allocate(floats.length*4);
        for(float f: floats) {
            b.putFloat(f);
        }
        return b.array();
    }
}
