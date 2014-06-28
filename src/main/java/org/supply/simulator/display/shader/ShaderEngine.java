package org.supply.simulator.display.shader;

/**
 * Created by Alex on 6/26/2014.
 */
public interface ShaderEngine {

    /**
     *
     * @param chunkVertexShader
     */
    void setPlayVertexShader(String chunkVertexShader);

    /**
     *
     * @param chunkTessellationShader
     */
    void setPlayTessellationShader(String chunkTessellationShader);

    /**
     *
     * @param chunkGeometryShader
     */
    void setPlayGeometryShader(String chunkGeometryShader);

    /**
     *
     * @param chunkFragmentShader
     */
    void setPlayFragmentShader(String chunkFragmentShader);

    /**
     *
     * @param chunkVertexShader
     */
    void setPlayComputeShader(String chunkVertexShader);


    /**
     *
     * @param type
     * @return
     */
    int getProjectionMatrixLocation (ShaderProgramType type);

    /**
     *
     * @param type
     * @return
     */
    int getViewMatrixLocation (ShaderProgramType type);

    /**
     *
     * @param type
     * @return
     */
    int getModelMatrixLocation (ShaderProgramType type);

    /**
     *
     * @param type
     */
    void useProgram(ShaderProgramType type);

    /**
     *
     * @param type
     */
    void createProgram(ShaderProgramType type);


    /**
     *
     * @param type
     */
    void deleteProgram(ShaderProgramType type);
}
