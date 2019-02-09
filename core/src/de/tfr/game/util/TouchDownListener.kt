package de.tfr.game.util

import com.badlogic.gdx.scenes.scene2d.InputEvent

interface TouchDownListener {
    fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean
}
