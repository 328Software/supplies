package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * A SupplyRenderable object to represent a chunk of the ground.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Chunk extends SupplyRenderable/*, Entity<
        EntityAttribute<Long>,
        EntityStatistic<Object,Long>,
        Long
        >, HasId<Long>*/ {

    public void setIndexManager(ChunkIndexManager indexManager);


}
