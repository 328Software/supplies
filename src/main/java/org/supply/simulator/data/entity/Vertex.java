package org.supply.simulator.data.entity;

/**
 * Created by Brandon on 2/6/2018.
 */
public interface Vertex {
    // Setters
    void setXYZ(float x, float y, float z);

    void setRGB(float r, float g, float b);

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
