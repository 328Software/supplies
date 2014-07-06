package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.supply.simulator.display.core.DisplayCore;
import org.supply.simulator.display.core.DisplayCoreTest;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.MockCamera;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 7/3/2014.
 */
public class NewTest {
    MockCamera camera;
    BasicShaderEngine shaderEngine;
    BasicChunk chunk;

    @Before
    public void create() {
        DisplayCoreTest.build();

        shaderEngine = new BasicShaderEngine();
        shaderEngine.setPlayVertexShader("shaders/vertex.glsl");
        shaderEngine.setPlayFragmentShader("shaders/fragments.glsl");

        shaderEngine.createProgram(ShaderProgramType.PLAY);

        camera = new MockCamera();
        camera.setProjectionMatrixLocation(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY));
        camera.setModelMatrixLocation(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY));
        camera.setViewMatrixLocation(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY));

        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getData(100,100));
        chunk.setAttributeLocations(new int[] {0,1});
        chunk.build();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {

            camera.build();
            shaderEngine.useProgram(ShaderProgramType.PLAY);

            shaderEngine.useProgram(ShaderProgramType.CLEAR);

            // Clear bit
            //TODO What does this do?
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // Set shader program type to CHUNK
            shaderEngine.useProgram(ShaderProgramType.PLAY);

            chunk.render();

            camera.render();

            shaderEngine.useProgram(ShaderProgramType.CLEAR);
            DisplayCoreTest.render();
        }

    }

    @After
    public void destroy() {
        //
        shaderEngine.useProgram(ShaderProgramType.CLEAR);
        shaderEngine.deleteProgram(ShaderProgramType.PLAY);
        chunk.destroy();
        DisplayCoreTest.destroy();

    }

}
