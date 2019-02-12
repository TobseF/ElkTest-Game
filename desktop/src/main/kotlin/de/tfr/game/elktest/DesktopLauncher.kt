package de.tfr.game.elktest

import com.badlogic.gdx.Files.FileType
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import de.tfr.game.elktest.model.InputConfig

/**
 * Runs the elk test desktop game (on LWJGL).
 */
object DesktopLauncher {

    private val defaultConfig = LwjglApplicationConfiguration().apply {
        title = "Elk Test"
        width = Config.GAME_WIDTH
        height = Config.GAME_HEIGHT
        addIcons()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun LwjglApplicationConfiguration.addIcons() {
        intArrayOf(128, 64, 32, 16).forEach { size ->
            this.addIcon("icon_$size.png", FileType.Internal)
        }
    }

    private fun createApplication(): LwjglApplication {
        val inputConfig = InputConfig(keyPlayerA = 1, keyPlayerB = 2)
        return LwjglApplication(ElkTestGame(inputConfig = inputConfig), defaultConfig)
    }
}