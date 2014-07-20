package org.supply.simulator.core.main;

import org.junit.Test;

/**
 * Created by Brandon on 6/29/2014.
 */
public class MainTest {
    @Test
    public void testMain() {
        try {
            System.out.println("start of main test");
            Main.main(new String[0]);
            System.out.println("end of main test");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failure in main test");
        }
    }
}
