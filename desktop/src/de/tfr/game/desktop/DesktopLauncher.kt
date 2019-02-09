package de.tfr.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import de.tfr.game.elktest.Config
import de.tfr.game.elktest.ElkTestGame

/**
 * Run the desktop game.
 *
 * Ensure you set the `Working Directory` in the run configuration to `$PROJECT_DIR$\android\assets`.
 */
fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration().apply {
        width = Config.GAME_WIDTH
        height = Config.GAME_HEIGHT
    }
    LwjglApplication(ElkTestGame(), config)
}



