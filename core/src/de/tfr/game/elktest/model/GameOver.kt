package de.tfr.game.elktest.model

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Actor
import de.tfr.game.elktest.model.GameOver.GameOverState.*
import de.tfr.game.elktest.screens.Strings
import de.tfr.game.util.GdxTimer
import de.tfr.game.util.newSound

class GameOver(private val player1: Player,
        private val player2: Player,
        private val font: BitmapFont,
        gameTime: Float) : Actor() {

    private val gameOver: GdxTimer
    private val player1wins: Sound
    private val player2wins: Sound
    private val drawGame: Sound
    private val gameOverDelay: GdxTimer

    private val gameOverState: GameOverState
        get() {
            if (player1 > player2) {
                return Player1
            } else {
                if (player1 < player2) {
                    return Player2
                }
            }
            return Draw
        }

    val isGameEnded: Boolean
        get() = this.gameOver.isFinished()

    val isGameReadyToRestart: Boolean
        get() = this.gameOverDelay.isFinished()

    enum class GameOverState {
        Player1, Player2, Draw
    }

    init {
        this.gameOver = GdxTimer(gameTime) { gameOver() }
        this.gameOverDelay = GdxTimer(gameTime + 1.2f)
        this.player1wins = newSound("game_end_player_1_de.mp3")
        this.player2wins = newSound("game_end_player_2_de.mp3")
        this.drawGame = newSound("game_end_draw_de.mp3")
    }

    fun startGame() {
        gameOver.start()
        gameOverDelay.start()
    }

    override fun act(delta: Float) {
        super.act(delta)
        gameOver.update(delta)
        gameOverDelay.update(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        if (gameOver.isFinished()) {
            this.font.draw(batch, "Gewinner:", 360f, 350f)
            this.font.draw(batch, Strings.getText(gameOverState), 390f, 150f)
        }
    }

    fun gameOver() {
        when (gameOverState) {
            Player1 -> player1wins.play()
            Player2 -> player2wins.play()
            Draw -> drawGame.play()
        }
    }
}
