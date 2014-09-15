package org.supply.simulator.core.dao.chunk;

import org.supply.simulator.core.dao.AbstractDAO;
import org.supply.simulator.data.attribute.entity.impl.BasicChunkType;

import java.util.Collection;

/**
 * Created by Alex on 7/17/2014.
 */
public class ChunkTypeDAO extends AbstractDAO {
    @SuppressWarnings("unchecked")
    public Collection<BasicChunkType> findAll() {
        return (Collection<BasicChunkType>)createQueryAndExecute("from org.supply.simulator.data.attribute.entity.impl.BasicChunkType;");
    }
}
