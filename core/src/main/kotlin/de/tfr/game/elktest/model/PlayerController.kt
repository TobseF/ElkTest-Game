package de.tfr.game.elktest.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerAdapter
import com.badlogic.gdx.controllers.Controllers

class PlayerController(val buttonIndex: Int, val action: () -> Unit) : ControllerAdapter() {

    private companion object {
        fun log(message: String) = Gdx.app.log("PlayerController", message)

        init {
            log("Found controllers:" + Controllers.getControllers())
        }
    }

    init {
        Controllers.addListener(this)
    }


    override fun buttonDown(controller: Controller, buttonIndex: Int): Boolean {
        if (this.buttonIndex == buttonIndex) {
            action.invoke()
        }
        log("Button $buttonIndex on Controller $controller")
        return false
    }
}