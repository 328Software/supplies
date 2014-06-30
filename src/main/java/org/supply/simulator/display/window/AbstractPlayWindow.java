package org.supply.simulator.display.window;

import org.lwjgl.opengl.GL11;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkManager;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfoAbstract;

import java.util.Iterator;

/**
 * Created by Alex on 6/29/2014.
 */
public abstract class AbstractPlayWindow<S extends ShaderEngine> extends HasRenderableInfoAbstract implements Window<S> {

    protected ShaderEngine shaderEngine;

    protected ChunkManager<Integer,BasicChunk> chunkManager;

    private boolean isBuilt;
    private boolean isDestroyed;

    public AbstractPlayWindow() {
        isBuilt = false;
        isDestroyed = false;
    }

    @Override
    public void setShaderEngine(ShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }

    @Override
    public void render() {

        // Get new camera position
        Camera cameraData = getCameraFromStream();
        cameraData.build();


        // Set shader program type to VIEW
        shaderEngine.useProgram(ShaderProgramType.PLAY);

        cameraData.render();

        // Clear shader program type
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

        // Clear bit
        //TODO What does this do?
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        // Set shader program type to CHUNK
        shaderEngine.useProgram(ShaderProgramType.PLAY);

        // Update chunks with new camera position
        chunkManager.update(cameraData);
        Iterator<BasicChunk> it = chunkManager.iterator();
        while (it.hasNext())
        {
            it.next().render();
        }
//        for (BasicChunk chunk: chunkManager.toArray(new BasicChunk[chunkManager.size()])) {
//            chunk.render();
//        }
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

        //***********RENDER ENTITIES***********
        shaderEngine.useProgram(ShaderProgramType.PLAY);
        //TODO RENDER ENTITIES
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

    }

    @Override
    public void build() {
        shaderEngine.createProgram(ShaderProgramType.PLAY);
        isBuilt = true;
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

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

    protected abstract Camera getCameraFromStream();
}
