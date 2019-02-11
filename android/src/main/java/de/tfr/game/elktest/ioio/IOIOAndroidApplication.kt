package de.tfr.game.elktest.ioio

import android.content.Intent
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import ioio.lib.util.IOIOLooper
import ioio.lib.util.IOIOLooperProvider
import ioio.lib.util.android.IOIOAndroidApplicationHelper

abstract class IOIOAndroidApplication : AndroidApplication(), IOIOLooperProvider {

    private val ioioHelper = IOIOAndroidApplicationHelper(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ioioHelper.create()
    }


    override fun onDestroy() {
        super.onDestroy()
        ioioHelper.destroy()
    }

    override fun onStart() {
        super.onStart()
        ioioHelper.start()

    }

    override fun onStop() {
        super.onStop()
        ioioHelper.stop()

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.flags and Intent.FLAG_ACTIVITY_NEW_TASK != 0) {
            ioioHelper.restart()
        }
    }

    abstract override fun createIOIOLooper(connectionType: String?, extra: Any?): IOIOLooper

}
