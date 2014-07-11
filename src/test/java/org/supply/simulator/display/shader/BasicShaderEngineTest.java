package org.supply.simulator.display.shader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.logging.HasLogger;


/**
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngineTest extends HasLogger {

    private ShaderEngine engine;
    @Before
    public void createFixture () {
        MockDisplayCore.build("BasicShaderEngineTest");
        logger.info("START BasicShaderEngineTest");

        engine = new BasicShaderEngine();
        engine.setPlayShaderFile("shaders/vertex.glsl",ShaderType.VERTEX);
        engine.setPlayShaderFile("shaders/fragments.glsl",ShaderType.FRAGMENT);

    }

    @Test
    public void TestPlayShader () {
        logger.info("    TEST createPlayShader");
        engine.createProgram(ShaderProgramType.PLAY);
        if (engine.getModelMatrixLocation(ShaderProgramType.PLAY)!=0
                ||engine.getProjectionMatrixLocation(ShaderProgramType.PLAY)!=1
                ||engine.getViewMatrixLocation(ShaderProgramType.PLAY)!=2) {
            logger.error("View matrix location wrong");
            System.exit(-1);
        }
        logger.info("Successfully created shader program");

        engine.useProgram(ShaderProgramType.PLAY);
        engine.useProgram(ShaderProgramType.CLEAR);
        logger.info("Successfully used program");

        engine.deleteProgram(ShaderProgramType.PLAY);
        logger.info("Successfully deleted program");

    }

    @After
    public void destroyObject() {

        MockDisplayCore.destroy();
    }

}
