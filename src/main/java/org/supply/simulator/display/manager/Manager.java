package org.supply.simulator.display.manager;

import org.supply.simulator.display.core.SupplyDisplay;
import org.supply.simulator.display.renderer.EntityRenderer;
import org.supply.simulator.display.window.Camera;

/**
 * Interface for managers of SupplyRenderable objects.
 *
 * Created by Alex on 6/29/2014.
 */
public interface Manager<R extends EntityRenderer> extends SupplyDisplay {

    public void setCamera(Camera camera);

    public void setEntityRenderer(R entityRenderer);



}
