package org.supply.simulator.display.manager;

import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.window.Camera;

/**
 * Interface for managers of SupplyRenderable objects.
 *
 * Created by Alex on 6/29/2014.
 */
public interface Manager {

    public void setCamera(Camera camera);

    public void setEntityRenderer(EntityRenderer entityRenderer);

    public void start();

    public void update();

    public void stop();



}
