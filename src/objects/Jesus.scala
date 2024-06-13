package objects
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Input
import scala.collection.mutable

object Jesus {
  private val position: Vector2 = new Vector2(250, 500)
  var posX: Float = position.x
  var posY: Float = position.y
  var speed: Float = 0
  var width: Float = 0
  var height: Float = 0
}
case class Jesus(keyStatus: mutable.HashMap[Int, Boolean]) extends DrawableObject {
  private val size: Float = 0.16f
  private val img: BitmapImage = new BitmapImage("src/res/images/jesus.png")
  private var angle: Float = 0

  Jesus.width = img.getImage.getWidth * size
  Jesus.height = img.getImage.getHeight * size

  override def draw(g: GdxGraphics): Unit = {

    // Game loop
    if (Gamestate.isStarted && !Gamestate.isGameOver) {
      applyGravity()
      preventSkyExit()
    }

    // Keyboard control (Jump)
    if (keyStatus(Input.Keys.SPACE) && !Gamestate.isGameOver) {
      startGame()
    } else if (!Gamestate.isStarted) {
      resetAngle()
    } else {
      handleAngleAndPosition()
    }

    drawJesus(g)
  }

  private def applyGravity(): Unit = {
    Jesus.speed += 0.5f
    if (Jesus.speed > 10) Jesus.speed = 10
    if (Jesus.posY > 85) Jesus.posY -= Jesus.speed
  }

  private def preventSkyExit(): Unit = {
    if (Jesus.posY >= 1000) {
      angle = 0
      Jesus.posY = 1000
    }
  }

  private def startGame(): Unit = {
    Gamestate.isStarted = true
    Jesus.speed = -10
    Jesus.posY += 1
    angle = 10
  }

  private def resetAngle(): Unit = {
    angle = 0
  }

  private def handleAngleAndPosition(): Unit = {
    if (Jesus.posY == 85 || Jesus.posY == 960) {
      angle = 0 // If Jesus hits the ground or ceiling, set angle to 0
    } else if (Gamestate.isGameOver) {
      gameOver()
    } else {
      angle = -5 // Jesus faces down during normal gameplay
    }
  }

  private def gameOver(): Unit = {
    angle = -80
    Jesus.speed = 10
    Jesus.posY -= Jesus.speed
    if (Jesus.posY <= 100) Jesus.posY = 100
  }

  private def drawJesus(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(Jesus.posX.toInt, Jesus.posY.toInt, angle, size, img)
  }
}
