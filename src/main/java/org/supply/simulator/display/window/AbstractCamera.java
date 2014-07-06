package org.supply.simulator.display.window;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfoAbstract;

import java.nio.FloatBuffer;

/**
 * Created by Alex on 7/2/2014.
 */
public abstract class AbstractCamera extends HasRenderableInfoAbstract implements Camera {

    private static final double PI = 3.14159265358979323846;

    private boolean isBuilt;

    protected Vector3f modelPos;
    protected Vector3f modelAngle;
    protected Vector3f modelScale;
    protected Vector3f cameraPos;
    protected Vector3f cameraAngle;

    protected Matrix4f projectionMatrix;
    protected Matrix4f viewMatrix;
    protected Matrix4f modelMatrix;

    protected FloatBuffer matrix44Buffer;

    protected int projectionMatrixLocation;
    protected int modelMatrixLocation;
    protected int viewMatrixLocation;

    public AbstractCamera () {
        isBuilt = false;
    }

    @Override
    public void build () {


        projectionMatrix = new Matrix4f();
        float fieldOfView = 60f;
        float aspectRatio = (float)100 / (float)100;
        float near_plane = 0.1f;
        float far_plane = 100f;

        float y_scale = (float)(1f / Math.tan((fieldOfView / 2f)* (float)(PI / 180d)));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
        projectionMatrix.m33 = 0;

        matrix44Buffer = BufferUtils.createFloatBuffer(16);
        modelPos    =    new Vector3f(0, 0, 0);
        modelAngle  =    new Vector3f(0, 0, 0);
        modelScale  =    new Vector3f(1, 1, 1);
        cameraPos   =    new Vector3f(0, 0,-1);
        cameraAngle =    new Vector3f(0, 0, 0);

        isBuilt= true;
    }


    @Override
    public void update() {
        getNewData();

        // Reset view
        viewMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();

        // Translate and rotate camera
        Matrix4f.translate(cameraPos, viewMatrix, viewMatrix);

        Matrix4f.rotate(cameraAngle.z, new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        Matrix4f.rotate(cameraAngle.y, new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate(cameraAngle.x, new Vector3f(1, 0, 0), viewMatrix, viewMatrix);

        // Translate, rotate and scale model
        Matrix4f.scale(modelScale, modelMatrix, modelMatrix);
        Matrix4f.translate(modelPos, modelMatrix, modelMatrix);

        Matrix4f.rotate(modelAngle.z* (float)(PI / 180d), new Vector3f(0, 0, 1),
                modelMatrix, modelMatrix);
        Matrix4f.rotate(modelAngle.y* (float)(PI / 180d), new Vector3f(0, 1, 0),
                modelMatrix, modelMatrix);
        Matrix4f.rotate(modelAngle.x* (float)(PI / 180d), new Vector3f(1, 0, 0),
                modelMatrix, modelMatrix);
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    @Override
    public void render() {
        projectionMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
        viewMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
        modelMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(modelMatrixLocation, false, matrix44Buffer);
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }


    //***** Movement Methods
    public void moveNorth(float posDelta) {
        cameraPos.y -= posDelta;
    }

    public void moveSouth(float posDelta) {
        cameraPos.y += posDelta;
    }

    public void moveEast(float posDelta) {
        cameraPos.x += posDelta;
    }

    public void moveWest(float posDelta) {
        cameraPos.x -= posDelta;
    }

    public void rotateUp(float rotationDelta) {
        cameraAngle.x += rotationDelta;
    }

    public void rotateDown(float rotationDelta) {
        cameraAngle.x -= rotationDelta;
    }

    public void rotateLeft(float rotationDelta) {
        cameraAngle.y -= rotationDelta;
    }

    public void rotateRight(float rotationDelta) {
        cameraAngle.y += rotationDelta;
    }


    public void rotateMUp(float rotationDelta) {
        modelAngle.x += rotationDelta;
    }

    public void rotateMDown(float rotationDelta) {
        modelAngle.x -= rotationDelta;
    }

    public void rotateMLeft(float rotationDelta) {
        modelAngle.y -= rotationDelta;
    }

    public void rotateMRight(float rotationDelta) {
        modelAngle.y += rotationDelta;
    }
    //***** Movement Methods

    @Override
    public boolean equals(Camera camera) {
        if (camera==null) {
            return false;
        } else {
            return (
                    camera.getModelPos()        ==this.modelPos         &&
                    camera.getModelAngle()      ==this.modelAngle       &&
                    camera.getModelScale()      ==this.modelScale       &&
                    camera.getCameraPos()       ==this.cameraPos        &&
                    camera.getCameraAngle()     ==this.cameraAngle     // &&
                    //        camera.getProjectionMatrix()==this.projectionMatrix &&
                    //        camera.getViewMatrix()      ==this.viewMatrix       &&
                    //        camera.getModelMatrix()     ==this.modelMatrix
            );
        }

    }

    protected abstract void getNewData();

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


    @Override
    public void setProjectionMatrixLocation(int projectionMatrixLocation) {
        this.projectionMatrixLocation = projectionMatrixLocation;
    }

    @Override
    public void setModelMatrixLocation(int modelMatrixLocation) {
        this.modelMatrixLocation = modelMatrixLocation;
    }

    @Override
    public void setViewMatrixLocation(int viewMatrixLocation) {
        this.viewMatrixLocation = viewMatrixLocation;
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


    @Override
    public int getProjectionMatrixLocation() {
        return projectionMatrixLocation;
    }

    @Override
    public int getViewMatrixLocation() {
        return viewMatrixLocation;
    }

    @Override
    public int getModelMatrixLocation() {
        return modelMatrixLocation;
    }
    //*****Getters

}
