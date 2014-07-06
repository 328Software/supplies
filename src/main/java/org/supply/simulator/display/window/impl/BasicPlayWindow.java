package org.supply.simulator.display.window.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkManager;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfoAbstract;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.ShaderProgramType;
import org.supply.simulator.display.window.AbstractPlayWindow;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.Window;

import java.nio.FloatBuffer;

/**
 * Created by Alex on 6/27/2014.
 */
public class BasicPlayWindow extends AbstractPlayWindow implements Window{
    public BasicPlayWindow() {
        super();
    }
}
