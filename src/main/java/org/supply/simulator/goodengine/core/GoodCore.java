package org.supply.simulator.goodengine.core;

import org.supply.simulator.core.container.EntityContainer;
import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.RepeatingScheduleInformation;

public class GoodCore implements RepeatableTask {

    private boolean doDestroy;

    private EntityContainer entities;

    public GoodCore () {
        doDestroy=false;
    }


    public void build() {

    }

    public void update() {

    }

    public void destroy() {
        doDestroy=true;
    }

    @Override
    public RepeatingScheduleInformation getScheduleInformation() {
        return null;
    }

    @Override
    public long getTimeLastStarted() {
        return 0;
    }

    @Override
    public long getTimeLastCompleted() {
        return 0;
    }

    @Override
    public void run() {
        build();

        while (!this.doDestroy) {

        }

        destroy();
    }

    public EntityContainer getEntities() {
        return entities;
    }

    public void setEntities(EntityContainer entities) {
        this.entities = entities;
    }
}
