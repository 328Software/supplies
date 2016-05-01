package org.supply.simulator.core.main;

/**
 * Created by Brandon on 4/30/2016.
 */
public class MenuFactory {


    public Menu getStartMenu() {
        return new Menu() {
            @Override
            public Options next() {
                return null;
            }
        };
    }

    public Menu build(Options build) {
        return null;
    }
}
