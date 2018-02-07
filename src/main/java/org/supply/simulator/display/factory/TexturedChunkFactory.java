package org.supply.simulator.display.factory;

import org.supply.simulator.badengine.terrain.TerrainGenerator;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.ChunkFactory;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.util.TextureUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/21/2016.
 */
public class TexturedChunkFactory implements ChunkFactory {
    private TextureEngine textureEngine;

    private TerrainGenerator terrainGenerator;

    @Override
    public Entity build() {
        return new Entity () {
            final Set<Positions> positions;

            {

                positions = new HashSet<>();


            }

            @Override
            public Set<Positions> getPositions() {
                return positions;
            }

            @Override
            public void setPositions(Set<Positions> positions) {
            }

//            @Override
            public void addAttribute(EntityAttribute attribute) {

            }

            @Override
            public Long getId() {
                return null;
            }

            //            @Override
            public void setId(Long id) {

            }

        };
    }


    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }


    public void setTerrainGenerator(TerrainGenerator terrainGenerator) {
        this.terrainGenerator = terrainGenerator;
    }
}
