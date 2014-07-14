package org.supply.simulator.display.window;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderData;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.supplyrenderable.AbstractSupplyRenderable;

import java.util.Iterator;

/**
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractPlayWindow extends AbstractSupplyRenderable implements Window {

    protected ShaderEngine<ShaderProgramType,ShaderData> shaderEngine;

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
        //shaderEngine.createProgram(ShaderProgramType.PLAY);

        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
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
        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

        camera.render();

        // Clear shader program type
        GL20.glUseProgram(0);

        // Clear bit
        //TODO What does this do?
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        // Set shader program type to CHUNK
        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

        // Update chunkCollection with new camera position
        chunkManager.update(camera);
        Iterator<BasicChunk> it = chunkManager.iterator();
        while (it.hasNext())
        {
            it.next().render();
        }

        GL20.glUseProgram(0);

        //***********RENDER ENTITIES***********
        GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
        //TODO RENDER ENTITIES
        GL20.glUseProgram(0);

    }

    @Override
    public void destroy() {
        chunkManager.clear();
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
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
