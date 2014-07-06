package org.supply.simulator.display.manager;

import org.supply.simulator.display.supplyrenderable.SupplyRenderable;
import org.supply.simulator.display.window.Camera;

import java.util.Collection;

/**
 * Interface for managers of SupplyRenderable objects.
 *
 * Created by Alex on 6/29/2014.
 */
public interface Manager<K,V extends SupplyRenderable> extends Collection<V>, Iterable<V> {

    /**
     * Updates the chunks using the current camera view.
     *
     *
     * @param view the Camera view
     */
    public void update(Camera view);

}
