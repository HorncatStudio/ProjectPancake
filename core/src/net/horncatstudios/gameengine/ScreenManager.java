package net.horncatstudios.gameengine;


import com.badlogic.gdx.Gdx;
import net.horncatstudios.projectpancake.*;
import net.horncatstudios.projectpancake.ConversationScreen;

public class ScreenManager {
  private BaseScreen splashScreen = null;
  private BaseScreen menuScreen = null;
  private BaseScreen gameLevelScreen = null;
  private BaseScreen endGameScreen = null;
  private BaseScreen tileMapScreen = null;
  private BaseScreen mapTutorialScreen = null;
  private BaseScreen phoneTutorialScreen = null;

  private static final ScreenManager INSTANCE = new ScreenManager();

  private ScreenType currentScreenType = ScreenType.SPLASH;

  private BaseScreen currentScene;

  //! Shall be used once the user is able to freely open the game menu AKA
  //! the phone menu.
  private BaseScreen lastScreen;

  public enum ScreenType {
    SPLASH,
    TITLE_MENU,
    CONVERSATION,
    DEMO_END,
    SCHOOL_TILED_MAP,
    MAP_TUTORIAL,
    PHONE_TUTORIAL,
    ENERGY_TUTORIAL
  }

  public void setScene(ScreenType screenType) {
    switch (screenType) {
      case TITLE_MENU:
        loadMenuScene();
        break;
      case CONVERSATION:
        loadConversationScene();
        break;
      case SPLASH:
        loadSplashScene();
        break;
      case DEMO_END:
        loadEndScreen();
        break;
      case SCHOOL_TILED_MAP:
        loadSchoolTileMapScene();
        break;
      case MAP_TUTORIAL:
        loadWorldMapTutorialScreen();
        break;
      case PHONE_TUTORIAL:
        loadPhoneTutorialWithExplanation();
      default:
        break;
    }
  }

  private void setScene(BaseScreen scene) {
    currentScene = scene;
    currentScreenType = scene.getSceneType();
    currentScene.resourcesManager.setScreen(currentScene);
  }

  //---------------------------------------------
  // GETTERS AND SETTERS
  //---------------------------------------------

  public static ScreenManager getInstance() {
    return INSTANCE;
  }

  public ScreenType getCurrentScreenType() {
    return currentScreenType;
  }

  public BaseScreen getCurrentScreen() {
    return currentScene;
  }

  public void loadSplashScene() {
    ResourceManager.getInstance().loadSplashScreen();

    if (null == splashScreen) {
      splashScreen = new SplashScreen();
    }
    setScene(splashScreen);
  }

  private void disposeSplashScene() {
    splashScreen.dispose();
    splashScreen = null;
  }

  public void loadMenuScene() {
    if (null != splashScreen) {
      this.disposeSplashScene();
      splashScreen = null;
    }

    ResourceManager.getInstance().loadMenuResources();
    if (null == menuScreen) {
      menuScreen = new MainMenuScreen();
    }
    setScene(menuScreen);
  }

  public void loadConversationScene() {
    Gdx.app.log("touch", "menuItemClicked Loading Game");

    ResourceManager.getInstance().loadGameResources();
    if (null == gameLevelScreen) {
      gameLevelScreen = new ConversationScreen();
    }
    setScene(gameLevelScreen);
  }

  public void loadSchoolTileMapScene() {
    ResourceManager.getInstance().loadSchoolMapResources();

    if (null == tileMapScreen) {
      tileMapScreen = new TileMapScreen();
    }
    setScene(tileMapScreen);
  }

  public void loadWorldMapTutorialScreen() {
    ResourceManager.getInstance().loadMapTutorialResources();

    if (null == mapTutorialScreen) {
      mapTutorialScreen = new MapTutorialScreen();
    }

    setScene(mapTutorialScreen);
  }

  public void loadEndScreen() {
    Gdx.app.log("touch", "menuItemClicked Loading Game");

    if (null == endGameScreen) {
      endGameScreen = new EndGameScreen();
    }
    setScene(endGameScreen);
  }


  public void loadPhoneTutorialWithExplanation() {
    if (null == phoneTutorialScreen) {
      phoneTutorialScreen = new PhoneTutorialScreen();
    }

    setScene(phoneTutorialScreen);
  }

  public void loadPhoneTutorialWithoutExplanation() {
    if (null == phoneTutorialScreen) {
      phoneTutorialScreen = new PhoneTutorialScreen();
    }

    setScene(phoneTutorialScreen);
  }
}
