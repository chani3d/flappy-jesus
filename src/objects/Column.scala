package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.FlappyJesus
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsBox
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import ch.hevs.gdx2d.lib.physics.AbstractPhysicsObject
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, Input}

import javax.swing.plaf.synth.Region
import scala.collection.mutable
import scala.util.Random

object Column{
  var position = new Vector2(posX, posYTop)
  var posX: Int = 1920 - 40
  var posYTop: Int = 0

  var w: Int = (500 * 0.5).toInt
  var h: Int = (1080 * 0.5).toInt


}

case class Column(keyStatus: mutable.HashMap[Int, Boolean]) extends DrawableObject {
  private val column: BitmapImage = new BitmapImage("src/res/column.png")
  private val columnBase: BitmapImage = new BitmapImage("src/res/column-base.png")
  private val speed: Int = 10
  private val columnGap: Int = 200
  private val columnRestart: Int = -100
  private val posYTopBase: Int = 800 // posY for the top column
  private val posYBottomBase: Int = 280 // posY for the top column

  private val columnTopMin: Int = 620
  private val columnTopMax: Int = 850

  private val columnBottomMin: Int = 100
  private val columnBottomMax: Int = 400


  private val random: Random = new Random()


  private var posX1: Int = Column.posX + 700
  private var posX2: Int = posX1 + 700

  Column.posYTop = random.between(columnTopMin, columnTopMax)
  private var posY1Top: Int = random.between(columnTopMin, columnTopMax)
  private var posY2Top: Int = random.between(columnTopMin, columnTopMax)

  private var posYBottom: Int = random.between(columnBottomMin, columnBottomMax)
  private var posY1Bottom: Int = random.between(columnBottomMin, columnBottomMax)
  private var posY2Bottom: Int = random.between(columnBottomMin, columnBottomMax)





  override def draw(g: GdxGraphics): Unit = {

    if (keyStatus(Input.Keys.SPACE)) FlappyJesus.isStarted = true // Checks if the space is pressed to start the game
    if (FlappyJesus.isStarted){
      // Group 0
      // Top column
      g.drawTransformedPicture(Column.posX, Column.posYTop + columnGap, 0, 0.5f, column)
      g.drawTransformedPicture(Column.posX, posYTopBase, 0, 0.5f, columnBase)

      // Bottom column
      g.drawTransformedPicture(Column.posX, posYBottom - columnGap, 180, 0.5f, column)
      g.drawTransformedPicture(Column.posX, posYBottomBase, 180, 0.5f, columnBase)
      //println(s"diff: ${(posYTop + columnGap) - (posYBottom - columnGap)}")
      // Group 1
      // Top column
      g.drawTransformedPicture(posX1, posY1Top + columnGap, 0, 0.5f, column)
      g.drawTransformedPicture(posX1, posYTopBase, 0, 0.5f, columnBase)

      // Bottom column
      g.drawTransformedPicture(posX1, posY1Bottom - columnGap, 180, 0.5f, column)
      g.drawTransformedPicture(posX1, posYBottomBase, 180, 0.5f, columnBase)

      // Group 2
      // Top column
      g.drawTransformedPicture(posX2, posY2Top + columnGap, 0, 0.5f, column)
      g.drawTransformedPicture(posX2, posYTopBase, 0, 0.5f, columnBase)

      // Bottom column
      g.drawTransformedPicture(posX2, posY2Bottom - columnGap, 180, 0.5f, column)
      g.drawTransformedPicture(posX2, posYBottomBase, 180, 0.5f, columnBase)
    }

    // Loop effect
    if(FlappyJesus.isStarted){
      if(Column.posX == columnRestart) {
        Column.posX = 1920 + 100
        Column.posYTop = random.between(columnTopMin, columnTopMax)
        posYBottom = random.between(columnBottomMin, columnBottomMax)
      } else if(posX1 == columnRestart){
        posX1 = 1920 + 100
        posY1Top = random.between(columnTopMin, columnTopMax)
        posY1Bottom = random.between(columnBottomMin, columnBottomMax)

      } else if(posX2 == columnRestart){
        posX2 = 1920 + 100
        posY2Top = random.between(columnTopMin, columnTopMax)
        posY2Bottom = random.between(columnBottomMin, columnBottomMax)

      }
      // Changes the speed
      Column.posX -= speed
      posX1 -= speed
      posX2 -= speed
    }
  }
}