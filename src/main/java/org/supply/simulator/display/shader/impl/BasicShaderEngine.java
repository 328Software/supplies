package org.supply.simulator.display.shader.impl;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngine implements ShaderEngine {
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

    }

    @Override
    public void setPlayVertexShader(String playVertexShader) {
        this.vertexShader[ShaderProgramType.PLAY.value()] = playVertexShader;

    }

    @Override
    public void setPlayTessellationShader(String playTessellationShader) {
        this.tessellationShader[ShaderProgramType.PLAY.value()] = playTessellationShader;
    }

    @Override
    public void setPlayGeometryShader(String playGeometryShader) {
        this.geometryShader[ShaderProgramType.PLAY.value()] = playGeometryShader;
    }

    @Override
    public void setPlayFragmentShader(String playFragmentShader) {
        this.fragmentShader[ShaderProgramType.PLAY.value()] = playFragmentShader;
    }

    @Override
    public void setPlayComputeShader(String playComputeShader) {
        this.computeShader[ShaderProgramType.PLAY.value()] = playComputeShader;
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

    @Override
    public void useProgram(ShaderProgramType type) {

    }

    @Override
    public void createProgram(ShaderProgramType type) {
        programIds[type.value()] = GL20.glCreateProgram();

        if (vertexShader[type.value()]!=null) {
            GL20.glAttachShader(programIds[type.value()],
                    loadShader(vertexShader[type.value()], GL20.GL_VERTEX_SHADER));

            //Position information will be attribute 0
            GL20.glBindAttribLocation(programIds[type.value()], 0, "in_Position");
            // Color information will be attribute 1
            GL20.glBindAttribLocation(programIds[type.value()], 1, "in_Color");
        } else {
            System.out.println("FIAL");
        }

        if (fragmentShader[type.value()]!=null) {
            GL20.glAttachShader(programIds[type.value()],
                    loadShader(fragmentShader[type.value()], GL20.GL_FRAGMENT_SHADER));
        } else {
            System.out.println("FIAL");
        }

        if (geometryShader[type.value()]!=null) {

        }

        if (tessellationShader[type.value()]!=null) {

        }

        if (computeShader[type.value()]!=null) {

        }

        projectionMatrixLocation[type.value()] = GL20.glGetUniformLocation(programIds[type.value()],"projectionMatrix");
        viewMatrixLocation[type.value()]       = GL20.glGetUniformLocation(programIds[type.value()], "viewMatrix");
        modelMatrixLocation[type.value()]      = GL20.glGetUniformLocation(programIds[type.value()], "modelMatrix");

        GL20.glLinkProgram(programIds[type.value()]);
        GL20.glValidateProgram(programIds[type.value()]);
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
            System.out.println("ERROR READING SHADER FILE " + fileName);
            e.printStackTrace();
        }


        shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println("Could not compile shader.");
        }

        return shaderID;
    }
}
