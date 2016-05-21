package org.supply.simulator.display.assetengine.texture;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Brandon on 5/21/2016.
 */
public class TextureEngineComposite implements TextureEngine {
    final Set<TextureEngine> textureEngines;


    public TextureEngineComposite() {
        textureEngines = new LinkedHashSet<>();
    }
    public TextureEngineComposite(TextureEngine... textureEngines) {
        this();
        Collections.addAll(this.textureEngines, textureEngines);
    }

    @Override
    public boolean canHandle(String key) {
        return textureEngines.stream().anyMatch(e -> e.canHandle(key));
    }

    @Override
    public TextureHandle get(String key) {
        return textureEngines.stream().filter(e -> e.canHandle(key)).findFirst().orElseThrow(NullPointerException::new).get(key);
    }

    @Override
    public void done(String key) {
        textureEngines.stream().filter(e -> e.canHandle(key)).findFirst().orElseThrow(NullPointerException::new).done(key);
    }

    public void add(TextureEngine engine) {
        this.textureEngines.add(engine);
    }
}
