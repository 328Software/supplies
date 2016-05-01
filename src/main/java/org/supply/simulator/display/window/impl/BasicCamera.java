package org.supply.simulator.display.window.impl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
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
        super.setModelPos(new Vector3f(0, 0, -.5f));
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

        Mouse.poll();

        if (Mouse.isButtonDown(1)) {
            int newX = Mouse.getX();
            if(newX> oldX) {
                this.moveEast(posDelta);
            } else if(newX < oldX) {
                this.moveWest(posDelta);
            }
            oldX = newX;


            int newY = Mouse.getY();
            if(newY> oldY) {
                this.moveSouth(posDelta);
            } else if(newY < oldY) {
                this.moveNorth(posDelta);
            }
            oldY = newY;
        }


    }

}