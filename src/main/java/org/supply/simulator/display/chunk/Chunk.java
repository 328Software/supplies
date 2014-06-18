package org.supply.simulator.display.chunk;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Chunk {

    /**
     *
     * @param length
     * @param width
     */
    public void init (int length, int width);

    /**
     *
     * @param vertexData
     */
    public void addVertex(VertexData vertexData);

    /**
     *
     * @param vertexData
     */
    public void addVertices(VertexData[] vertexData);

    /**
     *
     */
    public void build ();

    /**
     *
     * @return
     */
    public BufferIds getBufferIds ();




}
