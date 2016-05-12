package org.supply.simulator.util;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alex on 5/12/2016.
 */
public class MapUtils {

    public static <K, M> Map.Entry<K, M> newEntry(K firstKey ,M secondKey) {
        return new AbstractMap.SimpleImmutableEntry<>(firstKey, secondKey);
    }

    public static <K extends Comparable<K>,M extends Comparable<M>,V> TreeMap<Map.Entry<K, M>, V> newMultiKeyMap() {
        return new TreeMap<>(getComparator());
    }

    private static <K extends Comparable<K>,M extends Comparable<M>> Comparator<Map.Entry<K,M>> getComparator() {
        return Map.Entry.<K,M>comparingByKey()
                .thenComparing(Map.Entry.<K,M>comparingByValue());
    }
}
