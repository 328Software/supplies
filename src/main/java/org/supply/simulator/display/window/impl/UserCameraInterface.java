package org.supply.simulator.display.window.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.supply.simulator.display.window.CameraImpl;
import org.supply.simulator.util.MatrixUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Basic implementation of AbstractCamera.
 *
 * Created by Alex on 6/17/2014.
 */
public class UserCameraInterface {

    private final float rotationDelta = 0.02f;
    private final float rotationDelta2 = 3f;
    private final float posDelta = 0.02f;
    private final float mouseDeltaThresh = 0.000000001f;

    private final float scaleDelta = 0.1f;


    CameraImpl camera;

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
                    camera.moveNorth(posDelta);
                    break;
                case Keyboard.KEY_S:
                    camera.moveSouth(posDelta);
                    break;
                case Keyboard.KEY_A:
                    camera.moveEast(posDelta);
                    break;
                case Keyboard.KEY_D:
                    camera.moveWest(posDelta);
                    break;
            }
        }

        if(Mouse.isButtonDown(0)) {
            int dx = Mouse.getX();
            int dy = Mouse.getY();

            float[] floats = calcWorldCoordinates(dx, dy);

            camera.getCameraPos().set(floats[0], floats[1]);
        }

        if (Mouse.isButtonDown(1)) {

            int newX = Mouse.getDX();
            int newY = Mouse.getDY();

            int dx = Mouse.getX();
            int dy = Mouse.getY();

            Matrix4f orthogonal = MatrixUtil.orthogonal(0, 800, 600, 0, 0.1f, 100);

            Vector4f other = new Vector4f(newX, newY, 0, 0);
            Vector4f otherd = new Vector4f();


            Matrix4f.transform(orthogonal, other, otherd);

            float[] floats = calcWorldCoordinates(dx  , dy);
            float[] offset = calcWorldCoordinates(dx+newX  , dy+newY);

            Vector3f.add(camera.getCameraPos(), new Vector3f(offset[0]-floats[0], offset[1]-floats[1], 0), camera.getCameraPos());
        }
    }


    public float[] calcWorldCoordinates(int mouseX, int mouseY) {
        FloatBuffer model = BufferUtils.createFloatBuffer(16);
        camera.getModelMatrix().store(model); model.flip();

        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        camera.getProjectionMatrix().store(projection); projection.flip();
        IntBuffer viewport = BufferUtils.createIntBuffer(16);

        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);

        FloatBuffer z = BufferUtils.createFloatBuffer(1);
        glReadPixels(mouseX, mouseY, 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, z);

        FloatBuffer posNear = BufferUtils.createFloatBuffer(3);

        float winz = (100f*(0.1f-1f)/((100f-0.1f)*-1f));
        GLU.gluUnProject(mouseX, mouseY, winz, model, projection, viewport, posNear);

        return new float[]{posNear.get(), posNear.get()};
    }

    public void setCamera(CameraImpl camera) {
        this.camera = camera;
    }
}
