package org.supply.simulator.display.renderer;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.manager.impl.BasicMenuManager;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.BasicMenuRenderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenuRendererTest {
    private MockDisplayCore core;
    private Camera camera;

    private DataGenerator generator;

    private BasicShaderEngine shaderEngine;

    //    private MockUnitManager manager;
//    private BasicUnitRenderer renderer;
    private BasicMenuManager manager;
    private BasicMenuRenderer renderer;

    @Before
    public void create() {
        core = new MockDisplayCore();
        core.build("MenuRendererTest");

        generator = new DataGenerator();

        shaderEngine = new BasicShaderEngine();

        camera = new Camera();

        manager = new BasicMenuManager();
        renderer = new BasicMenuRenderer();
//        manager = new MockUnitManager();
//        renderer = new BasicUnitRenderer();
        renderer.setAttributeLocations(new int[]{0, 1, 2});
        renderer.setTextureEngine(new BasicTextureEngine());
        manager.setEntityRenderer(renderer);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.create();
        manager.start();

        List<Menu> menus = new ArrayList();
        menus.add(generator.createMenu(-.5f, .25f, 0, .5f, .5f, "textures/text2.png"));
        manager.add(menus);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);// | GL11.GL_DEPTH_BUFFER_BIT);

            // Set shader program type to VIEW
            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.update();

            // Clear shader program type
            GL20.glUseProgram(0);

            // Clear bit
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//
//
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
//            chunkManager.update();
//            GL20.glUseProgram(0);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
            manager.update();
            GL20.glUseProgram(0);
//
//            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
//            menuManager.update();
//            GL20.glUseProgram(0);

            core.render();
        }

        manager.stop();
        GL20.glUseProgram(0);
        shaderEngine.done(ShaderProgramType.PLAY);
        core.destroy();
    }

}