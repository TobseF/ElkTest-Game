package de.tfr.game.util

import com.badlogic.gdx.Files.FileType.Internal
import com.badlogic.gdx.Gdx.audio
import com.badlogic.gdx.Gdx.files
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.BitmapFont

object Dir {
    const val SOUNDS = "sounds/"
    const val MUSIC = "music/"
    const val FONTS = "fonts/"
    const val IMAGES = "images/"
}

fun newTexture(fileName: String) = Texture(Dir.IMAGES + fileName)

fun newTextureLinear(internalPath: String): Texture {
    return newTexture(internalPath).apply { setFilter(Linear, Linear) }
}

fun newSound(fileName: String): Sound {
    return audio.newSound(getFileHandleInternal(Dir.SOUNDS + fileName))
}

fun newMusic(fileName: String): Music {
    return audio.newMusic(getFileHandleInternal(Dir.MUSIC + fileName))

}

fun newFont(fileName: String) = BitmapFont(getFileHandleInternal(Dir.FONTS + fileName))

private fun getFileHandleInternal(path: String) = files.getFileHandle(path, Internal)!!
