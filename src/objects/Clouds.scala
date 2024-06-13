package objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Clouds() extends DrawableObject {
  private val img: BitmapImage = new BitmapImage("src/res/images/clouds.png")
  private val posy: Int = 92
  private var posx: Int = 1920 + 60

  override def draw(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(posx, posy, 0, 1, img)
    if(Gamestate.isStarted && !Gamestate.isGameOver){
      if(posx == 100) posx = 1920 + 60 // Loop effect
      posx -= 10 // Changes the speed
    }
  }
}