package org.supply.simulator.display.manager.impl;

import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 9/10/2014.
 */
public class BasicChunkManager extends AbstractManager<BasicChunkRenderer> implements Manager<BasicChunkRenderer> {

    @Override
    protected Collection getRenderablesToAdd(Camera camera) {
        return new ArrayList();
    }

    @Override
    protected Collection getRenderablesToRemove(Camera camera) {
        return new ArrayList();
    }
}
