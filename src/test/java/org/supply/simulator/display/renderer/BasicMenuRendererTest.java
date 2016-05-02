package org.supply.simulator.display.renderer;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.impl.BasicMenu;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.display.renderer.impl.BasicMenuRenderer;

import java.util.ArrayList;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenuRendererTest {


    MockDisplayCore core;
    BasicShaderEngine shaderEngine;
    BasicTextureEngine textureEngine;
    MockCamera camera;
    BasicMenuRenderer menuRenderer;
    DataGenerator dataGenerator;


    public BasicMenuRendererTest () {
        shaderEngine = new BasicShaderEngine();
        textureEngine = new BasicTextureEngine();
        dataGenerator = new DataGenerator();
    }



    @Before
    public void createFixture() {
        core = new MockDisplayCore();

    }

    @Test
    public void MenuRendererTest() {

        startup();




        ArrayList<Menu> list  = new ArrayList<>();
        BasicMenu entityRenderable;
        int i = 0;
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "Y");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "O");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "U");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, " ");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "S");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "U");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "C");
        list.add(entityRenderable);
        entityRenderable = dataGenerator.createMenu((float) (-1f + (.1 * i++)), 1f, 0, -.2f, .1f, "K");
        list.add(entityRenderable);

        renderList(list);


        menuRenderer.destroyAll();
        core.destroy();
    }

    private void startup() {

        core.build("MenuRendererTest");
        camera = new MockCamera();
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.MENU).getViewMatrixLocation());
        camera.start();
        menuRenderer = new BasicMenuRenderer();
        menuRenderer.setTextureEngine(textureEngine);
        menuRenderer.setAttributeLocations(new int[]{0, 1, 2});
    }

    private void renderList (ArrayList list) {
        menuRenderer.build(list);
        while(!Display.isCloseRequested()) {

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
            camera.update();
            GL20.glUseProgram(0);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());

            menuRenderer.render(list);
            GL20.glUseProgram(0);
            core.render();
        }


    }
}
