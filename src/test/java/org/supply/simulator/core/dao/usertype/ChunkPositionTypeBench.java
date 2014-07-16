package org.supply.simulator.core.dao.usertype;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import sun.nio.ch.ThreadPool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by Brandon on 7/15/2014.
 */
//@BenchmarkOptions(benchmarkRounds = 100000, warmupRounds = 1000)
public class ChunkPositionTypeBench {
//    @Rule
//    public TestRule benchmarkRun = new BenchmarkRule();
    ThreadPoolExecutor service = new ThreadPoolExecutor(4,4,0, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

    byte[] bytes;

    @Before
    public void createFixture() {

        bytes = new byte[160000];
        for(int i = 0; i < bytes.length; i+=4) {
           bytes[i] = (byte)0xbf;
           bytes[i+1] = (byte)0;
           bytes[i+2] = (byte)0;
           bytes[i+3] = (byte)0;
        }
    }


//        @Test
//    public void bytesToFloats() {
////        System.out.println(Arrays.toString(bytesToFloats(bytes)));
//        bytesToFloats(bytes);
//    }
////
////
//    @Test
//    public void bytesToFloatsUnroll() {
////        System.out.println(Arrays.toString(bytesToFloats(bytes)));
//        bytesToFloatsUnroll(bytes);
//    }
//

//      @Test
//      public void makeIntTest() {
//          byte[] bs = {(byte)0xbf,(byte)0,(byte)0,(byte)0};
//
//          assertEquals(makeInt(bs[0],bs[1],bs[2],bs[3]),makeIntAlt(bs[0],bs[1],bs[2],bs[3]));
//      }




//
//    @Test
//    public void bytesToFloatsBufferCodeUnroll() {
////        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
//        bytesToFloatsBufferCodeUnroll(bytes);
//    }
//
    @Test
    public void bytesToFloatsBufferCodeUnrollMulti() {
//        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
        bytesToFloatsBufferCodeUnrollMulti(bytes);
    }
//
    @Test
    public void bytesToFloatsBufferCodeUnrollMultiFour() {
//        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
        bytesToFloatsBufferCodeUnrollMultiFour(bytes);
    }

    @Test
    public void bytesToFloatsBufferCodeUnrollCond() {
//        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
        bytesToFloatsBufferCodeUnrollCond(bytes);
    }

    @Test
    public void bytesToFloatsBufferCodeUnrollFour() {
//        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
        bytesToFloatsBufferCodeUnrollFour(bytes);
    }

//    @Test
//    public void bytesToFloatsBufferCodeUnrollFourCond() {
////        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
//        bytesToFloatsBufferCodeUnrollFourCond(bytes);
//    }

//    @Test
//    public void bytesToFloatsBuffer() {
////        System.out.println(Arrays.toString(bytesToFloatsBuffer(bytes)));
//        bytesToFloatsBuffer(bytes);
//    }

    @Test
    public void bytesToFloatsBufferCode() {
//        System.out.println(Arrays.toString(bytesToFloatsBufferCode(bytes)));
        bytesToFloatsBufferCode(bytes);
    }




    protected float[] bytesToFloats(byte[] bytes) {
        byte[] floatBytes = new byte[4];
        //todo this is probably pretty inefficient wrt memory
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=i; i < bytes.length; i+=4,j++) {
            floatBytes[0] = bytes[i];
            floatBytes[1] = bytes[i+1];
            floatBytes[2] = bytes[i+2];
            floatBytes[3] = bytes[i+3];
            floats[j] = ByteBuffer.wrap(floatBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
        }
        return floats;
    }

    protected float[] bytesToFloatsUnroll(byte[] bytes) {
        byte[] floatBytes = new byte[4];
        //todo this is probably pretty inefficient wrt memory
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=i; i < bytes.length; i+=8,j++) {
            floatBytes[0] = bytes[i];
            floatBytes[1] = bytes[i+1];
            floatBytes[2] = bytes[i+2];
            floatBytes[3] = bytes[i+3];
            floats[j] = ByteBuffer.wrap(floatBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
            floatBytes[0] = bytes[i+4];
            floatBytes[1] = bytes[i+5];
            floatBytes[2] = bytes[i+6];
            floatBytes[3] = bytes[i+7];
            floats[j+1] = ByteBuffer.wrap(floatBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
        }
        return floats;
    }

    protected float[] bytesToFloatsBuffer(final byte[] bytes) {
        //todo this is probably pretty inefficient wrt memory
        float[] floats = new float[bytes.length/4];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        FloatBuffer f = b.asFloatBuffer();

        f.get(floats);
        return floats;
    }

    protected float[] bytesToFloatsBufferCode(final byte[] bytes) {
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

    protected float[] bytesToFloatsBufferCodeUnrollCond(final byte[] bytes) {
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=0; i < floats.length; i+=2,j+=8) {
            floats[i] = Float.intBitsToFloat(makeInt(
                    bytes[j],
                    bytes[j+1],
                    bytes[j+2],
                    bytes[j+3]
            ));
            if(i+1 < floats.length) {
                floats[i + 1] = Float.intBitsToFloat(makeInt(
                        bytes[j + 4],
                        bytes[j + 5],
                        bytes[j + 6],
                        bytes[j + 7]
                ));
            }
        }
        return floats;
    }

    protected float[] bytesToFloatsBufferCodeUnroll(final byte[] bytes) {
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=0; i < floats.length; i+=2,j+=8) {
            floats[i] = Float.intBitsToFloat(makeInt(
                    bytes[j],
                    bytes[j+1],
                    bytes[j+2],
                    bytes[j+3]
            ));
//            if(i+1 < floats.length) {
                floats[i + 1] = Float.intBitsToFloat(makeInt(
                        bytes[j + 4],
                        bytes[j + 5],
                        bytes[j + 6],
                        bytes[j + 7]
                ));
//            }
        }
        return floats;
    }

    protected float[] bytesToFloatsBufferCodeUnrollMultiFour(final byte[] bytes) {
//        ThreadPoolExecutor service = new ThreadPoolExecutor(2,2,0, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

        final float[] floats = new float[bytes.length/4];

        Future f = service.submit(new R(bytes,floats,0,floats.length/4)),
                g = service.submit(new R(bytes,floats,floats.length/4,floats.length/2)),
                h = service.submit(new R(bytes,floats,floats.length/2,3*floats.length/4)),
                i = service.submit(new R(bytes,floats,3*floats.length/4,floats.length))
                        ;
//        new R(bytes,floats,floats.length/2,floats.length).run();
        while(!f.isDone() || !g.isDone() || !h.isDone() || !i.isDone()) {
            Thread.yield();
        }

        return floats;
    }

    protected float[] bytesToFloatsBufferCodeUnrollMulti(final byte[] bytes) {
//        ThreadPoolExecutor service = new ThreadPoolExecutor(2,2,0, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

        final float[] floats = new float[bytes.length/4];

        Future f = service.submit(new R(bytes,floats,0,floats.length/2)),
                g = service.submit(new R(bytes,floats,floats.length/2,floats.length));
//                h = service.submit(new R(bytes,floats,floats.length/2,3*floats.length/4)),
//                i = service.submit(new R(bytes,floats,3*floats.length/4,floats.length))

//        new R(bytes,floats,floats.length/2,floats.length).run();
        while(!f.isDone() || !g.isDone()) {
            Thread.yield();
        }

        return floats;
    }

    class R implements Runnable {
        byte[] b;
        float[] t;
        int s,e;

        R(byte[] b, float[] t, int s, int e) {
            this.b = b;
            this.t = t;
            this.s = s;
            this.e = e;
        }

        @Override
        public void run() {
            for(int i = s,j=i; i < e; i+=2,j+=8) {
                t[i] = Float.intBitsToFloat(makeInt(
                        b[j],
                        b[j+1],
                        b[j+2],
                        b[j+3]
                ));
                t[i + 1] = Float.intBitsToFloat(makeInt(
                        b[j + 4],
                        b[j + 5],
                        b[j + 6],
                        b[j + 7]
                ));
            }
        }
    }

    protected float[] bytesToFloatsBufferCodeUnrollFour(final byte[] bytes) {
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=0; i < floats.length; i+=4,j+=16) {
            floats[i] = Float.intBitsToFloat(makeInt(
                    bytes[j],
                    bytes[j+1],
                    bytes[j+2],
                    bytes[j+3]
            ));
//            if(i+1 < floats.length) {
                floats[i + 1] = Float.intBitsToFloat(makeInt(
                        bytes[j + 4],
                        bytes[j + 5],
                        bytes[j + 6],
                        bytes[j + 7]
                ));
//            }
//            if(i+2 < floats.length) {
                floats[i + 2] = Float.intBitsToFloat(makeInt(
                        bytes[j + 8],
                        bytes[j + 9],
                        bytes[j + 10],
                        bytes[j + 11]
                ));
//            }
//            if(i+3 < floats.length) {
                floats[i + 3] = Float.intBitsToFloat(makeInt(
                        bytes[j + 12],
                        bytes[j + 13],
                        bytes[j + 14],
                        bytes[j + 15]
                ));
//            }
        }
        return floats;
    }

