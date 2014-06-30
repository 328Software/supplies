package org.supply.simulator.display.window;

import org.supply.simulator.display.manager.chunk.MockChunkManager;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.window.impl.BasicCamera;

/**
 * Created by Alex on 6/29/2014.
 */
public class MockPlayWindow<S extends ShaderEngine> extends AbstractPlayWindow<S> {



    private MockInput input;

    public MockPlayWindow() {
        super();
        input = new MockInput();
        shaderEngine = new BasicShaderEngine();
        shaderEngine.setPlayVertexShader("shaders/vertex.glsl");
        shaderEngine.setPlayFragmentShader("shaders/fragments.glsl");
        chunkManager = new MockChunkManager<>();

    }

    @Override
    protected Camera getCameraFromStream() {



        input.refreshInput();

        return input.getCamera();
    }
}
