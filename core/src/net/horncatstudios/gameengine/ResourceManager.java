package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import net.horncatstudios.projectpancake.FontResourceManager;
import net.horncatstudios.projectpancake.ProjectPancakeGame;

public class ResourceManager {

  public static final ResourceManager INSTANCE = new ResourceManager();

  public ProjectPancakeGame mGame;

  void setScreen(BaseScreen scene) {
    mGame.setScreen(scene);
  }

  public static FontResourceManager fontManager = new FontResourceManager();

  //region SharedGameResources
  // public Music conversationMusic;
  // public Sound supriseSound;
  //endregion

  //regionSplash Resources
  public Texture ursaRageLogoimage;
  //endregion

  //region Main Menu Resources
  //endregion

  //region Tile Map resources
  public TiledMap mSchoolMap = null;
  //endregion

  //region Conversation Level Resources
  public Texture schoolBackground;
  public Texture textBackground;
  public TextureAtlas dorianImages;
  //endregion

  //region Map Tutorial Resources
  public Texture worldMap;
  //endregion


  public void loadSharedResouces() {
    // load the drop sound effect and the rain background "music"
    //conversationMusic = Gdx.audio.newMusic(Gdx.files.internal("conversationMusic.mp3"));
    //supriseSound = Gdx.audio.newSound(Gdx.files.internal("supriseSound.wav"));
    fontManager.loadSharedResources();
  }

  public void disposeSharedResources() {
    //supriseSound.dispose();
    //conversationMusic.dispose();
    fontManager.disposeSharedResources();
  }

  public void loadMenuResources() {
    fontManager.loadMenuResources();
  }

  public void unloadMenuResources() {
    fontManager.unloadMenuResources();
  }

  public void loadGameResources() {
    Gdx.app.log("touch", "Loading game resources");
    schoolBackground = new Texture(Gdx.files.internal("classroom.png"));
    textBackground = new Texture(Gdx.files.internal("blank.png"));
    dorianImages = new TextureAtlas(Gdx.files.internal("dorian/dorian.atlas"));
  }

  public void unloadGameResources() {
    schoolBackground.dispose();
    textBackground.dispose();
    dorianImages.dispose();
  }

  public void loadSchoolMapResources() {
    TmxMapLoader loader = new TmxMapLoader();
    mSchoolMap = loader.load("basic_map/classRoomBeta.tmx");
  }

  public void unloadSchoolMapResources() {
    mSchoolMap.dispose();
  }

  public void loadMapTutorialResources() {
    worldMap = new Texture(Gdx.files.internal("world-map-pokemon.png"));
  }

  public void unloadMapTutorialResources() {
    worldMap.dispose();
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
