package org.supply.simulator.core.dao.usertype;

import org.hibernate.HibernateException;
import org.hibernate.type.BlobType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;

/**
 * Created by Brandon on 7/14/2014.
 */
public abstract class AbstractArrayUserType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[] {
                BlobType.INSTANCE.sqlType()
        };
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
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return deepCopy(serializable);
    }

    @Override
    public Object replace(Object o, Object o2, Object o3) throws HibernateException {
        return deepCopy(o);
    }

    @Override
    public boolean isMutable() {
        return true;
    }
}
