package org.supply.simulator.display.chunk;
import org.supply.simulator.display.window.Camera;

import java.util.List;

/**
 * Created by Alex on 6/17/2014.
 */
public interface ChunkManager {

    /**
     *
     * @param view
     */
    public void updateChunks(Camera view);

    /**
     *
     * @return
     */
    public List<Chunk> getChunks();
}
