package org.supply.simulator.core.main;

import java.util.HashMap;

/**
 * Created by Brandon on 4/30/2016.
 */
public final class Options {
    private final HashMap<String, Object> options = new HashMap<>();
    private final OptionsType type;

    private Options(OptionsType type) {
        this.type = type;
    }


    public static Options newGameOptions() {
        return new Options(OptionsType.GAME_OPTIONS);
    }

    public static Options newMenuOptions() {
        return new Options(OptionsType.MENU_OPTIONS);
    }


    void addOption(String key, Object option) {
        options.put(key, option);
    }

    Object getOption(String key) {
        return options.get(key);
    }

    OptionsType getType() {
        return type;
    }

    enum OptionsType {
        MENU_OPTIONS, GAME_OPTIONS
    }
}
