package org.supply.simulator.display.chunk;
import org.supply.simulator.display.window.Camera;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 6/17/2014.
 */
public interface ChunkManager<
                         //TYPES:
        K,               //CHUNK ID: used to get chunks from DB
        V extends Chunk, //CHUNK
        T,               //VERTEX DATA
        S                //TYPE where chunks are stored
        > {

    /**
     *
     * @param view
     */
    public void updateChunks(Camera view);

    /**
     *
     * @return
     */
    public S getChunks();
}
