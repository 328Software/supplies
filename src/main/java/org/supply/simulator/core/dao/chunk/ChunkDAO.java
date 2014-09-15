package org.supply.simulator.core.dao.chunk;

import org.supply.simulator.core.dao.AbstractDAO;
import org.supply.simulator.data.entity.impl.BasicChunk;

import java.util.Collection;

/**
 * Created by Brandon on 7/7/2014.
 */
public class ChunkDAO extends AbstractDAO {

    @SuppressWarnings("unchecked")
    public Collection<BasicChunk> findAll() {
        return (Collection<BasicChunk>)createQueryAndExecute("from org.supply.simulator.data.entity.impl.BasicChunk");
    }

    public BasicChunk findOneByRowIndexColumnIndexRowsColumns(Integer rowIndex, Integer columnIndex, Integer rows, Integer columns) {
        return (BasicChunk)createQueryAndReturnOne("select c  as c left join c.data as d where d.rowIndex = ? and d.columnIndex = ? and d.rows = ? and d.columns = ?", rowIndex, columnIndex, rows, columns);
    }
}
