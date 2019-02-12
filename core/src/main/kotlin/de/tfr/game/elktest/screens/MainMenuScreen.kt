package de.tfr.game.elktest.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import de.tfr.game.elktest.Config
import de.tfr.game.elktest.model.InputConfig
import de.tfr.game.elktest.model.interfaces.PlayerInputReciever
import de.tfr.game.util.GdxTimer
import de.tfr.game.util.GlUtil
import de.tfr.game.util.ImageUtil.newImageLinear
import de.tfr.game.util.StagedScreenAdapter
import de.tfr.game.util.newTouchActor

class MainMenuScreen(private val game: Game,
        private val playerInput: PlayerInputReciever,
        val inputConfig: InputConfig) : StagedScreenAdapter(Config.GAME_WIDTH, Config.GAME_HEIGHT) {

    private val startDelay: GdxTimer = GdxTimer(0.4f).start()

    init {
        this.stage.addActor(newImageLinear("menu_main.jpg"))
        this.stage.addActor(newTouchActor(300F, 130F, 980F, 0F) { startGame() })
        this.stage.addActor(newTouchActor(450, 130) { exit() })
    }

    private fun exit() {
        Gdx.app.exit()
    }

    override fun render(delta: Float) {
        this.startDelay.update(delta)
        GlUtil.glClear()
        this.stage.draw()
        this.stage.act()
    }

    private fun startGame() {
        this.game.screen = GameScreen(game, playerInput, inputConfig)
        this.dispose()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        this.stage.dispose()
    }
}
