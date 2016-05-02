package org.supply.simulator.display.assetengine;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.attribute.entity.impl.BasicUnitType;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.extra.*;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.logging.HasLogger;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicTextureEngineTest extends HasLogger {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private BasicTextureEngine textureEngine;
    UnitType entityType;

    private MockDisplayCore core;
    private BasicShaderEngine shaderEngine;

    //Texture variables
    private TextureHandle textureHandle;

    private static final int textureWidth = 500;
    private static final int textureHeight = 500;

    private int VBO;
    private int VAO;
    private int VBOI;
    int indicesCount;
    UnitType unitType;

    TextureHandle handle;

    @Before
    public void createFixture() {
        core = new MockDisplayCore();
        core.build("BasicTextureEngineTest");

        shaderEngine = new BasicShaderEngine();
        textureEngine = new BasicTextureEngine();

        unitType = new BasicUnitType();
        unitType.setName("textures/rect.png");
        handle = textureEngine.get(unitType);



        setupQuad();



    }

    private void setupQuad() {
        TexturedVertex v0 = new TexturedVertex();
        v0.setXYZ(-0.5f, 0.5f, 0); v0.setRGB(1, 0, 0); v0.setST(0, 0);
        TexturedVertex v1 = new TexturedVertex();
        v1.setXYZ(-0.5f, -0.5f, 0); v1.setRGB(0, 1, 0); v1.setST(0, 1);
        TexturedVertex v2 = new TexturedVertex();
        v2.setXYZ(0.5f, -0.5f, 0); v2.setRGB(0, 0, 1); v2.setST(1, 1);
        TexturedVertex v3 = new TexturedVertex();
        v3.setXYZ(0.5f, 0.5f, 0); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        TexturedVertex[] vertices = new TexturedVertex[] {v0, v1, v2, v3};
        // Put each 'Vertex' in one FloatBuffer
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length *
                TexturedVertex.elementCount);
        for (int i = 0; i < vertices.length; i++) {
            // Add position, color and texture floats to the buffer
            verticesBuffer.put(vertices[i].getElements());
        }
        verticesBuffer.flip();
        // OpenGL expects to draw vertices in counter clockwise order by default
        byte[] indices = {
                0, 1, 2,
                2, 3, 0
        };
        indicesCount = indices.length;
        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
        indicesBuffer.put(indices);
        indicesBuffer.flip();

        // Create a new Vertex Array Object in memory and select it (bind)
        VAO = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(VAO);

        // Create a new Vertex Buffer Object in memory and select it (bind)
        VBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);

        // Put the position coordinates in attribute list 0BYTE
        GL20.glVertexAttribPointer(0, TexturedVertex.positionElementCount, GL11.GL_FLOAT,
                false, TexturedVertex.stride, TexturedVertex.positionByteOffset);
        // Put the color components in attribute list 1
        GL20.glVertexAttribPointer(1, TexturedVertex.colorElementCount, GL11.GL_FLOAT,
                false, TexturedVertex.stride, TexturedVertex.colorByteOffset);
        // Put the texture coordinates in attribute list 2
        GL20.glVertexAttribPointer(2, TexturedVertex.textureElementCount, GL11.GL_FLOAT,
                false, TexturedVertex.stride, TexturedVertex.textureByteOffset);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        // Deselect (bind to 0) the VAO
        GL30.glBindVertexArray(0);

        // Create a new VBO for the indices and select it (bind) - INDICES
        VBOI = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VBOI);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

    }

    @Test
    public void TestPlayTexture() {






        while (!Display.isCloseRequested()) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,0);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL20.glUseProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());

            // Bind the texture
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle.getTextureId());

            // Bind to the VAO that has all the information about the vertices
            GL30.glBindVertexArray(VAO);
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);

            // Bind to the index VBO that has all the information about the order of the vertices
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VBOI);

            // Draw the vertices
            GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);

            // Put everything back to default (deselect)
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);

            GL20.glUseProgram(0);


            core.render();
        }

        GL20.glUseProgram(0);
        GL20.glDeleteProgram(shaderEngine.get(ShaderProgramType.MENU).getProgramId());
        textureEngine.done(unitType);
        core.destroy();

    }


}
