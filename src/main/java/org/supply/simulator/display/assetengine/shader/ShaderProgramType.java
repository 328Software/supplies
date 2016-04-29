package org.supply.simulator.display.assetengine.shader;

/**
 * Created by Alex on 6/27/2014.
 */
public enum ShaderProgramType {
    PLAY(0,"shaders/vertex.glsl","shaders/fragments.glsl"),
    MENU(1,"shaders/vertexWithFlatTexture.glsl","shaders/fragmentsWithTexture.glsl"),
    UNIT(2,"shaders/vertexWithTexture.glsl","shaders/fragmentsWithTexture.glsl");


    private final int value;
    private final String vertex;
    private final String fragment;

    public static final int COUNT = 3;

    ShaderProgramType(int value, String vertex ,String fragment) {
        this.value = value;
        this.vertex = vertex;
        this.fragment = fragment;
    }

    public int value () {
        return value;
    }

    public String vertex() {
        return vertex;
    }

    public String fragment() {
        return fragment;
    }
}
