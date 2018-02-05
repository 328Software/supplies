package org.supply.simulator.data.entity;

/**
 * Created by Brandon on 2/5/2018.
 */
public final class Vertex {
    private Positions positions;
    private final int index;

    public Vertex(Positions positions, int index) {
        this.positions = positions;
        this.index = index;
    }

    // Setters
    public void setXYZ(float x, float y, float z) {
        this.setXYZW(x, y, z, 1f);
    }

    public void setRGB(float r, float g, float b) {
        this.setRGBA(r, g, b, 1f);
    }

    public void setXYZW(float x, float y, float z, float w) {
        int offset = getOffset();
        float[] value = positions.getValue();
        value[offset] = x;
        value[offset + 1] = y;
        value[offset + 2] = z;
        value[offset + 3] = w;
    }

    public void setRGBA(float r, float g, float b, float a) {
        if (positions.hasColor()) {
            int offset = getOffset();
            float[] value = positions.getValue();
            value[offset + 4] = r;
            value[offset + 5] = g;
            value[offset + 6] = b;
            value[offset + 7] = a;

        } else {
            throw new ArrayStoreException("Not a color " + getClass().getEnclosingClass().getName());
        }
    }

    public void setST(float s, float t) {
        if (positions.isTextured()) {
            int offset = getOffset();
            float[] value = positions.getValue();
            value[offset + 8] = s;
            value[offset + 9] = t;
        } else {
            throw new ArrayStoreException("Not a textured " + getClass().getEnclosingClass().getName());
        }
    }

    private int getOffset() {
        return index * positions.getVertexSize();
    }

    // Getters
    public float[] getElements() {
        int i = 0;
        float[] out = new float[positions.getVertexSize()];
        // Insert XYZW elements
        int offset = getOffset();
        float[] value = positions.getValue();
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

    public float[] getXYZW() {
        int offset = getOffset();
        float[] value = positions.getValue();
        return new float[]{
                value[offset], value[offset + 1], value[offset + 2], value[offset + 3]
        };
    }

    public float[] getRGBA() {
        if (positions.hasColor()) {
            float[] value = positions.getValue();
            int offset = getOffset();
            return new float[]{value[offset + 4], value[offset + 5], value[offset + 6], value[offset + 7]};
        }
        throw new ArrayIndexOutOfBoundsException("Not a color " + getClass().getEnclosingClass().getName());
    }

    public float[] getST() {
        if (positions.isTextured()) {
            float[] value = positions.getValue();
            int offset = getOffset();
            return new float[]{value[offset + 8], value[offset + 9]};
        }
        throw new ArrayIndexOutOfBoundsException("Not a textured " + getClass().getEnclosingClass().getName());
    }
}
