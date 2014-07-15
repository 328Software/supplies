package org.supply.simulator.display.assetengine.shader;

import org.lwjgl.opengl.GL20;

/**
 * Created by Alex on 7/6/2014.
 */
public enum ShaderType {
    VERTEX(GL20.GL_VERTEX_SHADER,"vertex"),
    FRAGMENT(GL20.GL_FRAGMENT_SHADER,"fragment");

    private final int value;
    private final String string;

    ShaderType(int value, String string) {
        this.value = value;
        this.string = string;
    }

    public int value () {
        return value;
    }

    @Override
    public String toString () {return string;}
}
