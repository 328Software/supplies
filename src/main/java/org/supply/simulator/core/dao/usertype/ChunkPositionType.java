package org.supply.simulator.core.dao.usertype;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.BlobType;
import org.hibernate.usertype.UserType;
import org.supply.simulator.core.dao.util.ArrayFloatByteTranslator;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Brandon on 7/14/2014.
 */
public class ChunkPositionType extends AbstractArrayUserType implements UserType {
    ArrayFloatByteTranslator translator;

    public ChunkPositionType() {
        translator = new ArrayFloatByteTranslator();
    }

    @Override
    public Class returnedClass() {
        return float[].class;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        assert strings.length == 1;
        Blob blob = ((Blob)BlobType.INSTANCE.get(resultSet, strings[0], sessionImplementor));
        if(blob != null) {
            byte[] bytes = blob.getBytes(1,(int)blob.length());
            blob.free();
            return translator.bytesToFloats(bytes);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {
        if(o == null) {
            BlobType.INSTANCE.set(preparedStatement, null,i,sessionImplementor);
        } else {
            float[] floats = (float[])o;
            BlobType.INSTANCE.set(preparedStatement, NonContextualLobCreator.INSTANCE.createBlob(translator.floatsToBytes(floats)),i,sessionImplementor);

        }
    }



    @Override
    public Object deepCopy(Object o) throws HibernateException {
        float[] oFloats = (float[])o, floats = new float[oFloats.length];
        System.arraycopy(oFloats, 0, floats, 0, oFloats.length);
        return floats;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (float[])deepCopy(o);
    }
}
