package org.supply.simulator.display.assetengine.shader;

import org.supply.simulator.display.assetengine.AssetEngine;

/**
 * The shader engine manages shaders for the rest of the display application. It is created by DisplayCore and is
 * passed to and used by Window objects.
 *
 * Created by Alex on 6/26/2014.
 */
public interface ShaderEngine<K,V extends ShaderHandle>  extends AssetEngine<K,ShaderHandle> {
    @Override
    public ShaderHandle get (K type);
}
