package org.supply.simulator.display.window;

import org.lwjgl.opengl.GL11;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.supplyrenderable.AbstractSupplyRenderable;

import java.util.Iterator;

/**
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractPlayWindow extends AbstractSupplyRenderable implements Window {

    protected ShaderEngine shaderEngine;

    protected Manager<BasicChunk> chunkManager;

    protected Camera camera;

    protected boolean isBuilt;
    protected boolean isDestroyed;

    public AbstractPlayWindow() {
        isBuilt = false;
        isDestroyed = false;
    }

    @Override
    public void build() {
        shaderEngine.createProgram(ShaderProgramType.PLAY);

        camera.setProjectionMatrixLocation(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY));
        camera.setModelMatrixLocation(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY));
        camera.setViewMatrixLocation(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY));
        camera.build();

        isBuilt = true;
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    @Override
    public void render() {
        // Set shader program type to VIEW
        shaderEngine.useProgram(ShaderProgramType.PLAY);

        camera.render();

        // Clear shader program type
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

        // Clear bit
        //TODO What does this do?
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        // Set shader program type to CHUNK
        shaderEngine.useProgram(ShaderProgramType.PLAY);

        // Update chunkCollection with new camera position
        chunkManager.update(camera);
        Iterator<BasicChunk> it = chunkManager.iterator();
        while (it.hasNext())
        {
            it.next().render();
        }

        shaderEngine.useProgram(ShaderProgramType.CLEAR);

        //***********RENDER ENTITIES***********
        shaderEngine.useProgram(ShaderProgramType.PLAY);
        //TODO RENDER ENTITIES
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

    }

    @Override
    public void destroy() {
        chunkManager.clear();
        shaderEngine.useProgram(ShaderProgramType.CLEAR);
        shaderEngine.deleteProgram(ShaderProgramType.PLAY);
        isDestroyed=true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Sets the camera object
     *
     * @param camera
     */
    public void setCamera(Camera camera) {this.camera = camera;}

    /**
     * Sets the manager object for chunkCollection
     *
     * @param manager
     */
    public void setChunkManager(Manager manager) {
        chunkManager = manager;
    }

    /**
     * Sets the manager object for entities
     *
     * @param manager
     */
    public void setEntityManager(Manager manager) {

    }

    @Override
    public void setShaderEngine(ShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }

}
