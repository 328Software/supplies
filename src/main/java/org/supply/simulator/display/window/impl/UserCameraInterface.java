package org.supply.simulator.display.window.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.window.Camera;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Basic implementation of AbstractCamera.
 *
 * Created by Alex on 6/17/2014.
 */
public class UserCameraInterface {

    private static final float WASD_POS_DELTA = 0.02f;
    public static final int SCROLL_FACTOR = 120;
    public static final int MIN_FOV = 5;
    public static final int MAX_FOV = 175;


    Camera camera;
    private int scrollSpeed = 3;

    public UserCameraInterface() {
        super();

        Keyboard.enableRepeatEvents(true);
    }

    public void refresh() {
        while(Keyboard.next()/*||Mouse.next()|| Mouse.isInsideWindow()*/) {
            // Only listen to events where the key was pressed (down event)
            if (!Keyboard.getEventKeyState()) continue;

            switch (Keyboard.getEventKey()) {

                case Keyboard.KEY_W:
                    camera.moveNorth(WASD_POS_DELTA);
                    break;
                case Keyboard.KEY_S:
                    camera.moveSouth(WASD_POS_DELTA);
                    break;
                case Keyboard.KEY_A:
                    camera.moveEast(WASD_POS_DELTA);
                    break;
                case Keyboard.KEY_D:
                    camera.moveWest(WASD_POS_DELTA);
                    break;
            }
        }

        if (Mouse.isButtonDown(1)) {

            int mouseDeltaX = Mouse.getDX();
            int mouseDeltaY = Mouse.getDY();

            int newMouseX = Mouse.getX();
            int newMouseY = Mouse.getY();

            float[] mouseWorldCoordinates = calcWorldCoordinates(newMouseX, newMouseY);


            float[] offset = calcWorldCoordinates(newMouseX + mouseDeltaX, newMouseY + mouseDeltaY);

            Vector3f.add(camera.getCameraPos(), new Vector3f(offset[0]-mouseWorldCoordinates[0], offset[1]-mouseWorldCoordinates[1], 0), camera.getCameraPos());
        }

        if(Mouse.hasWheel()) {
            int wheel = Mouse.getDWheel();
            if(wheel != 0) {

                float oldFov = camera.getFieldOfView();
                float result = min(MAX_FOV, (max(MIN_FOV, oldFov - (wheel / SCROLL_FACTOR) * scrollSpeed)));

//                if(wheel != 0) {

                    int newMouseX = Mouse.getX();
                    int newMouseY = Mouse.getY();

                    float changeDenominator = oldFov - MIN_FOV;
                    if(changeDenominator != 0) {
                        float[] originalCoords = calcWorldCoordinates(newMouseX, newMouseY);
                        camera.setFieldOfView(result);
//                        camera.reproject();
                        float[] newCoords = calcWorldCoordinates(newMouseX, newMouseY);
                        camera.moveWest (originalCoords[0] - newCoords[0] );
                        camera.moveNorth(originalCoords[1] - newCoords[1]);
                    }
               /* } else {
                    camera.setFieldOfView(result);
//                    camera.reproject();
                }*/
            }
        }
    }

    public float[] calcWorldCoordinates(int mouseX, int mouseY) {
        FloatBuffer model = BufferUtils.createFloatBuffer(16);
        camera.getModelMatrix().store(model); model.flip();

        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        camera.getProjectionMatrix().store(projection); projection.flip();

        //todo might be better to build our own viewport
        IntBuffer viewport = BufferUtils.createIntBuffer(16);

        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);

        FloatBuffer posNear = BufferUtils.createFloatBuffer(3);

        GLU.gluUnProject(mouseX, mouseY, getWinZ(), model, projection, viewport, posNear);

        return new float[]{posNear.get(), posNear.get()};
    }

    public float[] uncalcWorldCoordinates(float worldX, float worldY) {
        FloatBuffer model = BufferUtils.createFloatBuffer(16);
        camera.getModelMatrix().store(model); model.flip();

        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        camera.getProjectionMatrix().store(projection); projection.flip();

        //todo might be better to build our own viewport
        IntBuffer viewport = BufferUtils.createIntBuffer(16);

        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);

        FloatBuffer posNear = BufferUtils.createFloatBuffer(3);

        GLU.gluProject(worldX, worldY, getWinZ(), model, projection, viewport, posNear);

        return new float[]{posNear.get(), posNear.get()};
    }

    private float getWinZ() {
        float zDelta =  getViewZ() + getModelZ();
        return camera.getFarPlane()*(camera.getNearPlane()+zDelta)/((camera.getFarPlane()-camera.getNearPlane())*zDelta);
    }

    private float getModelZ() {
        return camera.getModelMatrix().m32;
    }

    private float getViewZ() {
        return camera.getViewMatrix().m32;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
