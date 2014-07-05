package net.horncatstudios.gameengine;


import com.badlogic.gdx.Game;
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

  //! The singleton instance of the screen manager.  So object responsible for
  //! managing the loading of different types of scenes in the game.
  private static final ScreenManager INSTANCE = new ScreenManager();

  public static ScreenManager getInstance() {
    return INSTANCE;
  }

  //! The current scene being displayed on the screen
  private IHcScreen mCurrentScene;

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

  //! Base libGDX game class used for displaying games
  private Game mGame = null;

  //! Initialized the scene manager to use the libGDX game to set the new screens.
  public void prepareSceneManager(Game game) {
    mGame = game;
  }

  /**
   * Sets the current screen to be based on the \a screenType.
   */
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

  /**
   * Sets \a screen to be the current child screen.  Due to ChildScreens implementation,
   * once this scene ends the parent scene shall return to be current screen.
   */
  public void setScene(ChildScreen screen) {
    mCurrentScene = screen;
    mGame.setScreen(mCurrentScene);
  }

  /**
   * Sets the \a screen to be the current screen.
   */
  private void setScene(BaseScreen screen) {
    mCurrentScene = screen;
    mGame.setScreen(mCurrentScene);
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

    ResourceManager.getInstance().loadConversationResources();
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

  //region Phone Tutorial
  public void loadPhoneTutorialWithExplanation() {
    ResourceManager.getInstance().loadPhoneResources();

    if (null == phoneTutorialScreen) {
      phoneTutorialScreen = new PhoneTutorialScreen();
    } else {
      phoneTutorialScreen.resume();
    }

    phoneTutorialScreen.setCustom("explain");

    setScene(phoneTutorialScreen);
  }

  public void loadPhoneTutorialWithoutExplanation() {
    ResourceManager.getInstance().loadPhoneResources();

    if (null == phoneTutorialScreen) {
      phoneTutorialScreen = new PhoneTutorialScreen();
    }

    phoneTutorialScreen.setCustom("noexplain");
    setScene(phoneTutorialScreen);
  }

}
