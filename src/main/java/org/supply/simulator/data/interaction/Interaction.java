package org.supply.simulator.data.interaction;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.entity.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/15/14
 * Time: 5:18 PM
 * Interaction is a way of describing a relationship between two Entities. Interactions can be described
 * by Attributes. Interactions are the highest non-generic super-type for all impl relationship descriptions.
 */
interface Interaction {
    /**
     * Sets the impl whence the interaction originates.
     *
     * @param fromEntity The impl "creator" of the Interaction.
     */
    void setFromEntity(Entity fromEntity);

    /**
     * Sets the impl to direct the interaction at.
     *
     * @param toEntity The impl to be affected.
     */
    void setToEntity(Entity toEntity);
}
