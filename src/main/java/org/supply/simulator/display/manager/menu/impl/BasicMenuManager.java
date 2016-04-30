package org.supply.simulator.display.manager.menu.impl;

import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderer.menu.MenuRenderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 9/10/2014.
 */
public class BasicMenuManager extends AbstractManager<MenuRenderer> implements Manager<MenuRenderer> {

    @Override
    protected Collection getRenderablesToAdd(Camera camera) {

        return new ArrayList();


    }

    @Override
    protected Collection getRenderablesToRemove(Camera camera) {
        return new ArrayList();
    }


}