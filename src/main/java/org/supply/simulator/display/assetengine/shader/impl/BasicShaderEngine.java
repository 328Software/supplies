package org.supply.simulator.display.assetengine.shader.impl;


import org.supply.simulator.display.assetengine.shader.AbstractShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderHandle;
import org.supply.simulator.display.assetengine.shader.ShaderEngine;

/**
 * Basic implementation of the ShaderEngine interface.
 *
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngine<K,V extends ShaderHandle>
        extends AbstractShaderEngine<K,ShaderHandle>
        implements ShaderEngine<K,ShaderHandle> {

    public BasicShaderEngine () {
        super();    }

    @Override
    public void createProgram(K key) {

    }


}
