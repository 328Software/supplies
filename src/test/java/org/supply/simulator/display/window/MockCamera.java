package org.supply.simulator.display.window;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.window.impl.BasicCamera;

/**
 * Created by Alex on 7/2/2014.
 */
public class MockCamera extends AbstractCamera{
    private final float rotationDelta = 0.02f;
    private final float rotationDelta2 = 3f;
    private final float posDelta = 0.02f;
    private final float mouseDeltaThresh = 0.000000001f;

    private final float scaleDelta = 0.1f;

    public MockCamera() {
        super();
        Keyboard.enableRepeatEvents(true);
        this.setModelPos(new Vector3f(0, 0, 0));
        this.setModelAngle(new Vector3f(0, 0, 0));
        this.setModelScale(new Vector3f(1, 1, 1));
        this.setCameraPos(new Vector3f(0, 0, -1));
        this.setCameraAngle(new Vector3f(0, 0, 0));
    }

    @Override
    protected void update() {
        refreshInput();
    }

    public void refreshInput() {
        Mouse.poll();
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

                case Keyboard.KEY_UP:
                    this.rotateUp(rotationDelta);
                    break;
                case Keyboard.KEY_DOWN:
                    this.rotateDown(rotationDelta);
                    break;
                case Keyboard.KEY_RIGHT:
                    this.rotateRight(rotationDelta);
                    break;
                case Keyboard.KEY_LEFT:
                    this.rotateLeft(rotationDelta);
                    break;

                case Keyboard.KEY_I:
                    this.rotateMUp(rotationDelta2);
                    break;
                case Keyboard.KEY_K:
                    this.rotateMDown(rotationDelta2);
                    break;
                case Keyboard.KEY_L:
                    this.rotateMRight(rotationDelta2);
                    break;
                case Keyboard.KEY_J:
                    this.rotateMLeft(rotationDelta2);
                    break;
            }
        }
    }
}
