package org.supply.simulator.core.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;

import java.util.Collection;
import java.util.List;

/**
 * Created by Brandon on 7/7/2014.
 */
public class AbstractDAO {

    protected SessionFactory sessionFactory;


    protected Object createQueryAndReturnOne(String queryString, Object... parameters) {
        return createQueryAndAddParameters(queryString,parameters).uniqueResult();
    }

    protected List<?> createQueryAndExecute(String queryString, Object... parameters) {
        return createQueryAndAddParameters(queryString,parameters).list();
    }

    protected Query createQueryAndAddParameters(String queryString, Object[] parameters) {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        addParametersToQuery(query,parameters);
        return query;
    }

    protected void addParametersToQuery(Query query, Object[] parameters) {
        for(int i = 0; i < parameters.length; i++)  {
            query.setParameter(i,parameters[i]);
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
