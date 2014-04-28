package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
