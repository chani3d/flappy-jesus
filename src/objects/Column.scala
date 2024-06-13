package objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Input
import scala.collection.mutable
import scala.util.Random

object Column {
  var score: Int = 0
}

case class Column(keyStatus: mutable.HashMap[Int, Boolean]) extends DrawableObject {
  private val column: BitmapImage = new BitmapImage("src/res/images/column.png")
  private val columnBase: BitmapImage = new BitmapImage("src/res/images/column-base.png")

  private var posX: Int = 1920
  private val size: Float = 0.5f

  var width: Int = (column.getImage.getWidth * size).toInt
  var height: Int = (column.getImage.getHeight * size).toInt

  private val speed: Int = 10
  private val columnGap: Int = 200
  private val columnRestart: Int = -100
  private val posYTopBase: Int = 800 // posY for the top column
  private val posYBottomBase: Int = 280 // posY for the top column

  private val columnTopMin: Int = 620
  private val columnTopMax: Int = 850

  private val columnBottomMin: Int = 100
  private val columnBottomMax: Int = 400

  private val random: Random = new Random()
  private var posX1: Int = posX + 700
  private var posX2: Int = posX1 + 700

  private var posYTop = random.between(columnTopMin, columnTopMax)
  private var posY1Top: Int = random.between(columnTopMin, columnTopMax)
  private var posY2Top: Int = random.between(columnTopMin, columnTopMax)

  private var posYBottom = random.between(columnBottomMin, columnBottomMax)
  private var posY1Bottom: Int = random.between(columnBottomMin, columnBottomMax)
  private var posY2Bottom: Int = random.between(columnBottomMin, columnBottomMax)

  // Handling collisions
  private def areColliding(posX: Int, posY: Int): Boolean = {
    // Calculate bounding box for Jesus
    val tolerance: Int = 40 // This was measured by hand trying the game. It allow us to approach better the columns.
    val jesusLeft = Jesus.posX // - (Jesus.width / 2)
    val jesusRight = Jesus.posX + (Jesus.width / 2)
    val jesusTop = Jesus.posY - (Jesus.height / 2)
    val jesusBottom = Jesus.posY + (Jesus.height / 2)

    // Calculate bounding box for the column
    val columnLeft = posX - (width / 2) + tolerance
    val columnRight = posX + (width / 2)
    val columnTop = posY - (height / 2)
    val columnBottom = posY + (height / 2)

    val xCollision: Boolean = (jesusLeft < columnRight) && (jesusRight > columnLeft)
    val yCollision: Boolean = (jesusTop < columnBottom) && (jesusBottom > columnTop)

    xCollision && yCollision
  }

  // Checking the score
  private def checkScore(posX: Int): Unit = {
    var hasEntered: Boolean = false
    val jesusLeft = Jesus.posX - (Jesus.width / 2)
    val jesusRight = Jesus.posX + (Jesus.width / 2)
    val columnLeft = posX - (width / 2)
    val columnRight = posX + (width / 2)

    if(jesusLeft > columnLeft && jesusRight < columnRight){
      Column.score += 1
    }
  }



  override def draw(g: GdxGraphics): Unit = {
    if (keyStatus(Input.Keys.SPACE)) Gamestate.isStarted = true // Checks if the space is pressed to start the game
    if (Gamestate.isStarted && !Gamestate.isGameOver){
      // Group 0
      // Top column
      g.drawTransformedPicture(posX, posYTop + columnGap, 0, size, column)
      g.drawTransformedPicture(posX, posYTopBase, 0, size, columnBase)

      // Bottom column
      g.drawTransformedPicture(posX, posYBottom - columnGap, 180, size, column)
      g.drawTransformedPicture(posX, posYBottomBase, 180, size, columnBase)

      // Group 1
      // Top column
      g.drawTransformedPicture(posX1, posY1Top + columnGap, 0, size, column)
      g.drawTransformedPicture(posX1, posYTopBase, 0, size, columnBase)
      // Bottom column
      g.drawTransformedPicture(posX1, posY1Bottom - columnGap, 180, size, column)
      g.drawTransformedPicture(posX1, posYBottomBase, 180, size, columnBase)

      // Group 2
      // Top column
      g.drawTransformedPicture(posX2, posY2Top + columnGap, 0, size, column)
      g.drawTransformedPicture(posX2, posYTopBase, 0, size, columnBase)
      // Bottom column
      g.drawTransformedPicture(posX2, posY2Bottom - columnGap, 180, size, column)
      g.drawTransformedPicture(posX2, posYBottomBase, 180, size, columnBase)
    }

    // Loop effect
    if(Gamestate.isStarted && !Gamestate.isGameOver){
      if(posX == columnRestart) {
        posX = g.getScreenWidth
        posYTop = random.between(columnTopMin, columnTopMax)
        posYBottom = random.between(columnBottomMin, columnBottomMax)
      } else if(posX1 == columnRestart){
        posX1 = g.getScreenWidth
        posY1Top = random.between(columnTopMin, columnTopMax)
        posY1Bottom = random.between(columnBottomMin, columnBottomMax)

      } else if(posX2 == columnRestart){
        posX2 = g.getScreenWidth
        posY2Top = random.between(columnTopMin, columnTopMax)
        posY2Bottom = random.between(columnBottomMin, columnBottomMax)

      }
      // Changes the speed
      posX -= speed
      posX1 -= speed
      posX2 -= speed
    }

    // Checking collisions
    if(areColliding(posX, posYTop + columnGap) || areColliding(posX, posYBottom - columnGap)
      || areColliding(posX1, posY1Top + columnGap) || areColliding(posX1, posY1Bottom - columnGap)
      || areColliding(posX2, posY2Top + columnGap) || areColliding(posX2, posY2Bottom - columnGap)) {
      Gamestate.isGameOver = true
    }

    // Once passed a column, let's check the score
    checkScore(posX)
    checkScore(posX1)
    checkScore(posX2)
    if(Gamestate.isGameOver) Column.score = 0
  }
}
