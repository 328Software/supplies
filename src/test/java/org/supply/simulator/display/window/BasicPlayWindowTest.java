package org.supply.simulator.display.window;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.supply.simulator.display.core.DisplayCoreTest;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicPlayWindowTest {

    private MockPlayWindow<BasicShaderEngine> window;

    @Before
    public void create() {
        DisplayCoreTest.build();
        window = new MockPlayWindow<BasicShaderEngine>();
        window.build();

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            window.render();

            DisplayCoreTest.render();
        }
    }

    @After
    public void destroy() {
        //window.destroy();


        DisplayCoreTest.destroy();
    }

}
