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
import org.supply.simulator.display.OpenGLDebugger;
import org.supply.simulator.display.core.DisplayCoreTest;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.ShaderType;
import org.supply.simulator.display.shader.impl.BasicShaderEngine;


import java.nio.FloatBuffer;

/**
 * Created by Alex on 7/3/2014.
 */
public class ChunkShaderTest {



//    MockCamera camera;
    BasicShaderEngine shaderEngine;
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

    private int columns;
    private int rows;


    @Before
    public void create() {
        columns=100;
        rows=100;
        DisplayCoreTest.build("ChunkShaderTest");

        shaderEngine = new BasicShaderEngine();
        shaderEngine.setPlayShaderFile("shaders/vertex.glsl", ShaderType.VERTEX);
        shaderEngine.setPlayShaderFile("shaders/fragments.glsl", ShaderType.FRAGMENT);

        shaderEngine.createProgram(ShaderProgramType.PLAY);
       // setupShaders();

//        camera = new MockCamera();
//        camera.setProjectionMatrixLocation(projectionMatrixLocation);
//        camera.setModelMatrixLocation(viewMatrixLocation);
//        camera.setViewMatrixLocation(modelMatrixLocation);
                //        projectionMatrix = new Matrix4f();
                //        float fieldOfView = 60f;
                //
                //        float aspectRatio = (float)columns / (float)rows;
                //        float near_plane = 0.1f;
                //        float far_plane = 100f;
                //
                //        float y_scale = (float)(1f / Math.tan((fieldOfView / 2f)* (float)(PI / 180d)));
                //        float x_scale = y_scale / aspectRatio;
                //        float frustum_length = far_plane - near_plane;
                //
                //        projectionMatrix.m00 = x_scale;
                //        projectionMatrix.m11 = y_scale;
                //        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
                //        projectionMatrix.m23 = -1;
                //        projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
                //        projectionMatrix.m33 = 0;
                //
                //        matrix44Buffer = BufferUtils.createFloatBuffer(16);
                //        modelPos = new Vector3f(0, 0, 0);
                //        modelAngle = new Vector3f(0, 0, 0);
                //        modelScale = new Vector3f(1, 1, 1);
                //        cameraPos = new Vector3f(0, 0, -1);
                //        cameraAngle = new Vector3f(0, 0, 0);

        camera = new NewCamera();
        camera.setRows(rows);
        camera.setColumns(columns);
        camera.setProjectionMatrixLocation(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY));
        camera.setViewMatrixLocation(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY));
        camera.setModelMatrixLocation(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY));
        camera.build();

        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getData(rows,columns));
        chunk.setAttributeLocations(new int[] {0,1});
        chunk.build();
        OpenGLDebugger.printChunkBuffers(chunk);

    }

    @Test
    public void render() {
        while (!Display.isCloseRequested()) {

            camera.update();


            shaderEngine.useProgram(ShaderProgramType.PLAY);

            camera.render();


            shaderEngine.useProgram(ShaderProgramType.CLEAR);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            shaderEngine.useProgram(ShaderProgramType.PLAY);
            chunk.render();

            shaderEngine.useProgram(ShaderProgramType.CLEAR);


            DisplayCoreTest.render();
        }

    }

    @After
    public void destroy() {
        //
        shaderEngine.useProgram(ShaderProgramType.CLEAR);
        shaderEngine.deleteProgram(ShaderProgramType.PLAY);


        chunk.destroy();
        DisplayCoreTest.destroy();

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

            float aspectRatio = (float)this.columns / (float)this.rows;
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

//            projectionMatrix.store(matrix44Buffer); matrix44Buffer.flip();
//            GL20.glUniformMatrix4(shaderEngine.getProjectionMatrixLocation(ShaderProgramType.PLAY), false, matrix44Buffer);
//            //   GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
//            viewMatrix.store(matrix44Buffer); matrix44Buffer.flip();
//            GL20.glUniformMatrix4(shaderEngine.getViewMatrixLocation(ShaderProgramType.PLAY), false, matrix44Buffer);
//            //  GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
//            modelMatrix.store(matrix44Buffer); matrix44Buffer.flip();
//            GL20.glUniformMatrix4(shaderEngine.getModelMatrixLocation(ShaderProgramType.PLAY), false, matrix44Buffer);

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
