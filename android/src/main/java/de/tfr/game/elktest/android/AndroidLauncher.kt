package de.tfr.game.elktest.android

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import de.tfr.game.elktest.ElkTestGame
import de.tfr.game.elktest.ioio.IOIOAndroidApplication
import de.tfr.game.elktest.ioio.IoIoLooper
import de.tfr.game.elktest.model.interfaces.PlayerInputListener
import de.tfr.game.elktest.model.interfaces.PlayerInputReciever
import ioio.lib.util.IOIOLooper
import java.util.*

class AndroidLauncher : IOIOAndroidApplication(), PlayerInputReciever {

    private val listeners = ArrayList<PlayerInputListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        val game = ElkTestGame(this)
        this.initialize(game, config)
    }

    override fun addPlayerInputLister(listener: PlayerInputListener) {
        this.listeners.add(listener)
    }

    override fun createIOIOLooper(connectionType: String?, extra: Any?): IOIOLooper {
        return IoIoLooper(this.listeners)
    }
}
