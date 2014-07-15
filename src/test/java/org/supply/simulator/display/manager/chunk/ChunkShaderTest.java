package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.assetengine.indices.ChunkType;
import org.supply.simulator.display.assetengine.indices.MockChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexHandle;
import org.supply.simulator.display.assetengine.shader.MockShaderEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderHandle;
import org.supply.simulator.display.core.MockDisplayCore;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;


import java.nio.FloatBuffer;

/**
 * Created by Alex on 7/3/2014.
 */
public class ChunkShaderTest {

    private final ChunkType chunkType = ChunkType.MEDIUM_T;

//    MockCamera camera;
MockShaderEngine<ShaderProgramType,BasicShaderHandle> shaderEngine;
    BasicChunk chunk;
    NewCamera camera;

//    private Vector3f modelPos;
//    private Vector3f modelAngle;
//    private Vector3f modelScale = null;
//    private Vector3f cameraPos = null;
//    private Vector3f cameraAngle = null;
//    private Matrix4f projectionMatrix;
//    private Matrix4f viewMatrix;
//    private Matrix4f modelMatrix;
//    private FloatBuffer matrix44Buffer;
    @Before
    public void create() {
        MockDisplayCore.build("ChunkShaderTest");

        shaderEngine = new MockShaderEngine();
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/vertex.glsl");
        shaderEngine.set(ShaderProgramType.PLAY,"shaders/fragments.glsl");


        camera = new NewCamera();
        camera.setProjectionMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getProjectionMatrixLocation());
        camera.setModelMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getModelMatrixLocation());
        camera.setViewMatrixLocation(shaderEngine.get(ShaderProgramType.PLAY).getViewMatrixLocation());
        camera.build();

        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getChunkData(chunkType.rows(), chunkType.columns(), 0, 0));
        chunk.setAttributeLocations(new int[] {0,1,2});
        MockChunkIndexEngine<ChunkType,BasicChunkIndexHandle> chunkIndexEngine= new MockChunkIndexEngine();
        chunkIndexEngine.set(chunkType, null);
        chunk.setChunkIndexEngine(chunkIndexEngine);
        chunk.build();
       // OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {

            camera.update();


            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());

            camera.render();


            GL20.glUseProgram(0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());
            chunk.render();

            GL20.glUseProgram(0);


            MockDisplayCore.render();
        }

    }

    @After
    public void destroy() {
        //
        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.PLAY).getProgramId());


        chunk.destroy();
        MockDisplayCore.destroy();

    }

    private class NewCamera {

        private static final double PI = 3.14159265358979323846;
        private Vector3f modelPos;
        private Vector3f modelAngle;
        private Vector3f modelScale = null;
        private Vector3f cameraPos = null;
        private Vector3f cameraAngle = null;
        private Matrix4f projectionMatrix;
        private Matrix4f viewMatrix;
        private Matrix4f modelMatrix;
        private FloatBuffer matrix44Buffer;

        private int projectionMatrixLocation;
        private int viewMatrixLocation;
        private int modelMatrixLocation;

        private int columns;
        private int rows;


        public void build() {

            projectionMatrix = new Matrix4f();
            float fieldOfView = 60f;

            float aspectRatio = (float)600 / (float)800;
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

            matrix44Buffer = BufferUtils.createFloatBuffer(16);
            modelPos = new Vector3f(0, 0, 0);
            modelAngle = new Vector3f(0, 0, 0);
            modelScale = new Vector3f(1, 1, 1);
            cameraPos = new Vector3f(0, 0, -1);
            cameraAngle = new Vector3f(0, 0, 0);

        }

        public void update() {
            viewMatrix = new Matrix4f();
            modelMatrix = new Matrix4f();

            // Translate and rotate camera
            Matrix4f.translate(cameraPos, viewMatrix, viewMatrix);

            Matrix4f.rotate(cameraAngle.z, new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
            Matrix4f.rotate(cameraAngle.y, new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
            Matrix4f.rotate(cameraAngle.x, new Vector3f(1, 0, 0), viewMatrix, viewMatrix);

            // Translate, rotate and scale model
            Matrix4f.scale(modelScale, modelMatrix, modelMatrix);
            Matrix4f.translate(modelPos, modelMatrix, modelMatrix);

            Matrix4f.rotate(modelAngle.z* (float)(PI / 180d), new Vector3f(0, 0, 1),
                    modelMatrix, modelMatrix);
            Matrix4f.rotate(modelAngle.y* (float)(PI / 180d), new Vector3f(0, 1, 0),
                    modelMatrix, modelMatrix);
            Matrix4f.rotate(modelAngle.x* (float)(PI / 180d), new Vector3f(1, 0, 0),
                    modelMatrix, modelMatrix);

        }

        public void render() {

            projectionMatrix.store(matrix44Buffer); matrix44Buffer.flip();
            GL20.glUniformMatrix4(this.projectionMatrixLocation, false, matrix44Buffer);
            //   GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
            viewMatrix.store(matrix44Buffer); matrix44Buffer.flip();
            GL20.glUniformMatrix4(this.viewMatrixLocation, false, matrix44Buffer);
            //  GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
            modelMatrix.store(matrix44Buffer); matrix44Buffer.flip();
            GL20.glUniformMatrix4(this.modelMatrixLocation, false, matrix44Buffer);
        }


        public void setColumns(int columns) {
            this.columns = columns;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public void setModelMatrixLocation(int modelMatrixLocation) {
            this.modelMatrixLocation = modelMatrixLocation;
        }

        public void setProjectionMatrixLocation(int projectionMatrixLocation) {
            this.projectionMatrixLocation = projectionMatrixLocation;
        }

        public void setViewMatrixLocation(int viewMatrixLocation) {
            this.viewMatrixLocation = viewMatrixLocation;
        }
    }



}
