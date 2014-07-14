package org.supply.simulator.display.assetengine.shader;

/**
 * Created by Alex on 6/27/2014.
 */
public enum ShaderProgramType {
    PLAY(1),
    MENU(2);

    private final int value;

    public static final int COUNT = 3;

    ShaderProgramType(int value) {
        this.value = value;
    }

    public int value () {
        return value;
    }
}
