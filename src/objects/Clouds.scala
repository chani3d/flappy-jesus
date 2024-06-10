package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Clouds() extends DrawableObject {
  val img = new BitmapImage("src/res/clouds.png")
  val posy: Int = 100
  var posx = 100

  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(posx, posy, 0, 1, img)
    if(posx == 1980) posx = 100
    posx += 10 // Changes the speed
  }
}