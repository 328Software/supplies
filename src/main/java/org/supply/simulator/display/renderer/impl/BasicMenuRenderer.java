package org.supply.simulator.display.renderer.impl;

import org.supply.simulator.data.entity.impl.BasicMenu;
import org.supply.simulator.display.assetengine.indices.impl.UnitIndexEngine;
import org.supply.simulator.display.renderer.AbstractRenderer;
import org.supply.simulator.display.renderer.OldAbstractRenderer;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenuRenderer extends AbstractRenderer<BasicMenu> implements EntityRenderer<BasicMenu> {

    @Override
    protected void setIndicesBufferId() {
        indicesBufferId = ((UnitIndexEngine)indexEngine).get(ENTITY_MAX).getIndexId();
    }
}
