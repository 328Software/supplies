package org.supply.simulator.core.dao.chunk;

import org.supply.simulator.core.dao.AbstractDAO;
import org.supply.simulator.data.entity.impl.Chunk;

import java.util.Collection;

/**
 * Created by Brandon on 7/7/2014.
 */
public class ChunkDAO extends AbstractDAO {

    @SuppressWarnings("unchecked")
    public Collection<Chunk> findAll() {
        return (Collection<Chunk>)createQueryAndExecute("from org.supply.simulator.data.entity.impl.Chunk");
    }

    public Chunk findOneByRowIndexColumnIndexRowsColumns(Integer rowIndex, Integer columnIndex, Integer rows, Integer columns) {
        return (Chunk)createQueryAndReturnOne("select c  as c left join c.data as d where d.rowIndex = ? and d.columnIndex = ? and d.rows = ? and d.columns = ?", rowIndex, columnIndex, rows, columns);
    }
}
