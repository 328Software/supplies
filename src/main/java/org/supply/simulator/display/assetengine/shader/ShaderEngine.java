package org.supply.simulator.display.assetengine.shader;

import org.supply.simulator.display.assetengine.AssetEngine;

import java.util.Map;

/**
 * The shader engine manages shaders for the rest of the display application. It is created by DisplayCore and is
 * passed to and used by Window objects.
 *
 * Created by Alex on 6/26/2014.
 */
public interface ShaderEngine<K extends ShaderProgramType,V extends ShaderData>  extends AssetEngine<ShaderProgramType,ShaderData>{

    /**
     *
     * @param fileName
     * @param pType
     */
    public void setShaderFile(String fileName, ShaderType sType, ShaderProgramType pType);

    @Override
    public ShaderData get (ShaderProgramType type);


//    /**
//     * Sets current shader program to the specified type
//     *
//     * @param type shader program type
//     */
//    void useProgram(ShaderProgramType type);

//    /**
//     * Creates a shader program of the specified type.
//     *
//     * @param type shader program type
//     */
//    void createProgram(ShaderProgramType type);

//
//    /**
//     * Deletes the shader program of the specified type.
//     *
//     * @param type shader program type
//     */
//    void deleteProgram(ShaderProgramType type);

    /**
//     * Sets the file names for the play program type shaders.
//     * Must be called before programs are created.
//     *
//     *
//     * @param chunkVertexShader path to shader(glsl) file
//     * @param shaderType shader type
//     */
//    void setPlayShaderFile(String chunkVertexShader, ShaderType shaderType);



}
