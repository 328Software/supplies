package org.supply.simulator.display.window.impl;

import org.supply.simulator.display.window.AbstractWindow;
import org.supply.simulator.display.window.Window;

/**
 * Basic implementaion of AbstractWindow.
 *
 * Created by Alex on 6/27/2014.
 */
public class BasicWindow extends AbstractWindow implements Window{
    UserCameraInterface userCameraInterface;

    public BasicWindow() {
        super();
    }

    @Override
    public void update() {
        userCameraInterface.refresh();

        super.update();
    }

    public void setUserCameraInterface(UserCameraInterface userCameraInterface) {
        this.userCameraInterface = userCameraInterface;
    }
}
