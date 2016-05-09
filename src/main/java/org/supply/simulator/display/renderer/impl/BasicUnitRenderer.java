package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.impl.BasicUnit;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.renderer.AbstractRenderer;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 7/21/2014.
 */
public class BasicUnitRenderer extends AbstractRenderer<BasicUnit> implements EntityRenderer<BasicUnit> {

    @Override
    protected void setIndicesBufferId() {
        indicesBufferId = ((UnitIndexEngine)indexEngine).get(ENTITY_MAX).getIndexId();
    }
}
