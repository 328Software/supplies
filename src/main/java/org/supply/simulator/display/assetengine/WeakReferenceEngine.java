package org.supply.simulator.display.assetengine;

import org.supply.simulator.logging.HasLogger;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Objects.isNull;

/**
 * Thread-safe abstract for asset engine which relies on weak referencing to cleanup
 *
 * Created by Brandon on 5/26/2016.
 */
public abstract class WeakReferenceEngine<K,V> extends HasLogger implements AssetEngine<K, V> {
    private final ReferenceQueue<V> referenceQueue = new ReferenceQueue<>();
    private final Map<K, WeakReferenceWithKey<K,V>> weakRefs = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final WeakReferenceWithKeyFactory factory;

    public WeakReferenceEngine(WeakReferenceWithKeyFactory factory) {
        this.factory = factory;
    }

    public WeakReferenceEngine() {
        factory = WeakReferenceWithKey::new;
    }

    @Override
    public V get(K key) {
        V handle;

        lock.readLock().lock();
        try {
            if(weakRefs.containsKey(key)) {
                handle = weakRefs.get(key).get();
                if(isNull(handle)) {
                    handle = lockAndCreate(key);
                }
            } else {
                handle = lockAndCreate(key);
            }

        } finally {
            lock.readLock().unlock();
        }
        return handle;
    }

    private V lockAndCreate(K key) {
        //must release to upgrade
        lock.readLock().unlock();
        lock.writeLock().lock();
        try {
            V handle;
            handle = createHandle(key);
            weakRefs.put(key, factory.get(key, handle, referenceQueue));
            return handle;
        } finally {
            //downgrade
            lock.readLock().lock();
            lock.writeLock().unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public void cleanup() {
        //note its ok to suppress warnings because only the object
        //can modify its reference queue, guaranteeing it only contains
        //what we put in it
        WeakReferenceWithKey<K,V> ref;
        while((ref = (WeakReferenceWithKey)referenceQueue.poll()) != null) {
            lock.writeLock().lock();
            try {
                weakRefs.remove(ref.getKey());
                destroyHandle(ref.getKey());
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    protected abstract void destroyHandle(K key);
    protected abstract V createHandle(K key);

}
