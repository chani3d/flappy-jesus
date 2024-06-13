import ch.hevs.gdx2d.components.audio.MusicPlayer
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import objects._
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.{Gdx, Input, InputAdapter}
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, TextButton}
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import scala.util.Random

import java.util
import scala.collection.mutable

object FlappyJesus extends App {
  new FlappyJesus
}

class FlappyJesus extends PortableApplication(1920, 1080){
  private var toDraw: util.Vector[DrawableObject] = new util.Vector[DrawableObject]
  // Key check (62 is the spacebar)
  private val keyStatus: mutable.HashMap[Int, Boolean] = mutable.HashMap(
    Input.Keys.SPACE -> false
  )
  private var skin: Skin = _
  private var stage: Stage = _
  private var onGame: Stage = _

  private var startGameButton: TextButton = _
  private var exitGameButton: TextButton = _
  private var restartGameButton: TextButton = _


  private var d8bmFont: BitmapFont = _
  private var mainSong: MusicPlayer = _
  private var onGameSong: MusicPlayer = _

  private var bell: MusicPlayer = _
  private var ninja0: MusicPlayer =_
  private var ninja1: MusicPlayer =_
  private var ninja2: MusicPlayer =_

  private val random: Random = new Random

  def chooseShout(): MusicPlayer = {
    val n: Int = random.between(0, 3)
    if(n == 0)  ninja0
    else if(n == 1)  ninja1
    else  ninja2
  }

  override def onInit(): Unit = {
    // Interface
    setTitle("Flappy Jesus - Sebastian Cruz Go 2024")
    val buttonWidth: Int = 180
    val buttonHeight: Int = 30
    stage = new Stage()
    onGame = new Stage()
    Gdx.input.setInputProcessor(stage) // Make the stage consume events

    skin = new Skin(Gdx.files.internal("res/ui/uiskin.json"))

    startGameButton = new TextButton("Start game", skin)
    startGameButton.setWidth(buttonWidth)
    startGameButton.setHeight(buttonHeight)
    startGameButton.setPosition(Gdx.graphics.getWidth / 2 - buttonWidth / 2, (Gdx.graphics.getHeight * 0.3).toInt)

    exitGameButton = new TextButton("Exit", skin)
    exitGameButton.setWidth(buttonWidth)
    exitGameButton.setHeight(buttonHeight)
    exitGameButton.setPosition(Gdx.graphics.getWidth / 2 - buttonWidth / 2, (Gdx.graphics.getHeight * 0.2).toInt)

    restartGameButton = new TextButton("Restart", skin)
    restartGameButton.setWidth(buttonWidth)
    restartGameButton.setHeight(buttonHeight)
    restartGameButton.setPosition(Gdx.graphics.getWidth / 2 - buttonWidth / 2, (Gdx.graphics.getHeight / 2))


    stage.addActor(startGameButton)
    stage.addActor(exitGameButton)
    onGame.addActor(restartGameButton)


    // Buttons listeners
    startGameButton.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        super.clicked(event, x, y)
        if (startGameButton.isChecked) {
          Gdx.input.setInputProcessor(new GameInputProcessor) // Make the stage consume events
          Gamestate.isStarted = true
          Gamestate.isGameOver = false
          restartGame()
        }
      }
    })

    // This button closes the window
    exitGameButton.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        super.clicked(event, x, y)
        if (exitGameButton.isChecked) {
          Gdx.app.exit()
        }
      }
    })

    restartGameButton.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        super.clicked(event, x, y)
        startGame()
      }
    })

    // Font related stuff
    // Score font
    val d8bm = Gdx.files.internal("res/font/Diary of an 8-bit mage.otf")
    val parameter = new FreeTypeFontParameter()
    var generator = new FreeTypeFontGenerator(d8bm)
    // Font effects
    generator = new FreeTypeFontGenerator(d8bm)
    parameter.size = generator.scaleForPixelHeight(80)
    parameter.color = Color.WHITE
    parameter.borderColor = Color.BLUE
    parameter.borderWidth = 3
    parameter.shadowOffsetY = -8
    parameter.shadowColor = Color.LIGHT_GRAY
    d8bmFont = generator.generateFont(parameter)
    generator.dispose()

    // Music related
    mainSong = new MusicPlayer("src/res/music/take-me-to-church_8bit-universe.wav")
    onGameSong = new MusicPlayer("src/res/music/supersonic_she.wav")

    // Sound effects
    bell = new MusicPlayer("src/res/sound/bell.wav")
    ninja0 = new MusicPlayer("src/res/sound/ninja0.wav")
    ninja1 = new MusicPlayer("src/res/sound/ninja1.wav")
    ninja2 = new MusicPlayer("src/res/sound/ninja2.wav")
  }

  private def startGame(): Unit = {
    Gamestate.isStarted = true
    Gamestate.isGameOver = false
    restartGame()
  }

  private def restartGame(): Unit = {
    Jesus.posX = 250
    Jesus.posY = 500
    toDraw.clear()
    toDraw.add(Background())
    toDraw.add(Clouds())
    toDraw.add(Jesus(keyStatus))
    toDraw.add(Column(keyStatus))
    restartGameButton.setVisible(true)
    Gdx.input.setInputProcessor(new GameInputProcessor)
  }
  private def endGame(g: GdxGraphics): Unit = {
    g.drawStringCentered(g.getScreenHeight.toFloat / 2, s"Game Over", d8bmFont)
    bell.play()
    onGameSong.stop()
    Gdx.input.setInputProcessor(onGame)
    onGame.act()
    onGame.draw()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    // Cleans the window
    g.clear()
    mainSong.loop()

    if (Gamestate.isStarted) {
      mainSong.stop()
      onGameSong.loop()
      onGame.act()
      onGame.draw()
      toDraw.forEach(element => element.draw(g))
      g.drawStringCentered(g.getScreenHeight.toFloat / 7.0f / 2 + g.getScreenHeight.toFloat / 7.0f * 6, s"${Column.score}", d8bmFont)

      if (Gamestate.isGameOver) {
        endGame(g)
      }
    } else {
      stage.act()
      stage.draw()
    }
    g.drawSchoolLogoUpperRight()
  }

  override def onDispose(): Unit = {
    // Release what we've used
    super.onDispose()
    d8bmFont.dispose()
    stage.dispose()
    skin.dispose()
    onGame.dispose()
    mainSong.dispose()
  }

  // This handles the game controls once the game is started
  class GameInputProcessor extends InputAdapter {
    // Keyboard control
    override def keyDown(keycode: Int): Boolean = {
      chooseShout().play()
      keyStatus.put(keycode, true)
      true
    }
    override def keyUp(keycode: Int): Boolean = {
      keyStatus.put(keycode, false)
      true
    }

    // Mouse control
    override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
      chooseShout().play()
      keyStatus.put(62, true) // 62 is the keycode for the spacebar, we can control this by printing the keycode
      true
    }

    override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
      keyStatus.put(62, false)
      true
    }
  }
}
