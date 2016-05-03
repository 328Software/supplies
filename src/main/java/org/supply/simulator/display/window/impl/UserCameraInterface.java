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

/**
 * Basic implementation of AbstractCamera.
 *
 * Created by Alex on 6/17/2014.
 */
public class UserCameraInterface {

    private static final float WASD_POS_DELTA = 0.02f;



    Camera camera;

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

      /*  if(Mouse.isButtonDown(0)) {
            int dx = Mouse.getX();
            int dy = Mouse.getY();

            float[] floats = calcWorldCoordinates(dx, dy);

            camera.getCameraPos().set(floats[0], floats[1]);
        }*/

        if (Mouse.isButtonDown(1)) {

            int mouseDeltaX = Mouse.getDX();
            int mouseDeltaY = Mouse.getDY();

            int newMouseX = Mouse.getX();
            int newMouseY = Mouse.getY();

            float[] floats = calcWorldCoordinates(newMouseX, newMouseY);
            float[] offset = calcWorldCoordinates(newMouseX + mouseDeltaX, newMouseY + mouseDeltaY);

            Vector3f.add(camera.getCameraPos(), new Vector3f(offset[0]-floats[0], offset[1]-floats[1], 0), camera.getCameraPos());
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

    private float getWinZ() {
        float zDelta =  getViewZ() - getModelZ();
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
