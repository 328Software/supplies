package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.util.Renderable;

/**
 * Interface for all objects that will be rendered by the Display Engine. All OpenGl code will be in classes that 
 * implement this interface(with the exception of the ShaderEngine).
 *
 * Created by Alex on 6/29/2014.
 */
public interface SupplyRenderable extends Renderable {

    /**
     * Prepares object to be rendered. Called once before rendering begins.
     *
     */
    public void build();

    /**
     * Checks if this object has been built yet. Returns false until build() has been called successfully.
     *
     * @return
     */
    public boolean isBuilt();

    /**
     * Deletes all information this object stored in OpenGl buffers. Can be called once after the build() method has
     * been called. This must be called before allowing this object to be destroyed by the garbage collector, or else
     * there would be a memory leak with orphaned OpenGl buffers floating around.
     *
     */
    public void destroy();

    /**
     * Returns false until destroy() has been called.
     *
     * @return
     */
    public boolean isDestroyed();

    /**
     * Sets the vertex attributes buffer object id.
     *
     * @param vertexAttributesId
     */
    void setVertexAttributesId(int vertexAttributesId);

    /**
     * Sets the indices buffer object id.
     *
     */
    void setIndicesBufferId(int indicesBufferId);

    /**
     * Sets the colors array buffer object id.
     *
     * @param colorsArrayId
     */
    void setColorsArrayId(int colorsArrayId);

    /**
     * Sets the positions array buffer object id.
     *
     * @param verticesArrayId
     */
    void setPositionsArrayId(int verticesArrayId);

    /**
     * Sets the entity buffer object id.
     *
     * @param entityBufferID
     */
    void setEntityBufferId(int entityBufferID);

    /**
     * Returns the vertex attributes buffer object id.
     *
     * @return
     */
    int getVertexAttributesId();

    /**
     * Returns the indices buffer object id.
     *
     * @return
     */
    int getIndicesBufferId();

    /**
     * Returns the positions array buffer object id.
     *
     * @return
     */
    int getPositionsArrayId();

    /**
     * Returns the colors array buffer object id.
     *
     * @return
     */
    int getColorsArrayId();

    /**
     * Returns the entity buffer object id.
     *
     * @return
     */
    int getEntityBufferId();

    /**
     * Setter.
     *
     * @param locations
     */
    void setAttributeLocations(int[] locations);

    /**
     * Getter.
     *
     * @return
     */
    int[] getAttributeLocations ();

    /**
     * Sets rows;
     *
     */
    void setRows(int rows);

    /**
     * Sets columns
     *
     */
    void setColumns(int columns);

    /**
     *  Returns rows.
     *
     * @return
     */
    int getRows();

    /**
     *  Returns columns;
     *
     * @return
     */
    int getColumns();

}
