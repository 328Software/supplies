package org.supply.simulator.display.window;

import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * Displays representation of the camera. Is SupplyRenderable. Also contains a lot of methods to control camera.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Camera extends SupplyRenderable {

    /**
     * Compares this to another camera object
     *
     * @param camera another camera object
     * @return true if the two object are equal, false if not
     */
    public boolean equals(Camera camera);

    /**
     * Sets the camera position vector.
     *
     * @param cameraPos
     */
    public void setCameraPos(Vector3f cameraPos);

    /**
     * Sets the camera angle vector.
     *
     * @param cameraAngle
     */
    public void setCameraAngle(Vector3f cameraAngle);

    /**
     * Sets the model position vector.
     *
     * @param modelPos
     */
    public void setModelPos(Vector3f modelPos);

    /**
     * Sets the model angle vector.
     *
     * @param modelAngle
     */
    public void setModelAngle(Vector3f modelAngle);

    /**
     * Sets the model scale vector.
     *
     * @param modelScale
     */
    public void setModelScale(Vector3f modelScale);


    /**
     * Sets the handle to the projection matrix
     *
     * @param projectionMatrixLocation
     */
    public void setProjectionMatrixLocation(int projectionMatrixLocation);

    /**
     * Sets the handle to the model matrix
     *
     * @param modelMatrixLocation
     */
    public void setModelMatrixLocation(int modelMatrixLocation);

    /**
     * Sets the handle to the view matrix
     *
     * @param viewMatrixLocation
     */
    public void setViewMatrixLocation(int viewMatrixLocation);

    /**
     * Gets camera position vector.
     *
     * @return
     */
    public Vector3f getCameraPos();

    /**
     * Gets camera angle vector.
     *
     * @return
     */
    public Vector3f getCameraAngle();

    /**
     * Gets model position vector.
     *
     * @return
     */
    public Vector3f getModelPos();

    /**
     * Gets model angle vector.
     *
     * @return
     */
    public Vector3f getModelAngle();

    /**
     * Gets model scale vector.
     *
     * @return
     */
    public Vector3f getModelScale();



}
