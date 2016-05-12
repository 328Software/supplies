package org.supply.simulator.display.assetengine.shader;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.shader.BasicShaderHandle;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Basic implementation of the ShaderEngine interface.
 *
 * Created by Alex on 6/28/2014.
 */
public class BasicShaderEngine
        extends AbstractAssetEngine<ShaderProgramType,BasicShaderHandle> {

    public BasicShaderEngine () {
        super();
    }

    @Override
    protected BasicShaderHandle createHandle(ShaderProgramType key) {
        int vertexId = -1;
        int fragmentId = -1;
        int programId = -1;


        vertexId = loadShader(key.vertex(), GL20.GL_VERTEX_SHADER);


        fragmentId=loadShader(key.fragment(), GL20.GL_FRAGMENT_SHADER);

        programId = GL20.glCreateProgram();


        GL20.glAttachShader(programId,vertexId);
        GL20.glAttachShader(programId,fragmentId);

        //Positions information will be attribute 0
        GL20.glBindAttribLocation(programId, 0, "in_Position");
        // Color information will be attribute 1
        GL20.glBindAttribLocation(programId, 1, "in_Color");

        //TODO ADDED FOR TEXTURE
        GL20.glBindAttribLocation(programId, 2, "in_TextureCoord");

        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);

        BasicShaderHandle data = new BasicShaderHandle();
        data.setProjectionMatrixLocation(GL20.glGetUniformLocation(programId,"projectionMatrix"));
        data.setViewMatrixLocation(GL20.glGetUniformLocation(programId, "viewMatrix"));
        data.setModelMatrixLocation(GL20.glGetUniformLocation(programId, "modelMatrix"));
        data.setProgramId(programId);


        return data;
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
    protected void destroyHandle(ShaderProgramType key) {
        BasicShaderHandle handle = handleMap.remove(key);
        GL20.glDeleteProgram(handle.getProgramId());

    }
}
