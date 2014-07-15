package org.supply.simulator.core.dao.usertype;

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
public class ChunkColorType  extends AbstractArrayUserType implements UserType {

    @Override
    public Class returnedClass() {
        return byte[].class;
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
}
