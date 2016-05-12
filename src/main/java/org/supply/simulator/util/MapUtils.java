package org.supply.simulator.util;

import org.supply.simulator.display.assetengine.indices.BasicIndexHandle;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alex on 5/12/2016.
 */
public class MapUtils {

    public static final Comparator<Map.Entry<Integer, Integer>> ENTRY_COMPARATOR = Map.Entry.<Integer, Integer>comparingByKey().thenComparing(Map.Entry.<Integer, Integer>comparingByValue());

    public static Map.Entry<Integer, Integer> newEntry(Integer rows,Integer columns) {
        return new AbstractMap.SimpleImmutableEntry(rows,columns);
    }

    public static TreeMap<Map.Entry<Integer, Integer>, BasicIndexHandle> newMap() {
        return new TreeMap<>(ENTRY_COMPARATOR);
    }
}
