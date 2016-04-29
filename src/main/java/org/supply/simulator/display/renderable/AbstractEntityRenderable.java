package org.supply.simulator.display.renderable;

import org.lwjgl.opengl.GL11;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/13/2014.
 */
public abstract class AbstractEntityRenderable extends HasLogger implements EntityRenderable {

    protected boolean isDestroyed;

    protected boolean isBuilt;

    protected Entity entity;


    public AbstractEntityRenderable() {
        isDestroyed = false;
        isBuilt = false;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public boolean isBuilt() {return isBuilt;}

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Prints OpenGL error types
     *
     * @param className
     */
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
    }


}
