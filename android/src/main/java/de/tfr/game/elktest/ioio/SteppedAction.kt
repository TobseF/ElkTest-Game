package de.tfr.game.elktest.ioio


open class SteppedAction(var action: (() -> Any)? = null, val actionStep: Int) {

    private var step = 0

    fun step() {
        step++
        if (step == actionStep) {
            action?.invoke()
            reset()
        }
    }

    fun reset() {
        this.step = 0
    }
}
