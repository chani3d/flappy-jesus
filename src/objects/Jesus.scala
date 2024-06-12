package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.FlappyJesus
import ch.hevs.gdx2d.components.bitmaps
import ch.hevs.gdx2d.components.bitmaps.{BitmapImage, Spritesheet}
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, Input}
import scala.collection.mutable.HashMap


import scala.collection.mutable.HashMap
import scala.collection.mutable

object Jesus {
  var speed: Float = 0
  var w: Int = 350 * 0.3.toInt
  var h: Int = (550 * 0.3).toInt
  val position: Vector2 = new Vector2(250, 500)
}
case class Jesus(keyStatus: mutable.HashMap[Int, Boolean]) extends DrawableObject {
  private val position: Vector2 = new Vector2(250, 500)
  private var isStarted: Boolean = false
  private var angle: Float = 0
  private var posX: Float = position.x
  private var posY: Float = position.y
  private val img: BitmapImage = new BitmapImage("src/res/jesus.png")

  override def draw(g: GdxGraphics): Unit = {
    // Game loop
    if(isStarted){
      // Gravity
      Jesus.speed += 0.5f
      if(Jesus.speed > 10) Jesus.speed = 10
      if(posY > 85) posY -= Jesus.speed
    }

    // Keyboard control (Jump)
    if (keyStatus(Input.Keys.SPACE)) {
      isStarted = true
      Jesus.speed = -10
      posY += 1
      angle = 10
    } else if(!isStarted) angle = 0
    else if(posY == 85) angle = 0 // If Jesus hits the ground the angle is set to 0 (to check the ground level just println posY)
    else (angle = -10) // If Jesus is falling, he faces down

    g.drawTransformedPicture(posX, posY, angle, 0.3f, img)

  }
}