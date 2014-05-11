package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.horncatstudios.projectpancake.ProjectPancakeGame;

public class ResourceManager {

  public static final ResourceManager INSTANCE = new ResourceManager();

  public ProjectPancakeGame mGame;

  void setScreen(BaseScene scene) {
    mGame.setScreen(scene);
  }

  /**
   * Shared Game Resources
   */
  // public Music conversationMusic;
  // public Sound supriseSound;
  public Label.LabelStyle mFontTitleStyle;

  public Skin mMenuButtonSkin;

  public BitmapFont mSharedFont;
  public BitmapFont mConversationFont;

  /**
   * Splash Resources
   */
  public Texture ursaRageLogoimage;

  /**
   * Main Menu Resources
   */

  /**
   * Game Level Resources
   */
  public Texture schoolBackground;
  public Texture textBackground;
  public Texture dorianBaseImage;

  public void loadSharedResouces() {
    mFontTitleStyle = new Label.LabelStyle(mSharedFont, Color.CYAN);

    // Sets the background of the Text Button - must be set
    Pixmap backgroundTexture = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    backgroundTexture.setColor(Color.RED);
    backgroundTexture.fill();

    mMenuButtonSkin = new Skin();
    mMenuButtonSkin.add("white", new Texture(backgroundTexture));

    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    style.checkedFontColor = Color.PINK;
    style.fontColor = Color.WHITE;
    style.font = mSharedFont;
    mMenuButtonSkin.add("default", style);


    // load the drop sound effect and the rain background "music"
    //conversationMusic = Gdx.audio.newMusic(Gdx.files.internal("conversationMusic.mp3"));
    //supriseSound = Gdx.audio.newSound(Gdx.files.internal("supriseSound.wav"));
  }

  public void disposeSharedresources() {
    //supriseSound.dispose();
    //conversationMusic.dispose();
  }


  public void loadMenuResources() {
  }

  public void loadGameResources() {
    Gdx.app.log("touch", "Loading game resources");
    schoolBackground = new Texture(Gdx.files.internal("back.png"));
    textBackground = new Texture(Gdx.files.internal("blank.png"));
    dorianBaseImage = new Texture(Gdx.files.internal("d2.png"));
    mConversationFont = new BitmapFont(Gdx.files.internal("font/Agency24.fnt"), false);
  }

  public void unloadGameResources() {
    schoolBackground.dispose();
    dorianBaseImage.dispose();
    textBackground.dispose();
  }


  public void loadSplashScreen() {
    ursaRageLogoimage = new Texture(Gdx.files.internal("logo.png"));
  }

  public void unloadSplashScreen() {
    ursaRageLogoimage.dispose();
  }

  public static void prepareManager(ProjectPancakeGame game) {
    getInstance().mGame = game;
  }

  //---------------------------------------------
  // GETTERS AND SETTERS
  //---------------------------------------------
  public static ResourceManager getInstance() {
    return INSTANCE;
  }

  public void prepareSharedResources() {
    loadSharedResouces();
  }
}
