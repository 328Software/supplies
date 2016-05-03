package org.supply.simulator.core.main;

import org.supply.simulator.display.core.impl.BasicDisplayCore;

import static org.supply.simulator.core.main.Game.DISPLAY_CORE_KEY;

/**
 * Created by Brandon on 4/30/2016.
 */
public class MainMenus {

    MenuFactory mainMenusFactory;

    BasicDisplayCore displayCore;


    public Options start() {
//        Menu menu = mainMenusFactory.getStartMenu();

       /* Options options;
        while(nonNull(options = menu.next())) {
            if(Options.OptionsType.GAME_OPTIONS.equals(options.getType())) {
                return options;
            }
            menu = mainMenusFactory.build(options);
        }*/

        Options options = Options.newGameOptions();
        options.addOption(DISPLAY_CORE_KEY, displayCore);

        return options;
    }

    public boolean destroyMenus() {
        return true;
    }

    public void setMainMenusFactory(MenuFactory mainMenusFactory) {
        this.mainMenusFactory = mainMenusFactory;
    }

    public void setDisplayCore(BasicDisplayCore displayCore) {
        this.displayCore = displayCore;
    }
}
