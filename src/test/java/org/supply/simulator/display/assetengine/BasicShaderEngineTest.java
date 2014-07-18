package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.mock.MockShaderEngine;
import org.supply.simulator.logging.HasLogger;


/**
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngineTest extends HasLogger {

    private MockDisplayCore core;
    private BasicShaderEngine engine;
    @Before
    public void createFixture () {
        core = new MockDisplayCore();
        core.build("BasicShaderEngineTest");
        logger.info("START BasicShaderEngineTest");

        engine = new BasicShaderEngine();

    }

    @Test
    public void TestPlayShader () {
        logger.info("    TEST createPlayShader");
        //engine.createProgram(ShaderProgramType.PLAY);
        if (engine.get(ShaderProgramType.PLAY).getModelMatrixLocation()!=0
                ||engine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation()!=1
                ||engine.get(ShaderProgramType.PLAY).getViewMatrixLocation()!=2) {
            logger.error("View matrix location wrong");
            System.exit(-1);
        }
        logger.info("Successfully created shader program");

        GL20.glUseProgram(engine.get(ShaderProgramType.PLAY).getProgramId());
        GL20.glUseProgram(0);
        logger.info("Successfully used program");

        GL20.glDeleteProgram(engine.get(ShaderProgramType.PLAY).getProgramId());
        logger.info("Successfully deleted program");

    }

    @After
    public void destroyObject() {

        core.destroy();
    }

}
