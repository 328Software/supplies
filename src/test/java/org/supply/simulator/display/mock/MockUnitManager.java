package org.supply.simulator.display.mock;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 7/27/2014.
 */
public class MockUnitManager extends AbstractManager implements Manager {
    private DataGenerator dataGenerator;
    private boolean isFirst;

    public  MockUnitManager () {
        dataGenerator = new DataGenerator();
        dataGenerator.setTextureEngine(new BasicTextureEngine());
        isFirst=true;
    }

    @Override
    protected Collection<Entity> getRenderablesToAdd(Camera view) {
        Collection<Entity> list = new ArrayList();


        if (isFirst) {
            list.add(dataGenerator.createUnit(-.5f, .25f, 0, .5f, .5f));
            isFirst=false;
        }


        return list;
    }

    @Override
    protected Collection getRenderablesToRemove(Camera view) {
        return new ArrayList();
    }
}
