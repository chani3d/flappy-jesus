package ch.hevs.gdx2d.objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject


case class Column() extends DrawableObject {
  private val column: BitmapImage = new BitmapImage("src/res/column.png")
  private val columnBase: BitmapImage = new BitmapImage("src/res/column-base.png")
  private var posX: Int = 1980 - 100
  private val posYTop: Int = 800 // posY for the top column
  private val posYBottom: Int = 280 // posY for the bottom column
  private val columnGap: Int = 100

  override def draw(g: GdxGraphics): Unit = {
    // Top column
    g.drawTransformedPicture(posX, posYTop + columnGap, 0, 0.5f, column)
    g.drawTransformedPicture(posX, posYTop, 0, 0.5f, columnBase)

    // Bottom column
    g.drawTransformedPicture(posX, posYBottom - columnGap, 180, 0.5f, column)
    g.drawTransformedPicture(posX, posYBottom, 180, 0.5f, columnBase)

    // Loop effect
    if(posX == 0) {
      posX = 1980 - 100
    }
    // Changes the speed
    posX -= 10
  }
}