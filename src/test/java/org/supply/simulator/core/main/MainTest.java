package org.supply.simulator.core.main;

import org.junit.Test;

/**
 * Created by Brandon on 6/29/2014.
 */
public class MainTest {
    @Test
    public void testMain() {
        try {
            System.out.println("could it be?");
            Main.main(new String[0]);
            System.out.println("magic");
        } catch (Exception e) {
            System.out.println("no waay");
        }
    }
}
