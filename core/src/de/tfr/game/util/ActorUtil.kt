package de.tfr.game.util

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage

fun Stage.addActors(vararg actors: Actor) {
    actors.forEach { this.addActor(it) }
}

fun Actor.setOriginToCenter() {
    this.setOrigin(this.width / 2, this.height / 2)
}

fun setSizeAsSquare(it: Actor, size: Float) {
    it.setSize(size, size)
}

fun newEventActor(weight: Float, height: Float, x: Float = 0F, y: Float = 0F, eventListener: EventListener): Actor {
    return Actor().apply {
        setSize(weight, height)
        setPosition(x, y)
        addListener(eventListener)
    }
}

fun newTouchActor(weight: Float, height: Float, x: Float, y: Float, touchAction: () -> Unit): Actor {
    return newEventActor(weight, height, x, y, newTouchDownListener(touchAction))
}

fun newTouchActor(weight: Int, height: Int, touchAction: () -> Unit): Actor {
    return newTouchActor(weight.toFloat(), height.toFloat(), 0F, 0F, touchAction)
}
