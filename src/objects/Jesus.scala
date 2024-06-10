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


case class Jesus(position: Vector2, var keyStatus: mutable.HashMap[Int, Boolean]) extends DrawableObject {
  private var angle: Float = 0
  private var posX: Float = position.x
  private var posY: Float = position.y
  private val img: BitmapImage = new BitmapImage("src/res/jesus.png")
  private var speed: Float = 0


  override def draw(g: GdxGraphics): Unit = {
    // Gravity
    speed += 0.5f
    if(speed > 10) speed = 10
    if(posY > 85) posY -= speed

    // Keyboard control (Jump)
    if (keyStatus(Input.Keys.SPACE)) {
      speed = -10
      posY += 1
      angle = 10
    }
    else if(posY == 85) angle = 0 // If Jesus hits the ground the angle is set to 0 (to check the ground level just println posY)
    else (angle = -10) // If Jesus is falling, he faces down
    println(posY)

    g.drawTransformedPicture(posX, posY, angle, 0.3f, img)

  }
}