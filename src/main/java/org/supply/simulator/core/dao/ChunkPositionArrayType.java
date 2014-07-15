package org.supply.simulator.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BlobType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Brandon on 7/14/2014.
 */
public class ChunkPositionArrayType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[] {
                BlobType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return float[].class;
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
            byte[] bytes = blob.getBytes(1,(int)blob.length());
            blob.free();
            return bytesToFloats(bytes);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if(o == null) {
            System.out.println("o is null");
            BlobType.INSTANCE.set(preparedStatement, null,i,sessionImplementor);
        } else {
            float[] floats = (float[])o;
            BlobType.INSTANCE.set(preparedStatement, NonContextualLobCreator.INSTANCE.createBlob(floatsToBytes(floats)),i,sessionImplementor);

        }
    }

    protected float[] bytesToFloats(byte[] bytes) {
        //todo this is probably pretty inefficient wrt memory
        float[] floats = new float[bytes.length/4];
        for(int i = 0,j=i; i < bytes.length; i+=4,j++) {
            byte[] floatBytes = new byte[4];
            floatBytes[0] = bytes[i];
            floatBytes[1] = bytes[i+1];
            floatBytes[2] = bytes[i+2];
            floatBytes[3] = bytes[i+3];
            floats[j] = ByteBuffer.wrap(floatBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
        }
        return floats;
    }

    protected byte[] floatsToBytes(float[] floats) {
        ByteBuffer b = ByteBuffer.allocate(floats.length*4);
        for(float f: floats) {
            b.putFloat(f);
        }
        return b.array();
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        float[] oFloats = (float[])o, floats = new float[oFloats.length];
        System.arraycopy(oFloats, 0, floats, 0, oFloats.length);
        return floats;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (float[])deepCopy(o);
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
