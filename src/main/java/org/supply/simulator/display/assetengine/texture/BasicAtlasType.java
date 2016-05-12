package org.supply.simulator.display.assetengine.texture;

/**
 * Created by Alex on 7/30/2014.
 */
public class BasicAtlasType {

    private String fileName;
    private Integer id;
    protected Integer count=0;
    private int height;
    private int width;


    public void add() {
        count = count +1;
    }


    public void subtract() {
        count = count -1;
    }


    public Integer count() {
        return count;
    }


    public void setFileName(String fileName) {
        this.fileName=fileName;
    }


    public String getFileName() {
        return this.fileName;
    }


    public Integer getTextureId() {
        return id;
    }


    public void setTextureId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicAtlasType)) return false;
        return false;
    }


    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
