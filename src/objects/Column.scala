package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.{Gdx, Input}

import scala.collection.mutable


case class Column(keyStatus: mutable.HashMap[Int, Boolean]) extends DrawableObject {
  private val column: BitmapImage = new BitmapImage("src/res/column.png")
  private val columnBase: BitmapImage = new BitmapImage("src/res/column-base.png")
  private var posX: Int = 1980 - 100
  private val posYTop: Int = 800 // posY for the top column
  private val posYBottom: Int = 280 // posY for the bottom column
  private val columnGap: Int = 100
  private var isStarted: Boolean = false
  private var currentTime: Float = 0
  private var lastColumnCreated: Float = 0
  private val columnFrequency = 10


  override def draw(g: GdxGraphics): Unit = {
    if (keyStatus(Input.Keys.SPACE)) isStarted = true // Checks if the space is pressed to start the game
    if (isStarted){
      currentTime += Gdx.graphics.getDeltaTime
      if(((currentTime - lastColumnCreated) * 1000) > columnFrequency){
        // Top column
        g.drawTransformedPicture(posX, posYTop + columnGap, 0, 0.5f, column)
        g.drawTransformedPicture(posX, posYTop, 0, 0.5f, columnBase)

        // Bottom column
        g.drawTransformedPicture(posX, posYBottom - columnGap, 180, 0.5f, column)
        g.drawTransformedPicture(posX, posYBottom, 180, 0.5f, columnBase)
        lastColumnCreated = currentTime
      }
    }

    // Loop effect
    if(isStarted){
      if(posX == -100) {
        posX = 1920 + 100
      }
      // Changes the speed
      posX -= 10
    }
  }
}