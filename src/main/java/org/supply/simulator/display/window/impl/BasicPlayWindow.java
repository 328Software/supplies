package org.supply.simulator.display.window.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkManager;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfoAbstract;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.Window;

import java.nio.FloatBuffer;

/**
 * Created by Alex on 6/27/2014.
 */
public class BasicPlayWindow extends HasRenderableInfoAbstract implements Window {
    private static final double PI = 3.14159265358979323846;

    private ShaderEngine shaderEngine;

    private BasicChunkManager<Integer,BasicChunk>  chunkManager;


    // Moving variables
    private Matrix4f projectionMatrix = null;
    private Matrix4f viewMatrix = null ;
    private Matrix4f modelMatrix = null;
    private FloatBuffer matrix44Buffer = null;

    private boolean isBuilt;
    private boolean isDestroyed;

    public BasicPlayWindow() {
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
        Camera camera = getCameraFromStream();

        //***********SET VIEW (CAMERA POSITON)***********
        // Reset view
        viewMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();

        // Translate and rotate camera
        Matrix4f.translate(camera.getCameraPos(), viewMatrix, viewMatrix);

        Matrix4f.rotate(camera.getCameraAngle().z, new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        Matrix4f.rotate(camera.getCameraAngle().y, new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate(camera.getCameraAngle().x, new Vector3f(1, 0, 0), viewMatrix, viewMatrix);

        // Translate, rotate and scale model
        Matrix4f.scale(camera.getModelScale(), modelMatrix, modelMatrix);
        Matrix4f.translate(camera.getCameraPos(), modelMatrix, modelMatrix);

        Matrix4f.rotate(camera.getModelAngle().z* (float)(PI / 180d), new Vector3f(0, 0, 1),
                modelMatrix, modelMatrix);
        Matrix4f.rotate(camera.getModelAngle().y* (float)(PI / 180d), new Vector3f(0, 1, 0),
                modelMatrix, modelMatrix);
        Matrix4f.rotate(camera.getModelAngle().x* (float)(PI / 180d), new Vector3f(1, 0, 0),
                modelMatrix, modelMatrix);


        // Set shader program type to VIEW
        shaderEngine.useProgram(ShaderProgramType.PLAY);

        // Store new view information in OpenGL buffers
        projectionMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY), false, matrix44Buffer);
        viewMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY), false, matrix44Buffer);
        modelMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY), false, matrix44Buffer);

        // Clear shader program type
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

        // Clear bit
        //TODO What does this do?
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        //***********RENDER CHUNKS***********
        // Set shader program type to CHUNK
        shaderEngine.useProgram(ShaderProgramType.PLAY);

        // Update chunks with new camera position
        chunkManager.update(camera);

//        while (chunkManager.hasNext()) {
//            chunkManager.next().render();
//        }


        shaderEngine.useProgram(ShaderProgramType.CLEAR);

        //***********RENDER ENTITIES***********
        shaderEngine.useProgram(ShaderProgramType.PLAY);
        //TODO RENDER ENTITIES??
        shaderEngine.useProgram(ShaderProgramType.CLEAR);

    }

    @Override
    public void build() {
        setupMatrices();
        shaderEngine.createProgram(ShaderProgramType.PLAY);
        isBuilt = true;
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    public void destroy() {
        shaderEngine.useProgram(ShaderProgramType.CLEAR);
        shaderEngine.deleteProgram(ShaderProgramType.PLAY);
        isDestroyed=true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    private Camera getCameraFromStream() {
        //TODO camera data stream
        return null;
    }

    private void setupMatrices() {
        // Setup projection matrix
        projectionMatrix = new Matrix4f();
        float fieldOfView = 60f;
        float aspectRatio = (float)columns / (float)rows;
        float near_plane = 0.1f;
        float far_plane = 100f;

        float y_scale = (float)(1f / Math.tan((fieldOfView / 2f)* (float)(PI / 180d)));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
        projectionMatrix.m33 = 0;

        // Setup view matrix
        viewMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();

        // Create a FloatBuffer with the proper size to store our matrices later
        matrix44Buffer = BufferUtils.createFloatBuffer(16);

    }
}
