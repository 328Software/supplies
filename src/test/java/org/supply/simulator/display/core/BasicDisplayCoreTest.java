package org.supply.simulator.display.core;

import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.core.impl.BasicDisplayCore;
import org.supply.simulator.display.simple.SimplePlayWindow;
import org.supply.simulator.display.window.impl.BasicPlayWindow;

/**
 * Created by Alex on 7/20/2014.
 */
public class BasicDisplayCoreTest {
    BasicDisplayCore core;

    @Before
    public void createFixture() {
        core = new BasicDisplayCore();
        core.setHeight(600);
        core.setWidth(800);
        core.setTitleString("BasicDisplayCoreTest");

        SimplePlayWindow window = new SimplePlayWindow();
        core.setWindow(window);

    }

    @Test
    public void runBasicDisplayTest () {
        core.run();

    }
}
