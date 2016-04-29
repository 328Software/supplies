package org.supply.simulator.display.renderable;

import org.supply.simulator.data.entity.Entity;

/**
 * Created by Alex on 7/13/2014.
 */
public interface EntityRenderable{


    /**
     * Checks if this object has been built yet.
     *
     * @return false until isBuilt() has been called
     */
    public boolean isBuilt();


    /**
     * Checks if this object has been destroyed yet.
     *
     * @return false until destroy() has been called
     */
    public boolean isDestroyed();


    /**
     *
     * @param entity
     */
    public void setEntity(Entity entity);

}
