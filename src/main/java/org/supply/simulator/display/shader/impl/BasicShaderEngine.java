package org.supply.simulator.display.shader.impl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.shader.ShaderType;
import org.supply.simulator.logging.HasLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Basic implementation of the ShaderEngine interface.
 *
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngine extends HasLogger implements ShaderEngine {
    private int [] programIds;

    private int[] projectionMatrixLocation;
    private int[] viewMatrixLocation;
    private int[] modelMatrixLocation;

    private String[] vertexShader;
    private String[] tessellationShader;
    private String[] geometryShader;
    private String[] fragmentShader;
    private String[] computeShader;

    public BasicShaderEngine () {
        programIds = new int[ShaderProgramType.COUNT];
        projectionMatrixLocation= new int[ShaderProgramType.COUNT];
        viewMatrixLocation= new int[ShaderProgramType.COUNT];
        modelMatrixLocation= new int[ShaderProgramType.COUNT];
        programIds[ShaderProgramType.CLEAR.value()] = 0;

        vertexShader = new String [ShaderProgramType.COUNT];
        tessellationShader= new String [ShaderProgramType.COUNT];
        geometryShader= new String [ShaderProgramType.COUNT];
        fragmentShader= new String [ShaderProgramType.COUNT];
        computeShader= new String [ShaderProgramType.COUNT];

    }

    @Override
    public void setPlayShaderFile(String playVertexShader, ShaderType type) {
        switch(type) {
            case VERTEX: this.vertexShader[ShaderProgramType.PLAY.value()] = playVertexShader;
                break;
            case FRAGMENT: this.fragmentShader[ShaderProgramType.PLAY.value()] = playVertexShader;
                break;
        }
    }

    @Override
    public void useProgram(ShaderProgramType type) {
        GL20.glUseProgram(programIds[type.value()]);
    }

    @Override
    public void createProgram(ShaderProgramType type) {

        int vertexId = -1;
        int fragmentId = -1;

        if (vertexShader[type.value()]!=null) {
            vertexId = loadShader(vertexShader[type.value()], GL20.GL_VERTEX_SHADER);
        } else {
            logger.error("Failed to load vertex shader");
            System.exit(-1);
        }

        if (fragmentShader[type.value()]!=null) {
            fragmentId=loadShader(fragmentShader[type.value()], GL20.GL_FRAGMENT_SHADER);
        } else {
            logger.error("Failed to load fragment shader");
            System.exit(-1);
        }

        programIds[type.value()] = GL20.glCreateProgram();


        GL20.glAttachShader(programIds[type.value()],vertexId);
        GL20.glAttachShader(programIds[type.value()],fragmentId);

        //Position information will be attribute 0
        GL20.glBindAttribLocation(programIds[type.value()], 0, "in_Position");
        // Color information will be attribute 1
        GL20.glBindAttribLocation(programIds[type.value()], 1, "in_Color");

        //TODO ADDED FOR TEXTURE
        GL20.glBindAttribLocation(programIds[type.value()], 2, "in_TextureCoord");

        if (geometryShader[type.value()]!=null) {

        }

        if (tessellationShader[type.value()]!=null) {

        }

        if (computeShader[type.value()]!=null) {

        }


        GL20.glLinkProgram(programIds[type.value()]);
        GL20.glValidateProgram(programIds[type.value()]);

        projectionMatrixLocation[type.value()] = GL20.glGetUniformLocation(programIds[type.value()],"projectionMatrix");
        viewMatrixLocation[type.value()]       = GL20.glGetUniformLocation(programIds[type.value()], "viewMatrix");
        modelMatrixLocation[type.value()]      = GL20.glGetUniformLocation(programIds[type.value()], "modelMatrix");

    }

    @Override
    public void deleteProgram(ShaderProgramType type) {
        GL20.glDeleteProgram(programIds[type.value()]);
    }


    private int loadShader(String fileName, int type) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = 0;


        int lineNum = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)));
        String line;


        try {
            while ((line = reader.readLine()) != null) {
                lineNum++;
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            logger.error("ERROR READING SHADER FILE " + fileName);
            e.printStackTrace();
        }


        shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            logger.error("Could not compile shader.");
        }

        return shaderID;
    }

    @Override
    public int getProjectionMatrixLocation(ShaderProgramType type) {
        return projectionMatrixLocation[type.value()];
    }

    @Override
    public int getViewMatrixLocation(ShaderProgramType type) {
        return viewMatrixLocation[type.value()];
    }

    @Override
    public int getModelMatrixLocation(ShaderProgramType type) {
        return modelMatrixLocation[type.value()];
    }
}
