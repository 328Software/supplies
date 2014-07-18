package org.supply.simulator.core.dao.chunk;

import org.supply.simulator.core.dao.AbstractDAO;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.ChunkType;

import java.util.Collection;

/**
 * Created by Alex on 7/17/2014.
 */
public class ChunkTypeDAO extends AbstractDAO {
    @SuppressWarnings("unchecked")
    public Collection<ChunkType> findAll() {
        return (Collection<ChunkType>)createQueryAndExecute("from org.supply.simulator.display.manager.chunk.impl.BasicChunkType");
    }
}
