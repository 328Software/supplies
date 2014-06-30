package org.supply.simulator.display.window;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.window.impl.BasicCamera;

/**
 * Created by Alex on 6/29/2014.
 */
public class MockInput {

    private final float rotationDelta = 0.02f;
    private final float rotationDelta2 = 3f;
    private final float posDelta = 0.02f;
    private final float mouseDeltaThresh = 0.000000001f;

    private final float scaleDelta = 0.1f;

    public BasicCamera getCamera() {
        return camera;
    }

    BasicCamera camera;
    public MockInput() {
        Keyboard.enableRepeatEvents(true);
        camera = new BasicCamera();
        camera.setModelPos(new Vector3f(0, 0, 0));
        camera.setModelAngle(new Vector3f(0, 0, 0));
        camera.setModelScale(new Vector3f(1, 1, 1));
        camera.setCameraPos(new Vector3f(0, 0, -1));
        camera.setCameraAngle(new Vector3f(0, 0, 0));
    }

    public void refreshInput() {
        Mouse.poll();
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

                case Keyboard.KEY_UP:
                    camera.rotateUp(rotationDelta);
                    break;
                case Keyboard.KEY_DOWN:
                    camera.rotateDown(rotationDelta);
                    break;
                case Keyboard.KEY_RIGHT:
                    camera.rotateRight(rotationDelta);
                    break;
                case Keyboard.KEY_LEFT:
                    camera.rotateLeft(rotationDelta);
                    break;

                case Keyboard.KEY_I:
                    camera.rotateMUp(rotationDelta2);
                    break;
                case Keyboard.KEY_K:
                    camera.rotateMDown(rotationDelta2);
                    break;
                case Keyboard.KEY_L:
                    camera.rotateMRight(rotationDelta2);
                    break;
                case Keyboard.KEY_J:
                    camera.rotateMLeft(rotationDelta2);
                    break;
            }
        }
    }
}
