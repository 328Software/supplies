package org.supply.simulator.display.window;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.renderer.impl.BasicMenuRenderer;
import org.supply.simulator.display.renderer.impl.BasicUnitRenderer;

/**
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractPlayWindow implements Window {

    protected ShaderEngine<ShaderProgramType> shaderEngine;

    protected CameraImpl camera;
    protected Manager<BasicMenuRenderer>  menuManager;
    protected Manager<BasicUnitRenderer>  unitManager;
    protected Manager<BasicChunkRenderer> chunkManager;

    public AbstractPlayWindow() {
    }

    @Override
    public void start() {
        //shaderEngine.createProgram(ShaderProgramType.PLAY);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.create();
        chunkManager.start();
        unitManager.start();
        menuManager.start();

    }

    @Override
    public void update() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,0);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);// | GL11.GL_DEPTH_BUFFER_BIT);

        // Set shader program type to VIEW
        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

        camera.update();

        // Clear shader program type
        GL20.glUseProgram(0);

        // Clear bit
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
        chunkManager.update();
        GL20.glUseProgram(0);

        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
        unitManager.update();
        GL20.glUseProgram(0);

        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
        menuManager.update();
        GL20.glUseProgram(0);

    }

    @Override
    public void stop() {
        chunkManager.stop();
        unitManager.stop();
        menuManager.stop();
        GL20.glUseProgram(0);
        shaderEngine.done(ShaderProgramType.PLAY);
    }

    /**
     * Sets the camera object
     *
     * @param camera
     */
    public void setCamera(CameraImpl camera) {this.camera = camera;}

    /**
     * Sets the manager object for entities
     *
     * @param unitManager
     */
    public void setUnitManager(Manager unitManager) {
        this.unitManager=unitManager;
    }

    /**
     * Sets the manager object for entities
     *
     * @param menuManager
     */
    public void setMenuManager(Manager menuManager) {
        this.menuManager=menuManager;
    }

    /**
     * Sets the manager object for entities
     *
     * @param chunkManager
     */
    public void setChunkManager(Manager chunkManager) {
        this.chunkManager=chunkManager;
    }

    @Override
    public void setShaderEngine(ShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }


}
