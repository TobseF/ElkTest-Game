package de.tfr.game.elktest.screens

import de.tfr.game.elktest.model.GameOver

object Strings {
    fun getText(gameOverState: GameOver.GameOverState?): String? {
        if (gameOverState != null) {
            when (gameOverState) {
                GameOver.GameOverState.Player1 -> return "Spieler 1"
                GameOver.GameOverState.Player2 -> return "Spieler 2"
                GameOver.GameOverState.Draw -> return "Gleichstand"
                else -> {
                }
            }
        }
        return null
    }
}
