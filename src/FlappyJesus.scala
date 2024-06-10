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

  override def onInit(): Unit = {
    setTitle("Flappy Jesus - Sebastian Cruz Go 2024")
    toDraw.add(Background())
    toDraw.add(Clouds())
    toDraw.add(Jesus(new Vector2(250, 500), keyStatus))
    toDraw.add(Column())
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    toDraw.forEach(element => element.draw(g))
    g.drawSchoolLogo()

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
