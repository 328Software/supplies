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
     * Checks if this object has been built yet.
     *
     * @return false until build() has been called successfully
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
     * Checks if this object has been destroyed yet.
     *
     * @return false until destroy() has been called
     */
    public boolean isDestroyed();

    /**
     * Sets the vertex attributes buffer object id.
     *
     * @param vertexAttributesId vertex attributes buffer object id
     */
    void setVertexAttributesId(int vertexAttributesId);

    /**
     * Sets the indices buffer object id.
     *
     * @param indicesBufferId indices buffer object id
     */
    void setIndicesBufferId(int indicesBufferId);

    /**
     * Sets the colors array buffer object id.
     *
     * @param colorsArrayId colors array buffer object id.
     */
    void setColorsArrayId(int colorsArrayId);

    /**
     * Sets the positions array buffer object id.
     *
     * @param positionsArrayId positions array buffer object id
     */
    void setPositionsArrayId(int positionsArrayId);

    /**
     * Sets the entity buffer object id.
     *
     * @param entityBufferID entity buffer object id
     */
    void setEntityBufferId(int entityBufferID);

    /**
     * Returns the vertex attributes buffer object id.
     *
     * @return vertex attributes buffer object id
     */
    int getVertexAttributesId();

    /**
     * Returns the indices buffer object id.
     *
     * @return indices buffer object id
     */
    int getIndicesBufferId();

    /**
     * Returns the positions array buffer object id.
     *
     * @return positions array buffer object id
     */
    int getPositionsArrayId();

    /**
     * Returns the colors array buffer object id.
     *
     * @return colors array buffer object id
     */
    int getColorsArrayId();

    /**
     * Returns the entity buffer object id.
     *
     * @return entity buffer object id
     */
    int getEntityBufferId();

    /**
     * Sets the vertex shader attribute locations. These locations were created by the ShaderEngine and reference\
     * uniforms within shaders(glsl).
     *
     * @param locations vertex shader attribute locations
     */
    void setAttributeLocations(int[] locations);

    /**
     * Gets the vertex shader attribute locations. These locations were created by the ShaderEngine and reference\
     * uniforms within shaders(glsl)
     *
     * @return
     */
    int[] getAttributeLocations ();

    /**
     * Sets rows;
     *
     * @param rows rows int
     */
    void setRows(int rows);

    /**
     * Sets columns
     *
     *@param columns columns int
     */
    void setColumns(int columns);

    /**
     *  Returns rows.
     *
     * @return rows
     */
    int getRows();

    /**
     *  Returns columns;
     *
     * @return columns
     */
    int getColumns();

}
