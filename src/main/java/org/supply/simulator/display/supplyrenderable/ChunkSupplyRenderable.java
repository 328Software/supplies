package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.util.Renderable;

/**
 * Interface for all objects that will be rendered by the Display Engine. All OpenGl code will be in classes that 
 * implement this interface(with the exception of the ShaderEngine).
 *
 * Created by Alex on 6/29/2014.
 */
public interface ChunkSupplyRenderable extends SupplyRenderable, Renderable {



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
