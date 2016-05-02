package org.supply.simulator.badengine.terrain.chunk;

/**
 * Created by Brandon on 7/21/2014.
 */
public class Vertex {
    private Color color;
    private Coordinate coordinate;

    public Vertex() {
        color = new Color((byte)0,(byte)0,(byte)0);
        coordinate = new Coordinate(0f,0f,0f);
    }

    public Color getColor() {
        return color;
    }

    public byte[] colorArray() {
        return new byte[]{color.r,color.g, color.b, color.a};
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public float[] coordinateArray() {
        return new float[] {coordinate.x,coordinate.y,coordinate.z,coordinate.w};
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
