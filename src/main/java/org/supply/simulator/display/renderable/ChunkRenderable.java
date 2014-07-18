package org.supply.simulator.display.renderable;

import org.lwjgl.util.Renderable;
import org.supply.simulator.data.HasId;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.display.renderable.SupplyRenderable;

/**
 * Interface for all objects that will be rendered by the Display Engine. All OpenGl code will be in classes that 
 * implement this interface(with the exception of the ShaderEngine).
 *
 * Created by Alex on 6/29/2014.
 */
public interface ChunkRenderable extends SupplyRenderable, Renderable {

    void setIndicesBufferId (int indicesBufferId);

    public ChunkType getChunkType();


}
