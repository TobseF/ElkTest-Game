package de.tfr.game.elktest

import com.badlogic.gdx.Files.FileType
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

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
        return LwjglApplication(ElkTestGame(), defaultConfig)
    }
}