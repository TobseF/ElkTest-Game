package de.tfr.game.elktest.ioio

import de.tfr.game.elktest.model.PlayerEvent
import de.tfr.game.elktest.model.interfaces.PlayerInputListener
import ioio.lib.api.DigitalInput
import ioio.lib.api.DigitalInput.Spec.Mode.PULL_UP
import ioio.lib.api.DigitalOutput
import ioio.lib.api.IOIO
import ioio.lib.api.exception.ConnectionLostException
import ioio.lib.util.BaseIOIOLooper

class IoIoLooper(private val listeners: List<PlayerInputListener>) : BaseIOIOLooper() {

    private lateinit var statusLED: DigitalOutput
    private lateinit var bumperLeft: DigitalInput
    private lateinit var bumperRight: DigitalInput

    private val playerRight = DeBouncer { firePlayerInputEvent(PlayerEvent.Player1) }
    private val playerLeft = DeBouncer { firePlayerInputEvent(PlayerEvent.Player2) }

    private val statusBlinking = ToggleAction({ statusLED.write(it) }, 100)

    @Throws(ConnectionLostException::class)
    public override fun setup() {
        this.statusLED = ioio_.openDigitalOutput(IOIO.LED_PIN, true)
        this.bumperLeft = ioio_.openDigitalInput(10, PULL_UP)
        this.bumperRight = ioio_.openDigitalInput(11, PULL_UP)
    }

    var blink = true

    @Throws(ConnectionLostException::class, InterruptedException::class)
    override fun loop() {
        playerLeft.next(!bumperLeft.read())
        playerRight.next(!bumperRight.read())
        blink = !blink
        statusLED.write(blink)
        //statusBlinking.step()
        Thread.sleep(1000)
    }

    fun firePlayerInputEvent(playerNo: PlayerEvent) {
        listeners.forEach { it.playerEvent(playerNo) }
    }

    override fun disconnected() {}
}