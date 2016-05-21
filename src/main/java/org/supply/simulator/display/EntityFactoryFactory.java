package org.supply.simulator.display;

import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.core.DisplayCore;
import org.supply.simulator.display.manager.impl.BasicManager;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.Camera;

/**
 * Created by Brandon on 5/8/2016.
 */
public class EntityFactoryFactory {


    private DisplayCore core;
    private Camera camera;

    private BasicShaderEngine shaderEngine;

    private BasicManager manager;
    private Renderer renderer;

    //todo perhaps we'll construct this on the fly, or else wire it up.
    //i lean towards wiring it up because on the fly construction might imply
    //there could exist more than one factoryfactory, which probably should not be the case
    //there's no contract for method signatures though, so modifications can be made to support
    //addition configuration

    public UnitFactory buildUnitFactory() {
        return null;
    }

    public MenuFactory buildMenuFactory() {
        return null;

    }

    public ChunkFactory buildChunkFactory() {
        return null;

    }



}
