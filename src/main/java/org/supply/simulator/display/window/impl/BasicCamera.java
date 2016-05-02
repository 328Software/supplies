package org.supply.simulator.display.window.impl;

import org.joml.GeometryUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.supply.simulator.display.window.AbstractCamera;

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

    private int oldX;
    private int oldY;



    public BasicCamera() {
        super();
        Keyboard.enableRepeatEvents(true);
        super.setModelPos(new Vector3f(0, 0, -3));
        super.setModelAngle(new Vector3f(0, 0, 0));
        super.setModelScale(new Vector3f(1, 1, 1));
        super.setCameraPos(new Vector3f(0, 0, -1));
        super.setCameraAngle(new Vector3f(0, 0, 0));
        oldX =0;
        oldY =0;
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

        if (Mouse.isButtonDown(1)) {
            int newX = Mouse.getDX();
            int newY = Mouse.getDY();

            Vector4f other = new Vector4f(newX, newY, 0, 0);
            Vector4f otherd = new Vector4f();

            Matrix4f orthogonal = orthogonal(0, 800, 600, 0, 0.1f, 100);


            System.out.println("derrr");

            System.out.println(orthogonal);
            System.out.println(otherd);

            Matrix4f.transform(orthogonal, other, otherd);

            System.out.println(otherd);

            Vector3f.add(getCameraPos(), new Vector3f(otherd.getX()*2, -otherd.getY()*2, 0), getCameraPos());
        }
    }
}
