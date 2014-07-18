package org.supply.simulator.display.assetengine.shader;

import org.supply.simulator.logging.HasLogger;

import java.util.HashMap;

/**
 * Created by Alex on 7/14/2014.
 */
public abstract class AbstractShaderEngine<K>
        extends HasLogger
        implements ShaderEngine<K> {

    protected HashMap<K,ShaderHandle> shaderMap;

    public AbstractShaderEngine() {
        shaderMap = new HashMap<>(ShaderProgramType.COUNT);
    }

    @Override
    public ShaderHandle get(K key) {
        if (!shaderMap.containsKey(key)) {
            createProgram(key);
        }
        return shaderMap.get(key);
    }


    protected abstract void createProgram(K key);
}
