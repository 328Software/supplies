package org.supply.simulator.data.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Menu extends Entity<Long> {

//    @Override
//    public MenuType getType();

//    public void setType(MenuType unitType);

    public Positions getPositions();

    public void setPositions(Positions positions);


}
