package de.tfr.game.util

import de.tfr.game.elktest.model.interfaces.Resetable

/**
 * Timer which provides a {@link #timerAction()} function. It's called after the the timer was
 * started with {@link #start()} and the {@link #duration} period has passe by. The timer
 * <b>has to<b> updated with {@link #update(float)}.
 *
 * @see #timerAction()
 */
class GdxTimer : Resetable {

    /** Time in ms before the Timer Action is called */
    var duration = 0F
        set(value) {
            reset()
            field = value
        }

    /** Time since the timer was started, or last {@link #timerAction()} was called. */
    var timeSinceStart = 0F

    /** Whether the timer should run consecutively (true) */
    var repeating = false

    /** Whether the timer is running at the moment */
    var running = false

    var timerAction: (() -> Unit)? = null

    /**
     * @param duration time before the Timer Action is called in [seconds].
     * @param repeat Whether the timer should run consecutively (true) or stop after one
     *            runningOnStart Whether the Timer should start running on build (true) ore
     *            have started with {@link #setPaused(boolean)} Timer Action (false)
     * @see #MyTimer(float, boolean)
     * @see #timerAction()
     */
    constructor(duration: Float, repeat: Boolean, runningOnStart: Boolean = true) {
        this.duration = duration
        this.repeating = repeat
        running = runningOnStart
    }

    /**
     * @param duration time before the Timer Action is called in [seconds].
     * @see #MyTimer(float, boolean, boolean)
     * @see #timerAction()
     */
    constructor(duration: Float) {
        this.duration = duration
    }

    /**
     * @param duration time before the Timer Action is called in [seconds]
     * @param timerAction
     * @see #timerAction()
     */
    constructor(duration: Float, timerAction: () -> Unit) {
        this.duration = duration
        this.timerAction = timerAction
    }

    fun update(delta: Float) {
        if (running) {
            timeSinceStart += delta
            if (timeSinceStart >= duration) {
                timerAction()
                if (repeating) {
                    timeSinceStart -= duration
                } else {
                    running = false
                }
            }
        }
    }

    /**
     * Action which is proceeded on a Timer event. Overwrite this function to get Timer
     * functionality.
     */
    protected fun timerAction() {
        timerAction?.invoke()
    }

    fun setPaused(doPause: Boolean) {
        running = !doPause
    }


    /**
     * @return if the timer paused (not running)
     */
    fun isPaused() = !running

    /**
     * @return if the timer is not running and the {@link #timerAction()} was called
     */
    fun isFinished() = !running && timeSinceStart >= duration

    /**
     * Starts the timer.
     */
    fun start(): GdxTimer {
        running = true
        return this
    }

    /**
     * Pauses the timer.
     */
    fun pause() {
        running = false
    }

    /**
     * Stops the timer, an resets it.
     */
    fun stop() {
        reset()
        pause()
    }


    /**
     * Adds the time [sconds] to the duration
     */
    fun add(time: Float) {
        duration += time
    }

    fun isRunning() = running


    fun noRepeating(): GdxTimer {
        repeating = false
        return this
    }

    fun noRepeting(): GdxTimer {
        repeating = false
        return this
    }

    fun withRepetition(): GdxTimer {
        repeating = false
        return this
    }

    override fun reset() {
        timeSinceStart = 0F
    }
}