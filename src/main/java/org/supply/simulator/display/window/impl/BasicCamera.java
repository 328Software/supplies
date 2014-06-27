package org.supply.simulator.display.window.impl;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicCamera {

    private Vector3f modelPos;
    private Vector3f modelAngle;
    private Vector3f modelScale;
    private Vector3f cameraPos;
    private Vector3f cameraAngle;

    //*****Setters
    public void setCameraAngle(Vector3f cameraAngle) {
        this.cameraAngle = cameraAngle;
    }

    public void setModelPos(Vector3f modelPos) {
        this.modelPos = modelPos;
    }

    public void setModelAngle(Vector3f modelAngle) {
        this.modelAngle = modelAngle;
    }

    public void setModelScale(Vector3f modelScale) {
        this.modelScale = modelScale;
    }

    public void setCameraPos(Vector3f cameraPos) {
        this.cameraPos = cameraPos;
    }
    //*****Setters

    //*****Getters
    public Vector3f getCameraAngle() {
        return this.cameraAngle;
    }

    public Vector3f getModelPos() {
        return this.modelPos;
    }

    public Vector3f getModelAngle() {
        return this.modelAngle;
    }

    public Vector3f getModelScale() {
        return this.modelScale;
    }

    public Vector3f getCameraPos() {
        return this.cameraPos;
    }
    //*****Getters
}
