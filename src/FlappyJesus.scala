package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import objects._
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.{Interpolation, Vector2}

import java.util
import scala.collection.mutable

object FlappyJesus extends App {
  var isStarted: Boolean = false
  new FlappyJesus
}

class FlappyJesus extends PortableApplication(1920, 1080){
  private var toDraw: util.Vector[DrawableObject] = new util.Vector[DrawableObject]
  // Key check (62 is the spacebar)
  private val keyStatus: mutable.HashMap[Int, Boolean] = mutable.HashMap(
    Input.Keys.SPACE -> false
  )

  private def areColliding(): Boolean = {
    val xOverlap = Jesus.position.x < Column.posX + Column.w && Jesus.position.x + Jesus.w > Column.posX
    val yOverlap = Jesus.position.y < Column.posYTop + Column.h && Jesus.position.y + Jesus.h > Column.posYTop
    xOverlap && yOverlap
  }

  override def onInit(): Unit = {
    setTitle("Flappy Jesus - Sebastian Cruz Go 2024")
    toDraw.add(Background())
    toDraw.add(Clouds())
    toDraw.add(Jesus(keyStatus))
    toDraw.add(Column(keyStatus))
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    toDraw.forEach(element => element.draw(g))
    if(areColliding()) FlappyJesus.isStarted = false
    g.drawSchoolLogoUpperRight()
  }

  // Keybord management (Jump)
  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    keyStatus.put(keycode, true)
  }
  override def onKeyUp(keycode: Int): Unit = {
    super.onKeyUp(keycode)
    keyStatus.put(keycode, false)
    // 62 is the keycode for the spacebar, we can control this by printing the keycode
  }

  // Mouse management (Jump)
  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
    keyStatus.put(62, true)
  }

  override def onRelease(x: Int, y: Int, button: Int): Unit = {
    super.onRelease(x, y, button)
    keyStatus.put(62, false)
  }
}
