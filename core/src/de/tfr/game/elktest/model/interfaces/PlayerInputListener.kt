package de.tfr.game.elktest.model.interfaces


import de.tfr.game.elktest.model.PlayerEvent

interface PlayerInputListener {

    fun playerEvent(playerEvent: PlayerEvent)

}
