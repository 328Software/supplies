package org.supply.simulator.display.assetengine.shader;

import java.util.Comparator;

/**
 * Created by Brandon on 2/10/2018.
 */
public class ShaderKeyWithProgramId<K extends Comparable<K>> implements Comparable<ShaderKeyWithProgramId<K>> {
    private K key;
    private int programId = -1;

    public ShaderKeyWithProgramId(K key) {
        this.key = key;
    }

    public ShaderKeyWithProgramId(K key, int programId) {
        this.key = key;
        this.programId = programId;
    }

    public K getKey() {
        return key;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getProgramId() {
        return programId;
    }

    @Override
    public int compareTo(ShaderKeyWithProgramId<K> o) {
        Comparator<ShaderKeyWithProgramId<K>> comparing = Comparator.comparing(ShaderKeyWithProgramId<K>::getKey);
        return comparing.compare(this, o);
    }
}
