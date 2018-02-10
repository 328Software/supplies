package org.supply.simulator.display.assetengine.shader;


import org.lwjgl.opengl.GL20;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic implementation of the ShaderEngine interface.
 *
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngine
        extends AbstractShaderEngine<ShaderProgramType> {


    public BasicShaderEngine () {
        super();
    }

    @Override
    public String getFragmentResourceName(ShaderProgramType key) {
        return key.fragment();
    }

    @Override
    public String getVertexResourceName(ShaderProgramType key) {
        return key.vertex();
    }

    @Override
    public boolean isValid(ShaderProgramType key) {
        return true;
    }
}
