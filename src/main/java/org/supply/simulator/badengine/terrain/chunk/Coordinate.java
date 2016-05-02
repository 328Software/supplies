package org.supply.simulator.badengine.terrain.chunk;

/**
 * Created by Brandon on 7/21/2014.
 */
public class Coordinate {
    public float x,y,z,w;

    Coordinate(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    Coordinate(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1f;
    }
}
