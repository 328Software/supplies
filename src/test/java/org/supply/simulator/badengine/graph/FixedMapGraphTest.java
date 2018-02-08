package org.supply.simulator.badengine.graph;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.badengine.graph.impl.BasicNodeFactory;
import org.supply.simulator.badengine.graph.impl.NodeGraphGenerator;
import org.supply.simulator.util.DataGenerator;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.impl.BasicMapGraph;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.FontTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.manager.impl.BasicManager;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.util.GraphUtils;
import org.supply.simulator.util.PositionsUtil;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class FixedMapGraphTest extends NodeGraphGenerator {

    private static int NUM_NODES = 3;

    private MockDisplayCore core;
    private Camera camera;

    private BasicShaderEngine shaderEngine;

    private BasicManager manager;
    private Renderer renderer;

    @Before
    public void create() throws ExportException, IOException {

        core = new MockDisplayCore();
        core.build("FixedMapGraphTest");

        shaderEngine = new BasicShaderEngine();

        camera = new Camera();

        manager = new BasicManager();
        renderer = new Renderer();

        renderer.setAttributeLocations(new int[]{0, 1, 2});
        TextureEngine textureEngine = new FontTextureEngine();
        renderer.setTextureEngine(textureEngine);
        renderer.setIndexEngine(new BasicIndexEngine());
        renderer.setColumns(1);
        renderer.setRows(100);
        manager.setEntityRenderer(renderer);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
        camera.create();

        BasicNodeFactory nodeFactory = new BasicNodeFactory();
        nodeFactory.setTextureEngine(textureEngine);

        this.setNodeFactory(nodeFactory);
        Graph<Node,Edge> g  = DataGenerator.fiveNodeGraph();

        Node n = g.vertexSet().iterator().next();
        arrangeNodes(g,n, 1);

        GraphUtils.printGraph(g);


        File file = new File("./src/main/resources/graphs/graph.gml");

//        g.exportGraph(file);

//        Set nodes = g.getNodeSet();
//
//
////
        manager.add(g.vertexSet());

    }

    protected void arrangeNodes(Graph g, Node n, int c1) {
        Set<Edge> edges = g.edgesOf(n);

        float angle = 180/(edges.size());
        int c2 = 0;
        for(Edge e : edges) {
//            float scale = count/edges
            Node src = e.getSource();
            Node tgt = e.getTarget();

            System.out.println(src.getName()+"  C1:"+c1+" C2:"+c2);
            if (src.equals(n)) { //only follow edges that start at the src Node


                GraphUtils.copyXYZvalues(src,tgt);
                float dx = .3f*c1;
                float dy = .3f*c2;
//                float dx = Math.round(1000*Math.sin(angle*count*Math.PI/180))/1000;
//                float dy = Math.round(1000*Math.cos(angle*count*Math.PI/180))/1000;
                System.out.println(tgt.getName()+"          dx:"+dx+"    dy:"+dy);
                PositionsUtil.movePositionsXY(tgt.getPositions(), dx, dy);
                arrangeNodes(g,tgt,1);
            }
            c2++;
        }
    }



    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);// | GL11.GL_DEPTH_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());

            camera.update();

            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.TEXTURED_STATIONARY).getProgramId());
            manager.update();
            GL20.glUseProgram(0);

            core.render();
        }

        GL20.glUseProgram(0);
        shaderEngine.done(ShaderProgramType.UNTEXTURED_MOVABLE);
        core.destroy();
    }

}
