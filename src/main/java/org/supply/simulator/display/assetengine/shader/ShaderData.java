package org.supply.simulator.display.assetengine.shader;

/**
 * Created by Alex on 7/13/2014.
 */
public interface ShaderData {

    void setModelMatrixLocation(int modelMatrixLocation);

    void setProjectionMatrixLocation(int projectionMatrixLocation);

    void setViewMatrixLocation(int viewMatrixLocation);

    void setProgramId(int programId);

    /**
     * Gets the projection matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @return projection matrix location
     */
    int getProjectionMatrixLocation ();

    /**
     * Gets the view matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @return view matrix location
     */
    int getViewMatrixLocation ();

    /**
     * Gets the model matrix location for the given program type.
     * Must be called after the program is created.
     *
     * @return model matrix location
     */
    int getModelMatrixLocation ();

    int getProgramId();
}
