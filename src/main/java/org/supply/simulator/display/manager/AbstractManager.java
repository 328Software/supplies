package org.supply.simulator.display.manager;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alex on 7/21/2014.
 */
public abstract class AbstractManager extends HasLogger implements Manager {




    protected Collection visibleRenderables;

    protected Camera camera;

    protected EntityRenderer renderer;

    private boolean isAdded;
    private boolean isRemoved;
    private List<Entity> toAdd;
    private List<Entity> toRemove;

    public AbstractManager () {
        isAdded=true;
        isRemoved=true;
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
    }

    @Override
    public void start() {
        visibleRenderables = new ArrayList();
    }
    @Override
    public void update() {

        ////////////REMOVE RENDERABLES
        Collection renderables = getRenderablesToRemove(camera);
        renderer.destroy(renderables);
        visibleRenderables.removeAll(renderables);
        ////////////

        ////////////ADD RENDERABLES
        renderables = getRenderablesToAdd(camera);
        renderer.build(renderables);
        visibleRenderables.addAll(renderables);
        ////////////

        ////////////MANIPULATE RENDERABLES?
        //TODO apply effects that originate from outside game world
        ////////////


        ///////////RENDER RENDERABLES
        renderer.render(visibleRenderables);
        ////////////
    }

    @Override
    public void stop() {
        renderer.destroyAll();
    }


    protected Collection getRenderablesToAdd(Camera camera) {
        //TODO this cant be right, this is inefficient
        ArrayList out = new ArrayList();
        out.addAll(toAdd);
        toAdd=new ArrayList();
        return out;
    }

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

    @Override
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void setEntityRenderer(EntityRenderer entityRenderer) {
        this.renderer = entityRenderer;
    }


}
