package org.supply.simulator.badengine.terrain.chunk;

/**
 * Created by Brandon on 7/21/2014.
 */
public class Quad {
    Vertex[] vertices;

    public Quad() {
        vertices = new Vertex[4];
        for(int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex();
        }
    }

    public Quad(Vertex topLeft, Vertex bottomLeft, Vertex bottomRight, Vertex topRight) {
        vertices = new Vertex[4];
        vertices[0] = topLeft;
        vertices[1] = bottomLeft;
        vertices[2] = bottomRight;
        vertices[3] = topRight;
    }
    
    public void setColors(int index, byte r, byte g, byte b, byte a) {
        Color color = vertices[index].getColor();
        color.r = r;
        color.g = g;
        color.b = b;
        color.a = a;
    }

    public void setColors(int index, byte r, byte g, byte b) {
        Color color = vertices[index].getColor();
        color.r = r;
        color.g = g;
        color.b = b;
    }

    public void setCoordinates(int index, float x, float y, float z, float w) {
        Coordinate coordinate = vertices[index].getCoordinate();
        coordinate.x = x;
        coordinate.y = y;
        coordinate.z = z;
        coordinate.w = w;
    }

    public void setCoordinates(int index, float x, float y, float z) {
        Coordinate coordinate = vertices[index].getCoordinate();
        coordinate.x = x;
        coordinate.y = y;
        coordinate.z = z;
    }

    public void setTopLeftColors(byte r, byte g, byte b, byte a) {
        setColors(0,r,g,b,a);
    }

    public void setTopLeftColors(byte r, byte g, byte b) {
        setColors(0,r,g,b);
    }

    public void setBottomLeftColors(byte r, byte g, byte b, byte a) {
        setColors(1,r,g,b,a);
    }

    public void setBottomLeftColors(byte r, byte g, byte b) {
        setColors(1,r,g,b);
    }

    public void setBottomRightColors(byte r, byte g, byte b, byte a) {
        setColors(2,r,g,b,a);
    }

    public void setBottomRightColors(byte r, byte g, byte b) {
        setColors(2,r,g,b);
    }

    public void setTopRightColors(byte r, byte g, byte b, byte a) {
        setColors(3,r,g,b,a);
    }

    public void setTopRightColors(byte r, byte g, byte b) {
        setColors(3,r,g,b);
    }




    public void setTopLeftCoordinates(float x, float y, float z, float w) {
        setCoordinates(0,x,y,z,w);
    }

    public void setTopLeftCoordinates(float x, float y, float z) {
        setCoordinates(0,x,y,z);
    }

    public void setBottomLeftCoordinates(float x, float y, float z, float w) {
        setCoordinates(1,x,y,z,w);
    }

    public void setBottomLeftCoordinates(float x, float y, float z) {
        setCoordinates(1,x,y,z);
    }

    public void setBottomRightCoordinates(float x, float y, float z, float w) {
        setCoordinates(2,x,y,z,w);
    }

    public void setBottomRightCoordinates(float x, float y, float z) {
        setCoordinates(2,x,y,z);
    }

    public void setTopRightCoordinates(float x, float y, float z, float w) {
        setCoordinates(3,x,y,z,w);
    }

    public void setTopRightCoordinates(float x, float y, float z) {
        setCoordinates(3,x,y,z);
    }



    public Vertex getTopLeftVertex() {
        return vertices[0];
    }
    public Vertex getBottomLeftVertex() {
        return vertices[1];
    }
    public Vertex getBottomRightVertex() {
        return vertices[2];
    }
    public Vertex getTopRightVertex() {
        return vertices[3];
    }
}
