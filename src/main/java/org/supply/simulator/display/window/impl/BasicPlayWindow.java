package org.supply.simulator.display.window.impl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.chunk.Chunk;
import org.supply.simulator.display.chunk.ChunkManager;
import org.supply.simulator.display.chunk.impl.BasicChunk;
import org.supply.simulator.display.chunk.impl.BasicChunkManager;
import org.supply.simulator.display.renderableinfo.HasRenderableInfoAbstract;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.TextureEngine;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.Window;

import java.nio.FloatBuffer;
import java.util.Iterator;

/**
 * Created by Alex on 6/27/2014.
 */
public class BasicPlayWindow extends HasRenderableInfoAbstract implements Window {
    private static final double PI = 3.14159265358979323846;

    private ShaderEngine shaderEngine;
    private TextureEngine textureEngine;

    private BasicChunkManager<Integer,BasicChunk>  chunkManager;


    // Moving variables
    private int projectionMatrixLocation = 0;
    private int viewMatrixLocation = 0;
    private int modelMatrixLocation = 0;
    private Matrix4f projectionMatrix = null;
    private Matrix4f viewMatrix = null;
    private Matrix4f modelMatrix = null;
    private FloatBuffer matrix44Buffer = null;

    @Override
    public void setShaderEngine(ShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }

    @Override
    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }

    @Override
    public void render() {
        Camera camera = getCameraFromStream();


        updateViewMatrices(camera);

        shaderEngine.useViewProgram();

        projectionMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
        viewMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
        modelMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(modelMatrixLocation, false, matrix44Buffer);

        shaderEngine.clearProgram();

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        shaderEngine.useChunkProgram();

        chunkManager.updateChunks(camera);

        while (chunkManager.hasNext()) {
            chunkManager.next().render();
        }


        shaderEngine.clearProgram();

    }

    private Camera getCameraFromStream() {
        //TODO camera data stream
        return null;
    }

    private void updateViewMatrices(Camera camera) {
        // Reset view
        viewMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();

        // Translate and rotate camera
        Matrix4f.translate(camera.getCameraPos(), viewMatrix, viewMatrix);

        Matrix4f.rotate(camera.getCameraAngle().z, new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        Matrix4f.rotate(camera.getCameraAngle().y, new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate(camera.getCameraAngle().x, new Vector3f(1, 0, 0), viewMatrix, viewMatrix);


        Matrix4f.scale(camera.getModelScale(), modelMatrix, modelMatrix);
        Matrix4f.translate(camera.getCameraPos(), modelMatrix, modelMatrix);

        Matrix4f.rotate(camera.getModelAngle().z* (float)(PI / 180d), new Vector3f(0, 0, 1),
                modelMatrix, modelMatrix);
        Matrix4f.rotate(camera.getModelAngle().y* (float)(PI / 180d), new Vector3f(0, 1, 0),
                modelMatrix, modelMatrix);
        Matrix4f.rotate(camera.getModelAngle().x* (float)(PI / 180d), new Vector3f(1, 0, 0),
                modelMatrix, modelMatrix);
    }
}
