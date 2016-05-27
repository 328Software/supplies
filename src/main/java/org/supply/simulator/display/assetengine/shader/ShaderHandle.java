package org.supply.simulator.display.assetengine.shader;

/**
 * Created by Alex on 7/13/2014.
 */
public class ShaderHandle {

    //todo why?
    private int projectionMatrixLocation;
    private int viewMatrixLocation;
    private int modelMatrixLocation;

    private int programId;


    public void setModelMatrixLocation(int modelMatrixLocation) {
        this.modelMatrixLocation = modelMatrixLocation;
    }


    public void setProjectionMatrixLocation(int projectionMatrixLocation) {
        this.projectionMatrixLocation = projectionMatrixLocation;
    }



    public void setViewMatrixLocation(int viewMatrixLocation) {
        this.viewMatrixLocation = viewMatrixLocation;
    }


    public void setProgramId(int programId) {
        this.programId = programId;
    }




    public int getProjectionMatrixLocation() {
        return projectionMatrixLocation;
    }


    public int getViewMatrixLocation() {
        return viewMatrixLocation;
    }


    public int getModelMatrixLocation() {
        return modelMatrixLocation;
    }

    public int getProgramId() {
        return programId;
    }
}
