package org.supply.simulator.data.entity;

/**
 * Created by Brandon on 2/6/2018.
 */
public interface Vertex {
    // Setters
    default void setXYZ(float x, float y, float z) {
        this.setXYZW(x, y, z, 1f);
    }

    default void setRGB(float r, float g, float b) {
        this.setRGBA(r, g, b, 1f);
    }

    void setXYZW(float x, float y, float z, float w);

    void setRGBA(float r, float g, float b, float a);

    void setST(float s, float t);

    // Getters
    float[] getElements();

    int getSize();

    float[] getXYZW();

    float[] getRGBA();

    float[] getST();
}
