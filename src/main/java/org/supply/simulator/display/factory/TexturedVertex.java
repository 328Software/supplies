package org.supply.simulator.display.factory;

import static org.supply.simulator.display.renderer.DrawingUtil.*;

/**
 * Created by Alex on 7/27/2014.
 */
public class TexturedVertex {
    // Vertex data
    private float[] xyzw = new float[] {0f, 0f, 0f, 1f};
    private float[] rgba = new float[] {1f, 1f, 1f, 1f};
    private float[] st = new float[] {0f, 0f};

    public static final int TEXTURE_VERTEX_TOTAL_SIZE = POSITION_ELEMENT_COUNT +
            COLOR_ELEMENT_COUNT + TEXTURE_ELEMENT_COUNT;

    // Setters
    public void setXYZ(float x, float y, float z) {
        this.setXYZW(x, y, z, 1f);
    }

    public void setRGB(float r, float g, float b) {
        this.setRGBA(r, g, b, 1f);
    }

    public void setST(float s, float t) {
        this.st = new float[] {s, t};
    }

    public void setXYZW(float x, float y, float z, float w) {
        this.xyzw = new float[] {x, y, z, w};
    }

    public void setRGBA(float r, float g, float b, float a) {
        this.rgba = new float[] {r, g, b, 1f};
    }

    // Getters
    public float[] getElements() {
        float[] out = new float[TEXTURE_VERTEX_TOTAL_SIZE];
        int i = 0;

        // Insert XYZW elements
        out[i++] = this.xyzw[0];
        out[i++] = this.xyzw[1];
        out[i++] = this.xyzw[2];
        out[i++] = this.xyzw[3];
        // Insert RGBA elements
        out[i++] = this.rgba[0];
        out[i++] = this.rgba[1];
        out[i++] = this.rgba[2];
        out[i++] = this.rgba[3];
        // Insert ST elements
        out[i++] = this.st[0];
        out[i] = this.st[1];

        return out;
    }

    public float[] getXYZW() {
        return new float[] {this.xyzw[0], this.xyzw[1], this.xyzw[2], this.xyzw[3]};
    }

    public float[] getRGBA() {
        return new float[] {this.rgba[0], this.rgba[1], this.rgba[2], this.rgba[3]};
    }

    public float[] getST() {
        return new float[] {this.st[0], this.st[1]};
    }
}

