package de.tfr.game.elktest.ioio

class ToggleAction(statusAction: (Boolean) -> Any, actionStep: Int,

        private var status: Boolean = false) : SteppedAction(actionStep = actionStep) {

    init {
        action = { triggerAction(statusAction) }
    }

    private fun triggerAction(statusAction: (Boolean) -> Any) {
        statusAction.invoke(status)
        status = !status
    }
}
