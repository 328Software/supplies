package org.supply.simulator.display.manager.impl;

import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderer.impl.BasicMenuRenderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alex on 9/10/2014.
 */
public class BasicMenuManager extends AbstractManager<BasicMenuRenderer> implements Manager<BasicMenuRenderer> {

    private boolean isAdded;
    private boolean isRemoved;
    private List<Menu> toAdd;
    private List<Menu> toRemove;

//    //TODO Remove this method, its for testing.
//    public void setSpecialRenderer(EntityRenderer renderer) {
//        this.renderer=(BasicMenuRenderer)renderer;
//    }

    public BasicMenuManager() {
        super();
        isAdded=true;
        isRemoved=true;
    }

    @Override
    protected Collection getRenderablesToAdd(Camera camera) {
        if (isAdded) return new ArrayList();
        isAdded=true;
        return toAdd;

    }

    @Override
    protected Collection getRenderablesToRemove(Camera camera) {
        if (isRemoved) return new ArrayList();
        isRemoved=true;

        return toRemove;
    }

    public void add(List<Menu> menues) {
        isAdded=false;
        toAdd =menues;
    }

    public void remove(List<Menu> menues) {
        isRemoved=false;
        toRemove =menues;
    }
}