package org.supply.simulator.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Brandon on 2/6/2018.
 */
public interface HasLogger {

    default Logger getLogger() {
        return LogManager.getLogger(getClass());
    }
}
