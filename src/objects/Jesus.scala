package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.FlappyJesus
import ch.hevs.gdx2d.components.bitmaps
import ch.hevs.gdx2d.components.bitmaps.{BitmapImage, Spritesheet}
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, Input}


import scala.collection.mutable.HashMap
import scala.collection.mutable


case class Jesus() extends DrawableObject {
  var position: Vector2 = new Vector2(150, 500)
  private val img: Spritesheet = new Spritesheet("src/res/jesus.png", 32, 32)
  private var posy: Float = position.y
  private var posx: Float = position.x
  var speed: Float = 0

  override def draw(g: GdxGraphics): Unit = {
    // Gravity
    speed += 0.5f

    if(speed > 10) speed = 10
    if(posy > 100) posy -= speed

    g.draw
    g.drawTransformedPicture(posx, posy, 0, 0.3f, img)

  }
}