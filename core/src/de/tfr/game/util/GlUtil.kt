package de.tfr.game.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

object GlUtil {
    /**
     * Clears the Screen black
     */
    fun glClear() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
}
