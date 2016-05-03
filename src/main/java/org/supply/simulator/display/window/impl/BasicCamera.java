package org.supply.simulator.display.window.impl;

import org.joml.GeometryUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.supply.simulator.display.window.AbstractCamera;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

/**
 * Basic implementation of AbstractCamera.
 *
 * Created by Alex on 6/17/2014.
 */
public class BasicCamera extends AbstractCamera {

    private final float rotationDelta = 0.02f;
    private final float rotationDelta2 = 3f;
    private final float posDelta = 0.02f;
    private final float mouseDeltaThresh = 0.000000001f;

    private final float scaleDelta = 0.1f;



    public BasicCamera() {
        super();

       /* glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
        glDepthFunc(GL_ALWAYS); // Change this to whatever kind of depth testing you want
        glDepthRange(0.0f, 1.0f);*/

        Keyboard.enableRepeatEvents(true);
        super.setModelPos(new Vector3f(0, 0, 0));
        super.setModelAngle(new Vector3f(0, 0, 0));
        super.setModelScale(new Vector3f(1, 1, 1));
        super.setCameraPos(new Vector3f(0, 0, -1));
        super.setCameraAngle(new Vector3f(0, 0, 0));
    }

    @Override
    protected void refreshInput() {
        refresh();
    }



    public void refresh() {
        while(Keyboard.next()/*||Mouse.next()|| Mouse.isInsideWindow()*/) {
            // Only listen to events where the key was pressed (down event)
            if (!Keyboard.getEventKeyState()) continue;

            switch (Keyboard.getEventKey()) {

                case Keyboard.KEY_W:
                    this.moveNorth(posDelta);
                    break;
                case Keyboard.KEY_S:
                    this.moveSouth(posDelta);
                    break;
                case Keyboard.KEY_A:
                    this.moveEast(posDelta);
                    break;
                case Keyboard.KEY_D:
                    this.moveWest(posDelta);
                    break;
            }
        }




      /*  if(Mouse.getDY() != 0) {
            Mouse.getX();
            Mouse.getY();
        }*/

//        Mouse.poll();
        if(Mouse.isButtonDown(0)) {
            int dx = Mouse.getX();
            int dy = Mouse.getY();

            float[] floats = calcWorldCoordinates(dx, dy);
//            System.out.println(floats[0]);
//            System.out.println(floats[1]);

            System.out.println(floats[0]);
            System.out.println(floats[1]);

            getCameraPos().set(floats[0], floats[1]);
        }

        if(Mouse.isButtonDown(3)) {
            int dx = Mouse.getX();
            int dy = Mouse.getY();

            float[] floats = calcWorldCoordinates(dx, dy);
//            System.out.println(floats[0]);
//            System.out.println(floats[1]);

            System.out.println(floats[0]);
            System.out.println(floats[1]);

            getCameraPos().set(0, 0);
        }


        if (Mouse.isButtonDown(1)) {

            int newX = Mouse.getDX();
            int newY = Mouse.getDY();

            int dx = Mouse.getX();
            int dy = Mouse.getY();


            Matrix4f orthogonal = orthogonal(0, 800, 600, 0, 0.1f, 100);/*
            Matrix4f perspective = perspective(0, 800, 600, 0, 0.1f, 100);*/




            Vector4f other = new Vector4f(newX, newY, 0, 0);
            Vector4f otherd = new Vector4f();


            Matrix4f.transform(orthogonal, other, otherd);

            System.out.println("derrr");


            System.out.println(otherd);
//            System.out.println(ouwze);
//            Vector4f center = new Vector4f(400, 300, 0, 0);
//            Vector4f ceout = new Vector4f();
//            Matrix4f.transform(orthogonal, center, ceout);


//            double distanceFromCenter = Math.sqrt(Math.pow(ouwze.getX(), 2) + Math.pow(ouwze.getY(), 2));

//                    (float) Math.sqrt(Math.pow(ceout.getX() - ouwze.getX(), 2) + Math.pow(test - tt, 2) + Math.pow(ceout.getY() - ouwze.getY(), 2));
//            System.out.println(spped);

//            System.out.println(getCameraPos());
            float[] floats = calcWorldCoordinates(dx  , dy);
            float[] offset = calcWorldCoordinates(dx+newX  , dy+newY);
//            System.out.println(floats[0]);
//            System.out.println(floats[1]);

//            System.out.println(floats[0]);
//            System.out.println(floats[1]);
//            System.out.println(otherd.getX());
//            System.out.println(otherd.getY());

//            getCameraPos().set(floats[0], floats[1]);
            Vector3f.add(getCameraPos(), new Vector3f(offset[0]-floats[0], offset[1]-floats[1], 0), getCameraPos());
//            Vector3f.add(getCameraPos(), new Vector3f(otherd.getX()*spped, -otherd.getY()*spped, 0), getCameraPos());
//            Vector3f.add(getCameraPos(), new Vector3f(otherd.getX(), -otherd.getY(), 0), getCameraPos());
        }
    }


    public float[] calcWorldCoordinates(int mouseX, int mouseY)
// mouseX is in the range of [0..800] and mouseY in [0..600]
    {

        FloatBuffer model = BufferUtils.createFloatBuffer(16);
        FloatBuffer other = BufferUtils.createFloatBuffer(16);
        getModelMatrix().store(model); model.flip();

        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        getProjectionMatrix().store(projection); projection.flip();
        IntBuffer viewport = BufferUtils.createIntBuffer(16);

        float[] modelCopy = new float[16];
        float[] otherCopy = new float[16];
//        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, model);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, other);
        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);

        projection.get(modelCopy);
        other.get(otherCopy);
        projection.flip();
//        System.out.println(getModelMatrix());

        System.out.println(Arrays.toString(modelCopy));
        System.out.println(Arrays.toString(otherCopy));

//        glGetInteger(GL11.GL_MODELVIEW_MATRIX, model)
//        float viewportHeight = viewport.get(3); // = 600.0f
//        float y = viewportHeight - mouseY;
//        BufferUtils.re11a

        FloatBuffer z = BufferUtils.createFloatBuffer(1);
        glReadPixels(mouseX, mouseY, 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, z);
//        System.out.println(z.get());

        FloatBuffer posNear = BufferUtils.createFloatBuffer(3);

        float winz = (100f*(0.1f-1f)/((100f-0.1f)*-1f));
        System.out.println(winz);
        GLU.gluUnProject(mouseX, mouseY, winz, model, projection, viewport, posNear);

//        System.out.println(posNear.get());
//        System.out.println(posNear.get());

        float[] floats = {posNear.get(), posNear.get()};
        System.out.println(posNear.get());
        return floats;


    }
}
