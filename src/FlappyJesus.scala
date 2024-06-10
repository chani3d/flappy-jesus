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
  new FlappyJesus
}

class FlappyJesus extends PortableApplication(1920, 1080) {
  private var toDraw: util.Vector[DrawableObject] = new util.Vector[DrawableObject]
  // Key management
  private val keyStatus: mutable.HashMap[Int, Boolean] = mutable.HashMap(
    Input.Keys.SPACE -> false
  )
  var speed: Jesus = Jesus()

  override def onInit(): Unit = {
    setTitle("Flappy Jesus - Sebastian Cruz Go 2024")
    toDraw.add(Background())
    toDraw.add(Clouds())
    toDraw.add(Jesus())
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    println(Jesus().speed)
    g.clear()
    toDraw.forEach(element => element.draw(g))
    g.drawSchoolLogo()
    if (keyStatus(Input.Keys.SPACE)) println("okay")
  }

  // Keybord management (Jump)
  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    keyStatus.put(keycode, true)
  }
  override def onKeyUp(keycode: Int): Unit = {
    super.onKeyUp(keycode)
    keyStatus.put(keycode, false)
  }

}
