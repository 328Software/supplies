package org.supply.simulator.display.shader;

import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngineTest {

    private ShaderEngine engine;
    @Before
    public void createFixture () {
        engine = new BasicShaderEngine();
        engine.setPlayVertexShader("shaders/vertex.glsl");
        engine.setPlayFragmentShader("shaders/fragments.glsl");

    }

    @Test
    public void createPlayShader () {
        engine.createProgram(ShaderProgramType.PLAY);
        if (engine.getModelMatrixLocation(ShaderProgramType.PLAY)!=0
                ||engine.getProjectionMatrixLocation(ShaderProgramType.PLAY)!=1
                ||engine.getViewMatrixLocation(ShaderProgramType.PLAY)!=2) {
            System.out.println("View matrix location wrong");
            System.exit(-1);
        }

    }

    @Test
    public void usePlayShader() {
        engine.useProgram(ShaderProgramType.PLAY);
        engine.useProgram(ShaderProgramType.CLEAR);
    }

    @Test
    public void destroyPlayShader () {

        engine.deleteProgram(ShaderProgramType.PLAY);
    }


}
