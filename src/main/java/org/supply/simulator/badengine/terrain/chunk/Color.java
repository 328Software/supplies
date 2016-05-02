package org.supply.simulator.badengine.terrain.chunk;

/**
 * Created by Brandon on 7/21/2014.
 */
public class Color {
    public byte r,g,b,a;

    Color(byte r, byte g, byte b, byte a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    Color(byte r, byte g, byte b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = (byte)1;
    }
}
