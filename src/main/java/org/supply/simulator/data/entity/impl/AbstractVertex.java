package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Vertex;

/**
 * Created by Brandon on 2/7/2018.
 */
public abstract class AbstractVertex implements Vertex {
    protected abstract boolean hasColor();

    protected abstract boolean isTextured();

    protected abstract float[] getValue();

    protected abstract int getOffset();

    @Override
    public void setXYZW(float x, float y, float z, float w) {
        int offset = getOffset();
        float[] value = getValue();
        value[offset] = x;
        value[offset + 1] = y;
        value[offset + 2] = z;
        value[offset + 3] = w;
    }

    @Override
    public void setRGBA(float r, float g, float b, float a) {
        if (hasColor()) {
            int offset = getOffset();
            float[] value = getValue();
            value[offset + 4] = r;
            value[offset + 5] = g;
            value[offset + 6] = b;
            value[offset + 7] = a;

        } else {
            throw new ArrayStoreException("Not a color " + getClass().getEnclosingClass().getName());
        }
    }

    @Override
    public void setST(float s, float t) {
        if (isTextured()) {
            int offset = getOffset();
            float[] value = getValue();
            value[offset + 8] = s;
            value[offset + 9] = t;
        } else {
            throw new ArrayStoreException("Not a textured " + getClass().getEnclosingClass().getName());
        }
    }

    // Getters
    @Override
    public float[] getElements() {
        int i = 0;
        float[] out = new float[getSize()];
        // Insert XYZW elements
        int offset = getOffset();
        float[] value = getValue();
        out[i++] = value[offset];
        out[i++] = value[offset + 1];
        out[i++] = value[offset + 2];
        out[i++] = value[offset + 3];
        // Insert RGBA elements
        out[i++] = value[offset + 4];
        out[i++] = value[offset + 5];
        out[i++] = value[offset + 6];
        out[i++] = value[offset + 7];
        // Insert ST elements
        out[i++] = value[offset + 8];
        out[i] =   value[offset + 9];

        return out;
    }

    @Override
    public float[] getXYZW() {
        int offset = getOffset();
        float[] value = getValue();
        return new float[]{
                value[offset], value[offset + 1], value[offset + 2], value[offset + 3]
        };
    }

    @Override
    public float[] getRGBA() {
        if (hasColor()) {
            float[] value = getValue();
            int offset = getOffset();
            return new float[]{value[offset + 4], value[offset + 5], value[offset + 6], value[offset + 7]};
        }
        throw new ArrayIndexOutOfBoundsException("Not a color " + getClass().getEnclosingClass().getName());
    }

    @Override
    public float[] getST() {
        if (isTextured()) {
            float[] value = getValue();
            int offset = getOffset();
            return new float[]{value[offset + 8], value[offset + 9]};
        }
        throw new ArrayIndexOutOfBoundsException("Not a textured " + getClass().getEnclosingClass().getName());
    }
}
