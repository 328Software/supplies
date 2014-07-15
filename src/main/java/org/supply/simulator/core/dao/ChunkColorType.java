package org.supply.simulator.core.dao;

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

/**
 * Created by Brandon on 7/14/2014.
 */
public class ChunkColorType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[] {
                BlobType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return byte[].class;
    }

    @Override
    public boolean equals(Object o, Object o2) throws HibernateException {
        if(o == null) {
            return o2 == null;
        }
        return o.equals(o2);
//        return Arrays.equals((Byte[])o, (Byte[])o2);
    }


    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        assert strings.length == 1;
        Blob blob = ((Blob)BlobType.INSTANCE.get(resultSet, strings[0], sessionImplementor));
        if(blob != null) {
            return blob.getBytes(1,(int)blob.length());
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if(o == null) {
            BlobType.INSTANCE.set(preparedStatement, null,i,sessionImplementor);
        } else {
            BlobType.INSTANCE.set(preparedStatement, NonContextualLobCreator.INSTANCE.createBlob((byte[])o),i,sessionImplementor);
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        byte[] oBytes = (byte[])o, bytes = new byte[oBytes.length];
        System.arraycopy(oBytes, 0, bytes, 0, oBytes.length);
        return bytes;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (byte[])deepCopy(o);
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
