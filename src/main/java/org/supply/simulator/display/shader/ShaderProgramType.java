package org.supply.simulator.display.shader;

/**
 * Created by Alex on 6/27/2014.
 */
public enum ShaderProgramType {
    CLEAR(0),
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
