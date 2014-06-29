package org.supply.simulator.logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Brandon on 6/15/2014.
 */
public abstract class HasLogger {
    protected Logger logger = LogManager.getLogger(this.getClass());

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
