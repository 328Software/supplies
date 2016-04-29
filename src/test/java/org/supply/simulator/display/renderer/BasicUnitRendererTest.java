package org.supply.simulator.display.renderer;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.core.impl.BasicDisplayCore;
import org.supply.simulator.display.extra.DataGenerator;
import org.supply.simulator.display.mock.MockCamera;
import org.supply.simulator.display.renderable.unit.UnitRenderable;
import org.supply.simulator.display.renderable.unit.impl.BasicUnitRenderable;
import org.supply.simulator.display.renderer.unit.impl.BasicUnitRenderer;

import java.util.ArrayList;

/**
 * Created by Alex on 7/27/2014.
 */
public class BasicUnitRendererTest {


    BasicDisplayCore core;
    BasicShaderEngine shaderEngine;
    BasicTextureEngine textureEngine;
    MockCamera camera;
    BasicUnitRenderer unitRenderer;
    DataGenerator dataGenerator;


    public BasicUnitRendererTest () {
        shaderEngine = new BasicShaderEngine();
        textureEngine = new BasicTextureEngine();
        dataGenerator = new DataGenerator();
    }



    @Before
    public void createFixture() {
        core = new BasicDisplayCore();

    }




    @Test
    public void BasicUnitRendererTest() {
        core.setTitleString("BasicUnitRendererTest");
        startup();
        BasicUnitRenderable entityRenderable = dataGenerator.createUnit(-.5f, .5f, 0, -1f, 1f);

        ArrayList<UnitRenderable> list = new ArrayList<>();
        list.add(entityRenderable);

        renderList(list);

        unitRenderer.destroyAll();
        core.stop();

    }



    @Test
    public void BasicUnitRendererTest2() {
        core.setTitleString("BasicUnitRendererTest2");
        startup();




        ArrayList<BasicUnitRenderable> list  = new ArrayList<>();
        for (int i=0;i<6;i++) {

            BasicUnitRenderable entityRenderable = dataGenerator.createUnit((float) (-.7 + (.2 * i)), .7f, 0, .2f, .2f, "textures/alexsface.png");
            list.add(entityRenderable);
        }

        for (int i=0;i<6;i++) {

            BasicUnitRenderable entityRenderable = dataGenerator.createUnit((float) (-.7 + (.2 * i)), -.5f, 0, .2f, .2f, "textures/text2.png");
            list.add(entityRenderable);
        }

        renderList(list);


        unitRenderer.destroyAll();
        core.stop();
    }


    @Test
    public void TextSystemTest() {
        core.setTitleString("TextSystemTest");
        startup();




        ArrayList<BasicUnitRenderable> list  = new ArrayList<>();
        for (int i=0;i<6;i++) {

            BasicUnitRenderable entityRenderable = dataGenerator.createUnit((float) (-.7 + (.2 * i)), .7f, 0, .2f, .2f, "textures/alexsface.png");
            list.add(entityRenderable);
        }

        renderList(list);


        unitRenderer.destroyAll();
        core.stop();
    }

    private void startup() {

        core.start();
        camera = new MockCamera();
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNIT).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNIT).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNIT).getViewMatrixLocation());
        camera.start();
        unitRenderer = new BasicUnitRenderer();
        unitRenderer.setTextureEngine(textureEngine);
        unitRenderer.setAttributeLocations(new int[]{0,1,2});
    }

    private void renderList (ArrayList list) {
        unitRenderer.build(list);
        while(!Display.isCloseRequested()) {

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
            camera.update();
            GL20.glUseProgram(0);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());

            unitRenderer.render(list);
            GL20.glUseProgram(0);
            core.render();
        }


    }
}
