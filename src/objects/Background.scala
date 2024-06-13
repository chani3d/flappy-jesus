package objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject

import scala.util.Random


case class Background() extends DrawableObject {
  private val random: Random = new Random
  val n: Int = random.nextInt(2) // 0 or 1 to choose the bg
  private val img: BitmapImage = new BitmapImage(s"src/res/images/color$n.png")
  private val img2: BitmapImage =  new BitmapImage(s"src/res/images/bg$n.png")

  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(1920 / 2, 1080 / 2, 0, 1, img)
    g.drawTransformedPicture(1920 / 2, (1080 / 2) - 150, 0, 1, img2)
  }
}