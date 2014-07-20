package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.data.HasId;
import org.supply.simulator.display.assetengine.texture.TextureType;

/**
 * Created by Alex on 7/20/2014.
 */
public class BasicTextureType implements TextureType, HasId<Long> {
    private Long id;
    private String fileName;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String setFileName(String fileName) {
        return this.fileName=fileName;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }
}
