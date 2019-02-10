package de.tfr.game.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image

object ImageUtil {
    fun newImage(texture: Texture): Image {
        return Image(texture)
    }

    fun newImageLinear(internalPath: String): Image {
        return Image(newTextureLinear(internalPath))
    }
}
