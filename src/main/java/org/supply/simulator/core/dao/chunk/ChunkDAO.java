package org.supply.simulator.core.dao.chunk;

import org.supply.simulator.core.dao.AbstractDAO;
import org.supply.simulator.display.manager.chunk.Chunk;

/**
 * Created by Brandon on 7/7/2014.
 */
public class ChunkDAO extends AbstractDAO {

    public Chunk findOneByRowIndexColumnIndexRowsColumns(Integer rowIndex, Integer columnIndex, Integer rows, Integer columns) {
        return (Chunk)createQueryAndReturnOne("select c from org.supply.simulator.display.manager.chunk.impl.BasicChunk as c left join c.data as d where d.rowIndex = ? and d.columnIndex = ? and d.rows = ? and d.columns = ?", rowIndex, columnIndex, rows, columns);
    }
}
