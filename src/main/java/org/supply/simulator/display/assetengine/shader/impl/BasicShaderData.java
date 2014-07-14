package org.supply.simulator.display.assetengine.shader.impl;

import org.supply.simulator.display.assetengine.shader.ShaderData;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;

/**
 * Created by Alex on 7/13/2014.
 */
public class BasicShaderData implements ShaderData{

    private int projectionMatrixLocation;
    private int viewMatrixLocation;
    private int modelMatrixLocation;

    private int programId;

    @Override
    public void setModelMatrixLocation(int modelMatrixLocation) {
        this.modelMatrixLocation = modelMatrixLocation;
    }

    @Override
    public void setProjectionMatrixLocation(int projectionMatrixLocation) {
        this.projectionMatrixLocation = projectionMatrixLocation;
    }


    @Override
    public void setViewMatrixLocation(int viewMatrixLocation) {
        this.viewMatrixLocation = viewMatrixLocation;
    }

    @Override
    public void setProgramId(int programId) {
        this.programId = programId;
    }



    @Override
    public int getProjectionMatrixLocation() {
        return projectionMatrixLocation;
    }

    @Override
    public int getViewMatrixLocation() {
        return viewMatrixLocation;
    }

    @Override
    public int getModelMatrixLocation() {
        return modelMatrixLocation;
    }
    @Override
    public int getProgramId() {
        return programId;
    }
}
