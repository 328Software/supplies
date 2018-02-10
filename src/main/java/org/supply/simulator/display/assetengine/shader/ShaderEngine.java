package org.supply.simulator.display.assetengine.shader;

import org.supply.simulator.display.assetengine.AssetEngine;

/**
 * Created by Brandon on 2/9/2018.
 */
public interface ShaderEngine<K> extends AssetEngine<K, ShaderHandle> {
    String getFragmentResourceName(K key);

    String getVertexResourceName(K key);
}
