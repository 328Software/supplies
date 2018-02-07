package org.supply.simulator.badengine.graph.impl;

import org.supply.simulator.badengine.graph.NodeFactory;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.data.entity.impl.BasicPositions;
import org.supply.simulator.display.assetengine.texture.FontTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.factory.TextMenuFactory;
import org.supply.simulator.util.EntityUtils;
import org.supply.simulator.util.FactoryUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class BasicNodeFactory implements NodeFactory {

    private static final float TOP_LEFT_X = -0.5f;
    private static final float TOP_LEFT_Y = -0.5f;
    private static final float TOP_LEFT_Z = 0f;
    private static final float LENGTH = 0.05f;
    private static final float WIDTH = 0.05f;

//    private static final float LETTER_SPACING = 0.06f;

    //this whole node count thing is temporary
    private int node_count;

    private TextureEngine textureEngine;

    public BasicNodeFactory () {
        node_count = 0;
    }

    @Override
    public Node createVertex() {
        Float scale = node_count/10.0f;


        BasicNode node = new BasicNode();
        node.setPositions(getNewPositionsFromTextMenuFactory());
//        node.setPositions(getNewPositions());
        node.setName("n"+node_count);
        node_count=node_count+1;
//
        return node;
    }

    public List<Node> createVertex(int num) {

        List l = new ArrayList<BasicNode>();
        for (int i=0; i<num; i++) {
            l.add(this.createVertex());
        }
        return l;
    }

    private Set<Positions> getNewPositionsFromTextMenuFactory () {

        //using text menu factory for now, will implement unique Node Factory code eventually
        String text = "n"+String.format("%02d", node_count);

        TextMenuFactory t = new TextMenuFactory(text, TOP_LEFT_X,TOP_LEFT_Y, LENGTH, WIDTH);
        t.setTextureEngine(textureEngine);
        Entity e = t.build();
//        EntityUtils.printEntity(e);
        return e.getPositions();
    }


    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }




}
