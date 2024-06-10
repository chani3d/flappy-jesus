package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Background() extends DrawableObject {
  val bg = new BitmapImage("src/res/bg.png")
  val bg2 = new BitmapImage("src/res/bg2.png")
  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(1920 / 2, 1080 / 2, 0, 1, bg)
    g.drawTransformedPicture(1920 / 2, (1080 / 2) - 200, 0, 1, bg2)
  }
}