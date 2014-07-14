package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.opengl.GL11;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/13/2014.
 */
public abstract class AbstractSupplyRenderable extends HasLogger implements SupplyRenderable {

    protected int[] locations;


    @Override
    public void setAttributeLocations(int[] locations) {
        this.locations = locations;
    }

    @Override
    public int[] getAttributeLocations () {
        return this.locations;

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
