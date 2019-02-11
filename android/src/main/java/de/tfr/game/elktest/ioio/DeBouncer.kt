package de.tfr.game.elktest.ioio

/**
 * Fires an action on a positive flag.
 */
class DeBouncer(private val action: () -> Any) {

    private var released = false

    fun next(value: Boolean) {
        if (value && released) {
            action.invoke()
        }
        released = !value
    }
}
