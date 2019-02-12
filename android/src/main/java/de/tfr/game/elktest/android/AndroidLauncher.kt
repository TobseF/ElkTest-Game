package de.tfr.game.elktest.android

import android.os.Bundle
import android.view.KeyEvent
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import de.tfr.game.elktest.ElkTestGame
import de.tfr.game.elktest.model.InputConfig

/**
 * Runs the elk test with touch and gamepad support
 */
class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration = AndroidApplicationConfiguration()
        val inputConfig = InputConfig(KeyEvent.KEYCODE_BUTTON_A, KeyEvent.KEYCODE_BUTTON_B)
        initialize(ElkTestGame(inputConfig = inputConfig), configuration)
    }

}