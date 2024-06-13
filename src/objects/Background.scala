package objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Background() extends DrawableObject {
  private val img: BitmapImage = new BitmapImage("src/res/images/bg.png")
  private val img2: BitmapImage =  new BitmapImage("src/res/images/bg2.png")
  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(1920 / 2, 1080 / 2, 0, 1, img)
    g.drawTransformedPicture(1920 / 2, (1080 / 2) - 150, 0, 1, img2)
  }
}