    protected float[] bytesToFloatsBufferCodeUnrollFourCond(final byte[] bytes) {
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=0; i < floats.length; i+=4,j+=16) {
            floats[i] = Float.intBitsToFloat(makeInt(
                    bytes[j],
                    bytes[j+1],
                    bytes[j+2],
                    bytes[j+3]
            ));
            if(i+1 < floats.length) {
            floats[i + 1] = Float.intBitsToFloat(makeInt(
                    bytes[j + 4],
                    bytes[j + 5],
                    bytes[j + 6],
                    bytes[j + 7]
            ));
            }
            if(i+2 < floats.length) {
            floats[i + 2] = Float.intBitsToFloat(makeInt(
                    bytes[j + 8],
                    bytes[j + 9],
                    bytes[j + 10],
                    bytes[j + 11]
            ));
            }
            if(i+3 < floats.length) {
            floats[i + 3] = Float.intBitsToFloat(makeInt(
                    bytes[j + 12],
                    bytes[j + 13],
                    bytes[j + 14],
                    bytes[j + 15]
            ));
            }
        }
        return floats;
    }

    private int makeInt1(byte b3, byte b2, byte b1, byte b0) {
        return (((b3       ) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) <<  8) |
                ((b0 & 0xff)      ));
    }

    private int makeInt(byte b3, byte b2, byte b1, byte b0) {
//        return (((b3       ) << 8) |
//                ((b2 & 0xff) << 16) |
//                ((b1 & 0xff) <<  8) |
//                ((b0 & 0xff)      ));
        return ((((((b3 << 8) | b2) << 8) | b1) << 8) | b0);
//        i = (i | b2) << 8;
//        i = (i | b1) << 8;
//        i = (i | b0) << 8;
//        return i;
    }
}
