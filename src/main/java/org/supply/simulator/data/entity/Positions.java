package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;
import org.supply.simulator.display.renderer.DrawingUtil;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Alex on 9/14/2014.
 */
public class Positions implements HasId<Long>, HasValue<float[]> {
    public static final Comparator<Map.Entry<Integer, Integer>> ENTRY_COMPARATOR = Map.Entry.<Integer, Integer>comparingByKey().thenComparing(Map.Entry.<Integer, Integer>comparingByValue());
    public static final int NUMBER_OF_VERTICES = 4;

    private final boolean textured, color;
    private Long id;
    private float[] value;
    private Vertex[] vertices;

    private final int vertexSize;

    private String textureKey;

    public static Positions newTexturedPositions() {
        return new Positions(true, false);
    }

    public static Positions newColorPositions() {
        return new Positions(false, true);
    }

    public static Positions newTexturedColorPositions() {
        return new Positions(true, true);
    }


    private Positions(boolean textured, boolean color) {
        this.textured = textured;
        this.color = color;

        new TreeMap<Map.Entry<Integer, Integer>, Integer>(ENTRY_COMPARATOR);

        vertexSize = getVertexSize(textured, color);

        value = new float[vertexSize * NUMBER_OF_VERTICES];

        vertices = new Vertex[NUMBER_OF_VERTICES];
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    public boolean isTextured() {
        return textured;
    }

    public boolean hasColor() {
        return color;
    }

    public int[] getVertexAttributeLocations () {
        int[] locations=null;
        if (this.isTextured()&&this.hasColor()) {
            locations=new int[]{0,1,2};
        } else if (this.isTextured()||this.hasColor()){
            locations=new int[]{0,1};
        }
        return locations;
    }

    public Vertex getVertex(int index) {
        return vertices[index];
    }

    @Override
    public float[] getValue() {
        return value;
    }

    public void setValue(float[] value) {
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

//    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTextureKey() {
        return textureKey;
    }

    public void setTextureKey(String textureKey) {
        this.textureKey = textureKey;
    }

    private int getVertexSize(boolean textured, boolean colored) {
        return DrawingUtil.POSITION_ELEMENT_COUNT +
                (colored ? DrawingUtil.COLOR_ELEMENT_COUNT : 0) + (textured ? DrawingUtil.TEXTURE_ELEMENT_COUNT : 0);
    }


    public final class Vertex {
        private final int index;

        public Vertex(int index) {
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
            value[offset] = x;
            value[offset + 1] = y;
            value[offset + 2] = z;
            value[offset + 3] = w;
        }

        public void setRGBA(float r, float g, float b, float a) {
            if (color) {
                int offset = getOffset();
                value[offset + 4] = r;
                value[offset + 5] = g;
                value[offset + 6] = b;
                value[offset + 7] = a;

            } else {
                throw new ArrayStoreException("Not a color " + getClass().getEnclosingClass().getName());
            }
        }

        public void setST(float s, float t) {
            if (textured) {
                int offset = getOffset();
                value[offset + 8] = s;
                value[offset + 9] = t;
            } else {
                throw new ArrayStoreException("Not a textured " + getClass().getEnclosingClass().getName());
            }
        }

        private int getOffset() {
            return index * vertexSize;
        }

        // Getters
        public float[] getElements() {
            int i = 0;
            float[] out = new float[vertexSize];
            // Insert XYZW elements
            int offset = getOffset();
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
            out[i] = value[offset + 9];

            return out;
        }

        public float[] getXYZW() {
            int offset = getOffset();
            return new float[]{
                    value[offset], value[offset + 1], value[offset + 2], value[offset + 3]
            };
        }

        public float[] getRGBA() {
            if(color) {
                int offset = getOffset();
                return new float[]{value[offset + 4], value[offset + 5], value[offset + 6], value[offset + 7]};
            }
            throw new ArrayIndexOutOfBoundsException("Not a color " + getClass().getEnclosingClass().getName());
        }

        public float[] getST() {
            if(textured) {
                int offset = getOffset();
                return new float[]{value[offset + 8], value[offset + 9]};
            }
            throw new ArrayIndexOutOfBoundsException("Not a textured " + getClass().getEnclosingClass().getName());
        }
    }
}
