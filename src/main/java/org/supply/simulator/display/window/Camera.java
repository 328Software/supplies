package org.supply.simulator.display.window;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.util.vector.Matrix4f.*;
import static org.supply.simulator.util.MatrixUtil.projection;

/**
 * Created by Alex on 7/2/2014.
 */
public class Camera {

    private static final double PI = Math.PI;
    public static final double PI_OVER_ONE_HUNDRED_EIGHTY = PI / 180d;
    public static final Vector3f Z_VECTOR = new Vector3f(0, 0, 1);
    public static final Vector3f Y_VECTOR = new Vector3f(0, 1, 0);
    public static final Vector3f X_VECTOR = new Vector3f(1, 0, 0);

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


    private float fieldOfView;
    private float aspectRatio;
    private float nearPlane;
    private float farPlane;


    public void create() {
        projectionMatrix = createProjectionMatrix();

        matrix44Buffer = BufferUtils.createFloatBuffer(16);

        //todo this stuff should go in another class
       setModelPos(new Vector3f(0, 0, -5));
       setModelAngle(new Vector3f(0, 0, 0));
       setModelScale(new Vector3f(1, 1, 1));
       setCameraPos(new Vector3f(0, 0, -1));
       setCameraAngle(new Vector3f(0, 0, 0));
    }

    public void update() {
//        projectionMatrix = createProjectionMatrix();
        /*float fieldOfView = 60f;
        float aspectRatio = (float)100 / (float)100;
        float near_plane = 0.1f;
        float far_plane = 100f;*/

        viewMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();

        // Translate and rotate camera
        translate(cameraPos, viewMatrix, viewMatrix);

        rotate(cameraAngle.z, new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        rotate(cameraAngle.y, new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        rotate(cameraAngle.x, new Vector3f(1, 0, 0), viewMatrix, viewMatrix);

        // Translate, rotate and scale model
        scale(modelScale, modelMatrix, modelMatrix);
        translate(modelPos, modelMatrix, modelMatrix);

        rotate(modelAngle.z * (float) PI_OVER_ONE_HUNDRED_EIGHTY, Z_VECTOR,
                modelMatrix, modelMatrix);
        rotate(modelAngle.y * (float)(PI_OVER_ONE_HUNDRED_EIGHTY), Y_VECTOR,
                modelMatrix, modelMatrix);
        rotate(modelAngle.x * (float)(PI_OVER_ONE_HUNDRED_EIGHTY), X_VECTOR,
                modelMatrix, modelMatrix);

        storeProjectionMatrix();

        storeViewMatrix();

        storeModelMatrix();
    }

    private Matrix4f createProjectionMatrix() {
        return projection(fieldOfView, aspectRatio, nearPlane, farPlane);
    }

    private void storeModelMatrix() {
        modelMatrix.store(matrix44Buffer);
        matrix44Buffer.flip();
        glUniformMatrix4(modelMatrixLocation, false, matrix44Buffer);
    }

    private void storeViewMatrix() {
        viewMatrix.store(matrix44Buffer);
        matrix44Buffer.flip();
        glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
    }

    private void storeProjectionMatrix() {
        projectionMatrix.store(matrix44Buffer);
        matrix44Buffer.flip();
        glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
    }

    public void reproject() {
        this.projectionMatrix = createProjectionMatrix();
    }

    public void stop() {

    }


    //***** Movement Methods
    public void zoom(float delta) {
        cameraPos.z += delta;
    }

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
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
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

    public float getFieldOfView() {
        return fieldOfView;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public float getNearPlane() {
        return nearPlane;
    }

    public float getFarPlane() {
        return farPlane;
    }

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

    public void setProjectionMatrixLocation(int projectionMatrixLocation) {
        this.projectionMatrixLocation = projectionMatrixLocation;
    }

    public void setModelMatrixLocation(int modelMatrixLocation) {
        this.modelMatrixLocation = modelMatrixLocation;
    }

    public void setViewMatrixLocation(int viewMatrixLocation) {
        this.viewMatrixLocation = viewMatrixLocation;
    }

    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public void setNearPlane(float nearPlane) {
        this.nearPlane = nearPlane;
    }

    public void setFarPlane(float farPlane) {
        this.farPlane = farPlane;
    }
}
