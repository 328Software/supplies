package org.supply.simulator.core.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BlobType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 7/13/2014.
 */
public class ChunkPositionType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[] {
                BlobType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return List.class;
    }

    @Override
    public boolean equals(Object o, Object o2) throws HibernateException {
        if(o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        assert strings.length == 1;
        Blob blob = ((Blob)BlobType.INSTANCE.get(resultSet, strings[0], sessionImplementor));
        if(blob != null) {
            byte[] data = blob.getBytes(1,(int)blob.length());
            blob.free();
            try {
                List<Float> out = mapper.readValue(data, new TypeReference<List<Float>>() { });
                return out;
            } catch (Exception e) {
                throw new HibernateException("This ain't good.");
            }
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if(o == null) {
            System.out.println("o is null");
            BlobType.INSTANCE.set(preparedStatement, null,i,sessionImplementor);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                byte[] data = mapper.writeValueAsBytes((List<Float>) o);
                BlobType.INSTANCE.set(preparedStatement, NonContextualLobCreator.INSTANCE.createBlob(data),i,sessionImplementor);
            } catch (Exception e) {
                throw new HibernateException("This settin wasn't good.");
            }

        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        List<Float> list = new ArrayList<Float>();
        for(Float b: (ArrayList<Float>)o) {
            list.add(b);
        }
        return list;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return new ArrayList<Float>((List<Float>)o);
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return deepCopy(serializable);
    }

    @Override
    public Object replace(Object o, Object o2, Object o3) throws HibernateException {
        return deepCopy(o);
    }
}
