package org.supply.simulator.util;

import org.lwjgl.Sys;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;

import java.util.Set;

import static java.util.Objects.nonNull;

/**
 * Created by Brandon on 5/17/2016.
 */
public class TextureUtils {



    @Deprecated
    public static void oldApplyTexture(Entity entity, TextureEngine textureEngine) {
        for(Positions pos : entity.getPositions()) {
            if(nonNull(pos.getTextureKey())) {
//                entity.setAtlas(textureEngine.get(pos.getTextureKey()).getAtlas());
                TextureHandle texture = textureEngine.get(pos.getTextureKey());

                //TODO can this be done on Entity generation?
                pos.getValue()[8] = texture.getSubInfo()[0] / texture.getAtlas().getWidth();  //X0
                pos.getValue()[9] = texture.getSubInfo()[1] / texture.getAtlas().getHeight(); //Y0

                pos.getValue()[18] = texture.getSubInfo()[0] / texture.getAtlas().getWidth();  //X0
                pos.getValue()[19] = texture.getSubInfo()[3] / texture.getAtlas().getHeight(); //Y1

                pos.getValue()[28] = texture.getSubInfo()[2] / texture.getAtlas().getWidth();  //X1
                pos.getValue()[29] = texture.getSubInfo()[3] / texture.getAtlas().getHeight(); //Y1

                pos.getValue()[38] = texture.getSubInfo()[2] / texture.getAtlas().getWidth();  //X1
                pos.getValue()[39] = texture.getSubInfo()[1] / texture.getAtlas().getHeight(); //Y0

            }
        }
    }

    public static void applyTexture(Entity entity, TextureEngine textureEngine) {
        for(Positions pos : entity.getPositions()) {
            if(nonNull(pos.getTextureKey())) {
//                System.out.println("hey");
//                entity.setAtlas(textureEngine.get(pos.getTextureKey()).getAtlas());
//                System.out.println("key:"+pos.getTextureKey());
                TextureHandle texture = textureEngine.get(pos.getTextureKey());

                //TODO can this be done on Entity generation?
                pos.getValue()[8] = texture.getSubInfo()[0];
                pos.getValue()[9] = texture.getSubInfo()[1];

                pos.getValue()[18] = texture.getSubInfo()[0];
                pos.getValue()[19] = texture.getSubInfo()[3];

                pos.getValue()[28] = texture.getSubInfo()[2];
                pos.getValue()[29] = texture.getSubInfo()[3];

                pos.getValue()[38] = texture.getSubInfo()[2];
                pos.getValue()[39] = texture.getSubInfo()[1];

            }
        }
    }
}
