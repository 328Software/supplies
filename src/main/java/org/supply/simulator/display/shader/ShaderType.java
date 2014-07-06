package org.supply.simulator.display.shader;

import org.lwjgl.opengl.GL20;

/**
 * Created by Alex on 7/6/2014.
 */
public enum ShaderType {
    VERTEX(GL20.GL_VERTEX_SHADER),
    FRAGMENT(GL20.GL_FRAGMENT_SHADER);

    private final int value;

    ShaderType(int value) {
        this.value = value;
    }

    public int value () {
        return value;
    }
}
