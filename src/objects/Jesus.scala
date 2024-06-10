package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2


case class Jesus(position: Vector2) extends DrawableObject {
  private val img: BitmapImage = new bitmaps.BitmapImage("src/res/jesus.png")
  private var posy: Float = position.y
  private var posx: Float = position.x
  private var speed: Float = 0


  override def draw(g: GdxGraphics): Unit = {
    speed += 0.5f
    println(posy)
    if(posy > 100) posy -= speed

    g.drawTransformedPicture(posx, posy, 0, 0.3f, img)

  }
}