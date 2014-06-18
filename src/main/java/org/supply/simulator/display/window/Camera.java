package org.supply.simulator.display.window;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Camera {

    //*****Setters

    /**
     *
     * @param cameraAngle
     */
    public void setCameraAngle(Vector3f cameraAngle);

    /**
     *
     * @param modelPos
     */
    public void setModelPos(Vector3f modelPos);

    /**
     *
     * @param modelAngle
     */
    public void setModelAngle(Vector3f modelAngle);

    /**
     *
     * @param modelScale
     */
    public void setModelScale(Vector3f modelScale);

    /**
     *
     * @param cameraPos
     */
    public void setCameraPos(Vector3f cameraPos);
    //*****Setters

    
    //*****Getters
    /**
     *
     * @return
     */
    public Vector3f getCameraAngle();

    /**
     *
     * @return
     */
    public Vector3f getModelPos();

    /**
     *
     * @return
     */
    public Vector3f getModelAngle();

    /**
     *
     * @return
     */
    public Vector3f getModelScale();

    /**
     *
     * @return
     */
    public Vector3f getCameraPos();
    //*****Getters
}
