package org.supply.simulator.display.assetengine.texture;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.entity.Entity;

import java.util.Comparator;

/**
 * Created by Alex on 7/30/2014.
 */
public class Atlas implements HasId<Atlas, Integer> {

    private String fileName;
    private Integer id;
    private int height;
    private int width;

    public void setFileName(String fileName) {
        this.fileName=fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int compareTo(Atlas o) {
        return Comparator.comparing(Atlas::getId).compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atlas atlas = (Atlas) o;

        return id != null ? id.equals(atlas.id) : atlas.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
