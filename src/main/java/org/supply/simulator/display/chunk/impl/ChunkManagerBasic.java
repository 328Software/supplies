package org.supply.simulator.display.chunk.impl;

import org.supply.simulator.display.chunk.Chunk;
import org.supply.simulator.display.chunk.ChunkManager;
import org.supply.simulator.display.chunk.VertexData;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 6/17/2014.
 */
public class ChunkManagerBasic implements ChunkManager{

    ArrayList<Chunk> chunkList;

    private int VIEWDISTANCE;

    /**
     *
     */
    public ChunkManagerBasic () {
        chunkList = new ArrayList<>();
    }



    /**
     *
     * @param view
     */
    public void updateChunks(Camera view) {



    }

    /**
     *
     * @return
     */
    public List<Chunk> getChunks() {
        return chunkList;
    }

    private VertexData getGroundDataFromDAO () {
      //  VertexData[] data = new VertexDataBasic[];
        return null;
    }

}
