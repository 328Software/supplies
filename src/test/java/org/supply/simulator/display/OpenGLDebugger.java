package org.supply.simulator.display;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;


import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;


/**
 * Created by Alex on 7/2/2014.
 */
public class OpenGLDebugger  {

    protected static Logger logger = LogManager.getLogger(OpenGLDebugger.class);

    public static void printChunkBuffers(Chunk chunk) {

        GL30.glBindVertexArray(chunk.getVertexAttributesId());


        printColorBuffer(chunk.getColorsArrayId(),chunk.getRows(),chunk.getColumns());
        printPositionsBuffer(chunk.getPositionsArrayId(),chunk.getRows(),chunk.getColumns());
        printIndicesBuffer(chunk.getIndicesBufferId(),chunk.getRows(),chunk.getColumns());





        GL30.glBindVertexArray(0);


    }

    public static void printOpenGLError() {
        switch(GL11.glGetError()) {
            case GL11.GL_NO_ERROR: logger.info("OpenGl ERROR: GL_NO_ERROR");
                break;
            case GL11.GL_INVALID_ENUM: logger.error("OpenGl ERROR: GL_INVALID_ENUM");
                break;
            case GL11.GL_INVALID_VALUE: logger.error("OpenGl ERROR: GL_INVALID_VALUE");
                break;
            case GL11.GL_INVALID_OPERATION: logger.error("OpenGl ERROR: GL_INVALID_OPERATION");
                break;
            case GL11.GL_OUT_OF_MEMORY: logger.error("OpenGl ERROR: GL_OUT_OF_MEMORY");
                break;
            case GL11.GL_STACK_UNDERFLOW: logger.error("OpenGl ERROR: GL_STACK_UNDERFLOW");
                break;
            case GL11.GL_STACK_OVERFLOW: logger.error("OpenGl ERROR: GL_STACK_OVERFLOW");
                break;
        }
    }
    public static void getFragmentShaderPositionValue(int rows, int cols) {
        ByteBuffer bytes  = BufferUtils.createByteBuffer(4 * rows * cols *
                BasicChunkData.COLOR_BYTES);
    }

    public static void printColorBuffer(int bufferId,int rows, int cols) {
        ByteBuffer bytes  = BufferUtils.createByteBuffer(4 * rows * cols *
                BasicChunkData.COLOR_BYTES);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferId);

        bytes = GL15.glMapBuffer(GL15.GL_ARRAY_BUFFER,GL15.GL_READ_ONLY,
                4 * rows * cols * BasicChunkData.COLOR_BYTES, bytes);
        printByteBuffer(rows, cols, bytes, 4);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public static void printPositionsBuffer(int bufferId,int rows, int cols) {
        ByteBuffer bytes  = BufferUtils.createByteBuffer(4 * rows * cols *
                BasicChunkData.COLOR_BYTES);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferId);
        bytes = GL15.glMapBuffer(GL15.GL_ARRAY_BUFFER,GL15.GL_READ_ONLY,
                4 * rows * cols * BasicChunkData.POSITION_BYTES, bytes);

        printFloatBuffer(rows, cols, bytes, 1);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

    }

    public static void printIndicesBuffer(int bufferId,int rows, int cols) {
        ByteBuffer bytes  = BufferUtils.createByteBuffer(4 * rows * cols *
                BasicChunkData.COLOR_BYTES);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, bufferId);
        bytes = GL15.glMapBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,GL15.GL_READ_ONLY,
                6*4 * rows * cols * 4, bytes);
        printIntBuffer(rows, cols, bytes, 6);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

    }


    private static void printFloatBuffer(int rows, int columns, ByteBuffer buf, int stride) {
        for (int i=0; i<rows;i++) {
            for (int j=0; j<columns;j++) {
                logger.trace("{ ");
                for (int k = 0; k<stride;k++) {
                    logger.trace(buf.getFloat()+" ");
                }
                logger.trace("}");
            }
            logger.trace("\r\n");
        }
    }

    private static void printByteBuffer(int rows, int columns, ByteBuffer buf, int stride) {
        for (int i=0; i<rows;i++) {
            for (int j=0; j<columns;j++) {
                logger.trace("{ ");
                for (int k = 0; k<stride;k++) {
                    logger.trace(buf.get() + " ");
                }
                logger.trace("}");
            }
            logger.trace("\r\n");
        }
    }

    private static void printIntBuffer(int rows, int columns, ByteBuffer buf, int stride) {
        for (int i=0; i<rows;i++) {
            for (int j=0; j<columns;j++) {
                logger.trace("{ ");
                for (int k = 0; k<stride;k++) {
                    logger.trace(buf.getInt() + " ");
                }
                logger.trace("}");
            }
            logger.trace("\r\n");
        }
    }
}
