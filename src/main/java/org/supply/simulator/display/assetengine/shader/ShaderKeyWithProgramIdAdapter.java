package org.supply.simulator.display.assetengine.shader;

import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.AssetEngine;
import org.supply.simulator.display.assetengine.WeakReferenceEngine;
import org.supply.simulator.display.assetengine.WeakReferenceWithKeyFactory;

/**
 * Created by Brandon on 2/10/2018.
 */
public class ShaderKeyWithProgramIdAdapter<K extends Comparable<K>> extends WeakReferenceEngine<ShaderKeyWithProgramId<K>, ShaderHandle> implements ShaderEngine<ShaderKeyWithProgramId<K>> {
    private ShaderEngine<K> wrapping;

    //todo not sure about access to this class - it's basically for utility purposes
    ShaderKeyWithProgramIdAdapter(WeakReferenceWithKeyFactory factory, ShaderEngine<K> wrapping) {
        super(factory);
        this.wrapping = wrapping;
    }

    ShaderKeyWithProgramIdAdapter(ShaderEngine<K> wrapping) {
        this.wrapping = wrapping;
    }

    @Override
    protected void destroyHandle(ShaderKeyWithProgramId<K> key) {
        GL20.glDeleteProgram(key.getProgramId());
        key.setProgramId(-1);
    }

    @Override
    protected ShaderHandle createHandle(ShaderKeyWithProgramId<K> key) {
        //todo this code will change for shaders needing different parameters
        int vertexId = -1;
        int fragmentId = -1;
        int programId = -1;


        vertexId = ShaderUtils.loadShader(getVertexResourceName(key), GL20.GL_VERTEX_SHADER);


        fragmentId= ShaderUtils.loadShader(getFragmentResourceName(key), GL20.GL_FRAGMENT_SHADER);

        programId = GL20.glCreateProgram();


        GL20.glAttachShader(programId,vertexId);
        GL20.glAttachShader(programId,fragmentId);

        //Positions information will be attribute 0
        GL20.glBindAttribLocation(programId, 0, "in_Position");
        // Color information will be attribute 1
        GL20.glBindAttribLocation(programId, 1, "in_Color");

        //TODO ADDED FOR TEXTURE
        GL20.glBindAttribLocation(programId, 2, "in_TextureCoord");

        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);

        ShaderHandle data = new ShaderHandle();
        data.setProjectionMatrixLocation(GL20.glGetUniformLocation(programId,"projectionMatrix"));
        data.setViewMatrixLocation(GL20.glGetUniformLocation(programId, "viewMatrix"));
        data.setModelMatrixLocation(GL20.glGetUniformLocation(programId, "modelMatrix"));
        data.setProgramId(programId);
        key.setProgramId(programId);


        return data;
    }

    @Override
    public String getFragmentResourceName(ShaderKeyWithProgramId<K> key) {
        return wrapping.getFragmentResourceName(key.getKey());
    }

    @Override
    public String getVertexResourceName(ShaderKeyWithProgramId<K> key) {
        return wrapping.getVertexResourceName(key.getKey());
    }
}
