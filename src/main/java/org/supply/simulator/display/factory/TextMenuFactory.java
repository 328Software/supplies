package org.supply.simulator.display.factory;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;
import org.supply.simulator.display.MenuFactory;
import org.supply.simulator.display.assetengine.texture.Atlas;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.util.TextureUtils;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.arraycopy;
import static org.supply.simulator.display.factory.TexturedVertex.TEXTURE_VERTEX_TOTAL_SIZE;

/**
 * Created by Brandon on 5/8/2016.
 */
public class TextMenuFactory implements MenuFactory {
    private final float topLeftX, topLeftY, length, width;
    private final String text;
    private TextureEngine textureEngine;


    public TextMenuFactory(float topLeftX, float topLeftY, float length, float width, String text) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.length = length;
        this.width = width;
        this.text = text;
    }


    @Override
    public Entity build() {
        return new Entity() {
            final Set<Positions> positions;
            Atlas atlas;

            {
                positions = new HashSet<>();
                for(int i = 0; i < text.length(); i++) {
                    Character c = text.charAt(i);
                    Positions p = TextMenuFactory.getPositions(topLeftX+i*width, topLeftY, 0, length, width);
                    positions.add(p);

                    p.setTextureKey(c.toString());
                }
/*                this.getPositions().stream().filter(p -> nonNull(p.getTextureKey()))
                        .findAny()
                        .ifPresent(p -> this.setAtlas(textureEngine.get(p.getTextureKey()).getAtlas()));*/
                TextureUtils.applyTexture(this, textureEngine);
            }

            @Override
            public Set<Positions> getPositions() {
                return positions;
            }

            @Override
            public void setPositions(Set<Positions> positions) {
            }

            @Override
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

    private static Positions getPositions(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        Positions entityData = Positions.newTexturedColorPositions();
        Vertex v0 = entityData.getVertex(0);
        Vertex v1 = entityData.getVertex(1);
        Vertex v2 = entityData.getVertex(2);
        Vertex v3 = entityData.getVertex(3);
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(1, 1, 1); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(1, 1, 1); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(1, 1, 1); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        return entityData;
    }


    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
