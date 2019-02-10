/*
 * @version 0.0 30.05.2008
 *  F
 */
package de.tfr.game.elktest.model.interfaces

/**
 * To reset objects to their init BallState. Good for game restart.
 *
 *
 */
interface Resetable {

    /** Resets the Object to the default values  */
    fun reset()
}