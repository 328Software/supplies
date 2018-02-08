package org.supply.simulator.display.assetengine.shader;


import org.lwjgl.opengl.GL20;

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
    protected String getFragmentResourceName(ShaderProgramType key) {
        return key.fragment();
    }

    @Override
    protected String getVertexResourceName(ShaderProgramType key) {
        return key.vertex();
    }

    @Override
    protected void destroyHandle(ShaderProgramType key) {
        ShaderHandle handle = handleMap.remove(key);
        GL20.glDeleteProgram(handle.getProgramId());

    }
}
