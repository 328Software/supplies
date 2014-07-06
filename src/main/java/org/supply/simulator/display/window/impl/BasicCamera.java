package org.supply.simulator.display.window.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfoAbstract;
import org.supply.simulator.display.window.AbstractCamera;
import org.supply.simulator.display.window.Camera;

import java.nio.FloatBuffer;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicCamera extends AbstractCamera {

    public BasicCamera() {
        super();
    }

    @Override
    protected void getNewData() {

    }
}
