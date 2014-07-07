package org.supply.simulator.display.shader;

/**
 * The shader engine manages shaders for the rest of the display application. It is created by DisplayCore and is
 * passed to and used by Window objects.
 *
 * Created by Alex on 6/26/2014.
 */
public interface ShaderEngine {

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
     * @return projection matrix location
     */
    int getProjectionMatrixLocation (ShaderProgramType type);

    /**
     * Gets the view matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @param type shader program type
     * @return view matrix location
     */
    int getViewMatrixLocation (ShaderProgramType type);

    /**
     * Gets the model matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @param type shader program type
     * @return model matrix location
     */
    int getModelMatrixLocation (ShaderProgramType type);
}
