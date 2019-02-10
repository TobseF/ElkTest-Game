package de.tfr.game.elktest.model

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerAdapter

class PlayerController(val buttonIndex: Int, val action: () -> Unit) : ControllerAdapter() {

    override fun buttonDown(controller: Controller, buttonIndex: Int): Boolean {
        if (this.buttonIndex == buttonIndex) {

        }
        action.invoke()
        return false
    }
}