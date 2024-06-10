package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Jesus() extends DrawableObject {
  private val img = new BitmapImage("src/res/jesus.png")
  private val posy: Int = 100
  private var posx = 0

  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(posx, posy, 0, 0.3f, img)
    if(posx == 900) posx = 0
    posx += 10 // Changes the speed
  }
}