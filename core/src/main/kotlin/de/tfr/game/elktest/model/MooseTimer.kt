package de.tfr.game.elktest.model

import com.badlogic.gdx.Gdx.app
import com.badlogic.gdx.audio.Sound
import de.tfr.game.util.GdxTimer
import de.tfr.game.util.newSound
import java.util.*

class MooseTimer(gameTime: Float) {

    private val moose: Sound = newSound("moose.mp3")
    private val win: Sound = newSound("score.wav")
    private val loose: Sound = newSound("missed_hit.wav")
    private var nextMoose: GdxTimer? = null
    private var duringMoose: GdxTimer? = null
    private var delay: GdxTimer? = null
    private val gameOver: GdxTimer
    private val random = Random()
    private var hit = false

    init {
        gameOver = GdxTimer(gameTime, ::stop).start()
        reset()
    }

    fun start() {
        gameOver.start()
        reset()
    }

    private fun stop() {
        nextMoose?.stop()
        duringMoose?.stop()
        delay?.stop()
    }

    fun addPlayer(player1: Player, player2: Player) {
        player1.hitListener = ::hit
        player2.hitListener = ::hit
    }

    private fun moose() {
        moose.play()
        delay = GdxTimer(0.18f) { duringMoose?.start() }.start()
        duringMoose = GdxTimer(3f, ::reset)
    }

    fun loose() {
        loose.play()
    }

    private fun log(message: String) = app.log("MooseTimer", message)

    private fun hit(player: Player) {
        if (!hit) {
            if (duringMoose?.running == true) {
                hit = true
                win.play()
                player.newScore()
                log("New score $player")
            } else {
                player.loseScore()
                log("Missed hit $player")
                loose.play()
            }
        }
    }

    private fun reset() {
        hit = false
        log("reset")
        nextMoose?.stop()
        duringMoose?.stop()
        delay?.stop()

        nextMoose = GdxTimer(random.nextInt(9).toFloat()) { moose() }.start()
    }

    fun update(delta: Float) {
        listOf(nextMoose, duringMoose, delay, gameOver).forEach { it?.update(delta) }
    }
}