package org.supply.simulator.display.assetengine.shader.impl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.shader.ShaderData;
import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.ShaderType;
import org.supply.simulator.logging.HasLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Basic implementation of the ShaderEngine interface.
 *
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngine<K extends ShaderProgramType,V extends ShaderData> extends HasLogger implements ShaderEngine<ShaderProgramType,ShaderData> {


    private HashMap<ShaderProgramType,ShaderData> shaderMap;

    private String[] vertexShader;
    private String[] tessellationShader;
    private String[] geometryShader;
    private String[] fragmentShader;
    private String[] computeShader;

    public BasicShaderEngine () {
//        programIds = new int[ShaderProgramType.COUNT];
//        projectionMatrixLocation= new int[ShaderProgramType.COUNT];
//        viewMatrixLocation= new int[ShaderProgramType.COUNT];
//        modelMatrixLocation= new int[ShaderProgramType.COUNT];
//        programIds[ShaderProgramType.CLEAR.value()] = 0;

        shaderMap = new HashMap<>(ShaderProgramType.COUNT);
        vertexShader = new String [ShaderProgramType.COUNT];
        tessellationShader= new String [ShaderProgramType.COUNT];
        geometryShader= new String [ShaderProgramType.COUNT];
        fragmentShader= new String [ShaderProgramType.COUNT];
        computeShader= new String [ShaderProgramType.COUNT];

    }

    @Override
    public void setShaderFile(String fileName, ShaderType sType, ShaderProgramType pType) {
        switch(sType) {
            case VERTEX: this.vertexShader[pType.value()] = fileName;
                break;
            case FRAGMENT: this.fragmentShader[pType.value()] = fileName;
                break;
        }
    }

    @Override
    public ShaderData get(ShaderProgramType key) {
        if (!shaderMap.containsKey(key)) {
            createProgram(key);
        }
        return shaderMap.get(key);
    }



//    @Override
//    public void setPlayShaderFile(String fileName, ShaderType type) {
//        switch(type) {
//            case VERTEX: this.vertexShader[ShaderProgramType.PLAY.value()] = fileName;
//                break;
//            case FRAGMENT: this.fragmentShader[ShaderProgramType.PLAY.value()] = fileName;
//                break;
//        }
//    }
//
//    @Override
//    public void useProgram(ShaderProgramType type) {
//        GL20.glUseProgram(programIds[type.value()]);
//    }
//
//    @Override
//public void createProgram(ShaderProgramType type) {

    private void createProgram(ShaderProgramType type) {

        int vertexId = -1;
        int fragmentId = -1;
        int programId = -1;

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

        programId = GL20.glCreateProgram();


        GL20.glAttachShader(programId,vertexId);
        GL20.glAttachShader(programId,fragmentId);

        //Position information will be attribute 0
        GL20.glBindAttribLocation(programId, 0, "in_Position");
        // Color information will be attribute 1
        GL20.glBindAttribLocation(programId, 1, "in_Color");

        //TODO ADDED FOR TEXTURE
        GL20.glBindAttribLocation(programId, 2, "in_TextureCoord");

        if (geometryShader[type.value()]!=null) {

        }

        if (tessellationShader[type.value()]!=null) {

        }

        if (computeShader[type.value()]!=null) {

        }


        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);

        BasicShaderData data = new BasicShaderData();
        data.setProjectionMatrixLocation(GL20.glGetUniformLocation(programId,"projectionMatrix"));
        data.setViewMatrixLocation(GL20.glGetUniformLocation(programId, "viewMatrix"));
        data.setModelMatrixLocation(GL20.glGetUniformLocation(programId, "modelMatrix"));
        data.setProgramId(programId);

        shaderMap.put(type,data);
//        projectionMatrixLocation = GL20.glGetUniformLocation(programId,"projectionMatrix");
//        viewMatrixLocation[type.value()]       = GL20.glGetUniformLocation(programId, "viewMatrix");
//        modelMatrixLocation[type.value()]      = GL20.glGetUniformLocation(programId, "modelMatrix");

    }
//
//    @Override
//    public void deleteProgram(ShaderProgramType type) {
//        GL20.glDeleteProgram(programIds[type.value()]);
//    }
//
//
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



}
