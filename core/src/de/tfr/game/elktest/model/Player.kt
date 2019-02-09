package de.tfr.game.elktest.model


import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Interpolation.pow2
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import de.tfr.game.util.ImageUtil.newImageLinear
import de.tfr.game.util.newTouchDownListener

class Player(val number: Int, val game: Game, val font: BitmapFont, x: Float) : Group() {

    var hitListener: ((Player) -> Unit)? = null

    var score = 0
        private set

    private val mooseSign: Image

    init {
        setBounds(x, 0F, 270F, 720F)
        addListener(newTouchDownListener(this::hit))
        mooseSign = newImageLinear("moose_sign.png")
        addActor(mooseSign)

        mooseSign.addAction(Actions.alpha(0F))
    }

    fun setSignPosition(y: Float) {
        mooseSign.setPosition(y, 0F)
    }

    fun hit() {
        hitListener?.invoke(this)
    }

    fun newScore() {
        score++
        showMooseSignPopup()
    }

    fun showMooseSignPopup() {
        mooseSign.addAction(Actions.alpha(1F))
        mooseSign.addAction(Actions.alpha(0F, 1.2f, pow2))
    }


    fun loseScore() {
        if (score > 0) {
            score--
        }
    }

    override fun toString(): String {
        return "Player $number : $score"
    }

    operator fun compareTo(player: Player) = score - player.score

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        font.draw(batch, score.toString(), x + 90, y + 150)
    }

}