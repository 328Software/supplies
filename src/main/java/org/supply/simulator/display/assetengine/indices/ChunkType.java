package org.supply.simulator.display.assetengine.indices;

import org.lwjgl.opengl.GL11;

/**
 * Created by Alex on 7/14/2014.
 */
public enum ChunkType {
    TINY_T(2,2, GL11.GL_TRIANGLES),
    SMALL_T(20,20,GL11.GL_TRIANGLES),
    MEDIUM_T(50,50,GL11.GL_TRIANGLES),
    LARGE_T(80,80,GL11.GL_TRIANGLES),
    TINY_Q(2,2,GL11.GL_QUADS),
    SMALL_Q(20,20,GL11.GL_QUADS),
    MEDIUM_Q(50,50,GL11.GL_QUADS),
    LARGE_Q(80,80,GL11.GL_QUADS);

    private final int row;
    private final int columns;
    private final int glRenderType;

    ChunkType(int row, int columns, int glRenderType) {
        this.row = row;
        this.columns = columns;
        this.glRenderType = glRenderType;
    }

    public int rows () {
        return row;
    }

    public int columns() {return columns;}

    public int glRenderType() {return glRenderType;}
}
