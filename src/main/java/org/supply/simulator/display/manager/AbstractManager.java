package org.supply.simulator.display.manager;

import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.CameraImpl;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 7/21/2014.
 */
public abstract class AbstractManager<R extends EntityRenderer> extends HasLogger implements Manager<R> {




    protected Collection visibleRenderables;

    protected CameraImpl camera;

    protected R renderer;

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


    /**
     *
     * @param camera
     * @return
     */
    protected abstract Collection getRenderablesToAdd(CameraImpl camera);


    /**
     *
     * @param camera
     * @return
     */
    protected abstract Collection getRenderablesToRemove(CameraImpl camera);



    @Override
    public void setCamera(CameraImpl camera) {
        this.camera = camera;
    }

    public void setEntityRenderer(R entityRenderer) {
        this.renderer = entityRenderer;
    }


}
