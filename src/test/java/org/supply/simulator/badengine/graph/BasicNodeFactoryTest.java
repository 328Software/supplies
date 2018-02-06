package org.supply.simulator.badengine.graph;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.badengine.graph.impl.BasicNodeFactory;
import org.supply.simulator.badengine.temp.DataGenerator;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.FontTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.factory.TextMenuFactory;
import org.supply.simulator.display.manager.impl.BasicManager;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.util.TextureUtils;

import java.util.ArrayList;
import java.util.List;

public class BasicNodeFactoryTest {

    private static int NUM_NODES = 3;

    private MockDisplayCore core;
    private Camera camera;

//    private DataGenerator generator;

    private BasicShaderEngine shaderEngine;

    private BasicManager manager;
    private Renderer renderer;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("BasicNodeFactoryTest");

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

//        List nodes = new ArrayList<BasicNode>();
//        nodes.add(nodeFactory.createVertex());
        List nodes = nodeFactory.createVertex(NUM_NODES);

        nodes.stream().forEach(v-> {
            ((BasicNode)v).getPositions().stream().forEach(p->System.out.println("TK: "+p.getTextureKey()));
            TextureUtils.applyTexture(((BasicNode)v),textureEngine);
        });




        manager.add(nodes);

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
