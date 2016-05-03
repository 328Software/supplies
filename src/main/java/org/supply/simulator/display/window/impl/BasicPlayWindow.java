package org.supply.simulator.display.window.impl;

import org.supply.simulator.display.window.AbstractPlayWindow;
import org.supply.simulator.display.window.Window;

/**
 * Basic implementaion of AbstractPlayWindow.
 *
 * Created by Alex on 6/27/2014.
 */
public class BasicPlayWindow extends AbstractPlayWindow implements Window{
    UserCameraInterface userCameraInterface;

    public BasicPlayWindow() {
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
