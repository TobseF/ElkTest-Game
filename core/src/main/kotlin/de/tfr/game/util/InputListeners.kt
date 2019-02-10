package de.tfr.game.util

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener


fun newTouchDownListener(listener: TouchDownListener): InputListener {
    return object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            return listener.touchDown(event!!, x, y, pointer, button)
        }
    }
}

fun newTouchDownListener(event: () -> Unit, receiveAll: Boolean): InputListener {
    return object : InputListener() {
        override fun touchDown(e: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            event.invoke()
            return receiveAll
        }
    }
}

fun newTouchDownListener(eventreceiveAll: () -> Unit): InputListener {
    return newTouchDownListener(eventreceiveAll, false)
}
