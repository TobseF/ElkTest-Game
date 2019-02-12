package de.tfr.game.elktest.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx.input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.BitmapFont
import de.tfr.game.elktest.Config
import de.tfr.game.elktest.model.*
import de.tfr.game.elktest.model.interfaces.PlayerInputListener
import de.tfr.game.elktest.model.interfaces.PlayerInputReciever
import de.tfr.game.util.*
import de.tfr.game.util.ImageUtil.newImageLinear

class GameScreen(private val game: Game, val playerInput: PlayerInputReciever, val inputConfig: InputConfig) :
        StagedScreenAdapter(Config.GAME_WIDTH,
        Config.GAME_HEIGHT), PlayerInputListener {


    override fun playerEvent(playerEvent: PlayerEvent) {
        this.playerEvent = playerEvent
    }

    private var playerEvent: PlayerEvent? = null
    private val music: Music
    private val moose: Sound
    private val gameStart: Sound
    private val textFont: BitmapFont
    private val scoreFont: BitmapFont
    private val player1: Player
    private val player2: Player
    private val clock: Clock
    private val gameOver: GameOver
    private val timer: MooseTimer
    private val startDelay: GdxTimer

    /**
     * Game time in seconds
     */
    private val gameTime = 60f

    init {
        val background = newImageLinear("menu_ingame.jpg")
        this.stage.addActor(background)
        this.music = newMusic("forest_theme.mp3").apply {
            volume = 0.5f
            isLooping = true
            play()
        }
        this.moose = newSound("moose.mp3")
        this.gameStart = newSound("game_start_de.mp3")
        this.textFont = newFont("score.fnt")
        this.scoreFont = newFont("score_big.fnt")
        this.player1 = Player(1, inputConfig.keyPlayerA, game, scoreFont, 25F)
        this.player2 = Player(2, inputConfig.keyPlayerB, game, scoreFont, 1020F)
        this.player1.setSignPosition(120F)
        this.player2.setSignPosition(-200F)
        this.timer = MooseTimer(gameTime).apply {
            addPlayer(player1, player2)
        }
        this.clock = Clock(gameTime)
        this.clock.addListener(newTouchDownListener { goToMainMenu() })

        this.gameOver = GameOver(player1, player2, scoreFont, gameTime)
        stage.addActors(player1, player2, clock, gameOver)
        this.gameStart.play()
        this.startDelay = GdxTimer(1.2f) { startGame() }.start()
        playerInput.addPlayerInputLister(this)
    }

    private fun startGame() {
        this.timer.start()
        this.clock.start()
        this.gameOver.startGame()
    }

    override fun render(delta: Float) {
        this.update(delta)
        this.stage.draw()
        this.checkInput()
    }

    private fun update(delta: Float) {
        this.stage.act(delta)
        this.timer.update(delta)
        this.startDelay.update(delta)
    }

    private fun checkInput() {
        if (input.isTouched && gameOver.isGameReadyToRestart) {
            goToMainMenu()
        }
        if (!gameOver.isGameEnded) {
            performPlayerInput()
        }
    }

    private fun goToMainMenu() {
        this.game.screen = MainMenuScreen(game, playerInput, inputConfig)
        this.dispose()
    }

    private fun performPlayerInput() {
        when (playerEvent) {
            PlayerEvent.Player1 -> player1.hit()
            PlayerEvent.Player2 -> player2.hit()
        }
        playerEvent = null
    }

    override fun dispose() {
        this.stage.dispose()
    }

    override fun resize(width: Int, height: Int) {
        stage.getViewport().update(width, height, true)
    }


}
