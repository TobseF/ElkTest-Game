package de.tfr.game.elktest

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import de.tfr.game.elktest.model.InputConfig
import de.tfr.game.elktest.model.interfaces.PlayerInputListener
import de.tfr.game.elktest.model.interfaces.PlayerInputReciever
import de.tfr.game.elktest.screens.MainMenuScreen

class ElkTestGame(private val playerInput: PlayerInputReciever = noSpecialInput,
        val inputConfig: InputConfig) : Game() {

    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        setScreen(MainMenuScreen(this, playerInput, inputConfig))
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}

val noSpecialInput: PlayerInputReciever = object : PlayerInputReciever {
    override fun addPlayerInputLister(listener: PlayerInputListener) {
    }
}