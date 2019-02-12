package de.tfr.game.elktest.android

import android.os.Bundle
import android.view.KeyEvent
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import de.tfr.game.elktest.ElkTestGame
import de.tfr.game.elktest.ioio.IOIOAndroidApplication
import de.tfr.game.elktest.ioio.IoIoLooper
import de.tfr.game.elktest.model.InputConfig
import de.tfr.game.elktest.model.interfaces.PlayerInputListener
import de.tfr.game.elktest.model.interfaces.PlayerInputReciever
import ioio.lib.util.IOIOLooper
import java.util.*

/**
 * Runs the elk test with touch, gamepad and Android open accessory support (via a IOIO board).
 */
class AndroidLauncher : IOIOAndroidApplication(), PlayerInputReciever {

    private val listeners = ArrayList<PlayerInputListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        val inputConfig = InputConfig(KeyEvent.KEYCODE_BUTTON_A, KeyEvent.KEYCODE_BUTTON_B)
        val game = ElkTestGame(this, inputConfig)
        this.initialize(game, config)
    }

    override fun addPlayerInputLister(listener: PlayerInputListener) {
        this.listeners.add(listener)
    }

    override fun createIOIOLooper(connectionType: String?, extra: Any?): IOIOLooper {
        return IoIoLooper(this.listeners)
    }
}
