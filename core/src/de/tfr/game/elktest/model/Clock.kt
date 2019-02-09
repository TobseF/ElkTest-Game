package de.tfr.game.elktest.model

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import de.tfr.game.elktest.Config
import de.tfr.game.util.ImageUtil.newImageLinear
import de.tfr.game.util.setOriginToCenter
import de.tfr.game.util.setSizeAsSquare

class Clock(private val time: Float) : Group() {

    private val background: Image = newImageLinear("clock.png")
    private val hand: Image = newImageLinear("hand.png")

    init {
        hand.setOriginToCenter()
        val size = background.height
        setSizeAsSquare(this, size)
        setPosition(Config.GAME_WIDTH / 2 - size / 2, 450f)
        addActor(background)
        addActor(hand)
    }

    fun start() {
        this.hand.addAction(Actions.rotateBy((-360).toFloat(), time))
    }

}
