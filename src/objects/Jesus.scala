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
  private val img: BitmapImage = new BitmapImage("src/res/jesus.png")
  private var posY: Float = position.y
  private var posX: Float = position.x
  var speed: Float = 0f


  override def draw(g: GdxGraphics): Unit = {
    // Gravity
    speed += 0.5f
    if(speed > 10) speed = 10
    if(posY > 100) posY -= speed
    if (keyStatus(Input.Keys.SPACE)) {
      speed = -10
      posY += 1
    }
    println(speed)

    g.drawTransformedPicture(posX, posY, 0, 0.4f, img)

  }
}