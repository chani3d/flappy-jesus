package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import objects._
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject

import java.util

object FlappyJesus extends App {
  new FlappyJesus
}

class FlappyJesus extends PortableApplication(900, 400) {
  private var toDraw: util.Vector[DrawableObject] = new util.Vector[DrawableObject]

  override def onInit(): Unit = {
    setTitle("Flappy Jesus - Sebastian Cruz Go 2024")
    toDraw.add(new Jesus)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawSchoolLogo()
    toDraw.forEach(element => element.draw(g))

  }

}
