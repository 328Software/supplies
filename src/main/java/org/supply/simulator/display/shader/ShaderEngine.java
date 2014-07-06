package org.supply.simulator.display.shader;

/**
 * Created by Alex on 6/26/2014.
 */
public interface ShaderEngine {

    /**
     * Sets the file names for the play program type shaders.
     * Must be called before programs are created.
     *
     *
     * @param chunkVertexShader path to shader(glsl) file
     * @param shaderType shader type
     */
    void setPlayShaderFile(String chunkVertexShader, ShaderType shaderType);


    /**
     * Gets the projection matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @param type shader program type
     * @return
     */
    int getProjectionMatrixLocation (ShaderProgramType type);

    /**
     * Gets the view matrix location for the given program type.
     * Must be called after the program is created.
     * @param type shader program type
     * @return
     */
    int getViewMatrixLocation (ShaderProgramType type);

    /**
     * Gets the model matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @param type shader program type
     * @return
     */
    int getModelMatrixLocation (ShaderProgramType type);

    /**
     * Sets current shader program to the specified type
     *
     * @param type shader program type
     */
    void useProgram(ShaderProgramType type);

    /**
     * Creates a shader program of the specified type.
     *
     * @param type shader program type
     */
    void createProgram(ShaderProgramType type);


    /**
     * Deletes the shader program of the specified type.
     *
     * @param type shader program type
     */
    void deleteProgram(ShaderProgramType type);
}
