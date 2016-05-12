package org.supply.simulator.data.attribute.entity;

import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 5/1/2016.
 */
public abstract class AbstractEntityType extends HasLogger {

    private TextureHandle textureHandle;
    private Long id;


  /*  @Override
    public void setTextureHandle(TextureHandle textureHandle) {
        this.textureHandle=textureHandle;
    }

    @Override
    public TextureHandle getTextureHandle() {
        return this.textureHandle;
    }*/


//    @Override
    public Long getId() {
        return id;
    }

//    @Override
    public void setId(Long id) {
        this.id = id;
    }
/*
    public String printOpenGLError(String className) {
        String errorString = "";
        switch(GL11.glGetError()) {
            case GL11.GL_NO_ERROR: //errorString="GL_NO_ERROR";
                break;
            case GL11.GL_INVALID_ENUM:      errorString="GL_INVALID_ENUM";
                break;
            case GL11.GL_INVALID_VALUE:     errorString="GL_INVALID_VALUE";
                break;
            case GL11.GL_INVALID_OPERATION: errorString="GL_INVALID_OPERATION";
                break;
            case GL11.GL_OUT_OF_MEMORY:     errorString="GL_OUT_OF_MEMORY";
                break;
            case GL11.GL_STACK_UNDERFLOW:   errorString="GL_STACK_UNDERFLOW";
                break;
            case GL11.GL_STACK_OVERFLOW:    errorString="GL_STACK_OVERFLOW";
                break;
        }
        logger.error("OpenGl error in "+className+": "+errorString);
        return errorString;
    }*/
}
