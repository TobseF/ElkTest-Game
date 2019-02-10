/*
 * @version 0.0 03.06.2008
 *  F
 */
package de.tfr.game.elktest.model.interfaces

/**
 * For things that should be updated
 *
 *
 */
interface Updateable {

    /**
     * Performs all logic which should be updated
     *
     * @param delta time in ms since the last time updateded was called
     */
    fun update(delta: Float)
}
