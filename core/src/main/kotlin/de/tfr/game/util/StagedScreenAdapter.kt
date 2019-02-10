package de.tfr.game.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport

abstract class StagedScreenAdapter(width: Int, height: Int) : ScreenAdapter() {

    protected val stage: Stage = Stage(StretchViewport(width.toFloat(), height.toFloat()))

    init {
        Gdx.input.inputProcessor = this.stage
    }
}
