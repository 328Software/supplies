package org.supply.simulator.display.manager;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.window.Camera;

/**
 * Interface for managers of SupplyRenderable objects.
 *
 * Created by Alex on 6/29/2014.
 */
public interface Manager<I extends Entity> {

    public void setCamera(Camera camera);

    public void setEntityRenderer(EntityRenderer entityRenderer);

    public void update();

}
