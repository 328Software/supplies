package org.supply.simulator.display.renderer;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.assetengine.texture.FontTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.factory.TextMenuFactory;
import org.supply.simulator.display.manager.impl.BasicManager;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.Renderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/8/2016.
 */
public class BasicMenuRendererTest {
    private MockDisplayCore core;
    private Camera camera;

    private DataGenerator generator;

    private BasicShaderEngine shaderEngine;

    private BasicManager manager;
    private Renderer renderer;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("MenuRendererTest");

        generator = new DataGenerator();

        shaderEngine = new BasicShaderEngine();

        camera = new Camera();

        manager = new BasicManager();
        renderer = new Renderer();

        renderer.setAttributeLocations(new int[]{0, 1, 2});
        TextureEngine textureEngine = new FontTextureEngine();
        generator.setTextureEngine(textureEngine);
        renderer.setTextureEngine(textureEngine);
        renderer.setIndexEngine(new BasicIndexEngine());
        renderer.setColumns(1);
        renderer.setRows(100);
        manager.setEntityRenderer(renderer);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
        camera.create();
        manager.start();

        List<Entity> menus = new ArrayList();
//        menus.add(generator.createMenu(-.5f, .25f, 0, .5f, .5f, "a"));
     /*   menus.add(generator.createMenu(-.5f, .25f, 0, .5f, .5f, "textures/rect.png"));
        menus.add(generator.createMenu(.5f, .25f, 0, .5f, .5f, "textures/rect.png"));
        menus.add(generator.createMenu(0.0f, .25f, 0, .5f, .5f, "textures/text2.png"));
        menus.add(generator.createMenu(-0.15f, .8f, 0, .1f, .05f, "Y"));
        menus.add(generator.createMenu(-0.10f, .8f, 0, .1f, .05f, "O"));
        menus.add(generator.createMenu(-0.05f, .8f, 0, .1f, .05f, "U"));
        menus.add(generator.createMenu(0.0f, .8f, 0, .1f, .05f, " "));
        menus.add(generator.createMenu(0.05f, .8f, 0, .1f, .05f, "S"));
        menus.add(generator.createMenu(0.10f, .8f, 0, .1f, .05f, "U"));
        menus.add(generator.createMenu(0.15f, .8f, 0, .1f, .05f, "C"));
        menus.add(generator.createMenu(0.2f, .8f, 0, .1f, .05f, "K"));*/
        TextMenuFactory textMenuFactory = new TextMenuFactory(-0.15f, .8f, .1f, .05f, "KISS A BUTT!?");
        textMenuFactory.setTextureEngine(textureEngine);
        menus.add(textMenuFactory.build());

        manager.add(menus);

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

        manager.stop();
        GL20.glUseProgram(0);
        shaderEngine.done(ShaderProgramType.UNTEXTURED_MOVABLE);
        core.destroy();
    }

}
