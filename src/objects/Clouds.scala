package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Clouds() extends DrawableObject {
  private val img: BitmapImage = new BitmapImage("src/res/clouds.png")
  private val posy: Int = 100
  private var posx: Int = 1980 - 100

  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(posx, posy, 0, 1, img)
    if(posx == 0) posx = 1980 - 100
    posx -= 10 // Changes the speed
  }
}