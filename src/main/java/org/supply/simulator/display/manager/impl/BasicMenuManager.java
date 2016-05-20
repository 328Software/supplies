package org.supply.simulator.display.manager.impl;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by Alex on 9/10/2014.
 */
public class BasicMenuManager extends AbstractManager implements Manager {

    private boolean isAdded;
    private boolean isRemoved;
    private List<Entity> toAdd;
    private List<Entity> toRemove;

//    //TODO Remove this method, its for testing.
//    public void setSpecialRenderer(EntityRenderer renderer) {
//        this.renderer=(BasicMenuRenderer)renderer;
//    }

    public BasicMenuManager() {
        super();
        isAdded=true;
        isRemoved=true;
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();

    }

    @Override
    protected Collection getRenderablesToAdd(Camera camera) {
        //TODO this cant be right, this is inefficient
        ArrayList out = new ArrayList();
        out.addAll(toAdd);
        toAdd=new ArrayList();
        return out;
    }

    @Override
    protected Collection getRenderablesToRemove(Camera camera) {
        ArrayList out = new ArrayList();
        out.addAll(toRemove);
        toRemove=new ArrayList();
        return out;
    }

    public void add(List<Entity> menues) {
        toAdd.addAll(menues);
    }

    public void remove(List<Entity> menues) {
        toRemove.addAll(menues);
    }
}