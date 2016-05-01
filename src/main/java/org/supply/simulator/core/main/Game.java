package org.supply.simulator.core.main;

import org.supply.simulator.display.core.impl.BasicDisplayCore;

/**
 * Created by Brandon on 4/30/2016.
 */
public class Game {

    public static String DISPLAY_CORE_KEY = "displayCore";
    private BasicDisplayCore displayCore;

    private Game(BasicDisplayCore displayCore) {

        this.displayCore = displayCore;
    }

    public static Game newInstance(Options gameOptions) {
        BasicDisplayCore displayCore = (BasicDisplayCore) gameOptions.getOption(DISPLAY_CORE_KEY);

        return new Game(displayCore);
    }

    public boolean start() {
        try {
            displayCore.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
