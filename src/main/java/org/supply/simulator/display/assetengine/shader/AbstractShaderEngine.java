package org.supply.simulator.display.assetengine.shader;

import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.WeakReferenceEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brandon on 2/8/2018.
 */
public abstract class AbstractShaderEngine<K extends Comparable<K>> implements ShaderEngine<K> {
    private ShaderKeyWithProgramIdAdapter<K> adapter;
    private Map<K, ShaderKeyWithProgramId<K>> keyMap = new HashMap<>();

    public AbstractShaderEngine() {
        super();
        adapter = new ShaderKeyWithProgramIdAdapter<>(this);
    }

    @Override
    public ShaderHandle get(K key) {
        if(isValid(key)) {
            return adapter.get(keyMap.computeIfAbsent(key, ShaderKeyWithProgramId::new));
        }
        return null;
    }

    public abstract boolean isValid(K key);
}
