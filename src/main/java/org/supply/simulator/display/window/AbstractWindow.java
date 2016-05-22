package org.supply.simulator.display.window;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.manager.Manager;

/**
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractWindow implements Window {

    protected BasicShaderEngine shaderEngine;

    protected Camera camera;
    protected Manager menuManager;
    protected Manager unitManager;
    protected Manager chunkManager;

    public AbstractWindow() {
    }

    @Override
    public void start() {
        //shaderEngine.createProgram(ShaderProgramType.UNTEXTURED_MOVABLE);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getViewMatrixLocation());
        camera.create();

    }

    @Override
    public void update() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,0);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);// | GL11.GL_DEPTH_BUFFER_BIT);

        // Set shader program type to VIEW
        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());

        camera.update();

        // Clear shader program type
        GL20.glUseProgram(0);

        // Clear bit
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.UNTEXTURED_MOVABLE).getProgramId());
        chunkManager.update();
        GL20.glUseProgram(0);

        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.TEXTURED_STATIONARY).getProgramId());
        unitManager.update();
        GL20.glUseProgram(0);

        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.TEXTURED_STATIONARY).getProgramId());
        menuManager.update();
        GL20.glUseProgram(0);

    }

    @Override
    public void stop() {
        GL20.glUseProgram(0);
        shaderEngine.done(ShaderProgramType.UNTEXTURED_MOVABLE);
    }

    /**
     * Sets the camera object
     *
     * @param camera
     */
    public void setCamera(Camera camera) {this.camera = camera;}

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
    public void setShaderEngine(BasicShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }


}
