package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import objects._
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.{Interpolation, Vector2}

import java.util

object FlappyJesus extends App {
  new FlappyJesus
}

class FlappyJesus extends PortableApplication(1920, 1080) {
  private var toDraw: util.Vector[DrawableObject] = new util.Vector[DrawableObject]

  override def onInit(): Unit = {
    setTitle("Flappy Jesus - Sebastian Cruz Go 2024")
    toDraw.add(Background())
    toDraw.add(Clouds())
    toDraw.add(Jesus(new Vector2(150, 500)))
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    toDraw.forEach(element => element.draw(g))
    g.drawSchoolLogo()
  }
}
