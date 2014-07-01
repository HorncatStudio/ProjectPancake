package net.horncatstudios.gameengine;


import com.badlogic.gdx.Gdx;
import net.horncatstudios.projectpancake.*;
import net.horncatstudios.projectpancake.ConversationScreen;

public class SceneManager {
  private BaseScene splashScene = null;
  private BaseScene menuScene = null;
  private BaseScene gameLevelScene = null;
  private BaseScene endGameScreen = null;
  private BaseScene tileMapScene = null;

  private static final SceneManager INSTANCE = new SceneManager();

  private SceneType currentSceneType = SceneType.SCENE_SPLASH;
  private BaseScene currentScene;

  public enum SceneType {
    SCENE_SPLASH,
    SCENE_MENU,
    SCENE_GAME,
    SCENE_END,
    SCENE_TILED_MAP
  }

  public void setScene(SceneType sceneType) {
    switch (sceneType) {
      case SCENE_MENU:
        loadMenuScene();
        break;
      case SCENE_GAME:
        loadGameScene();
        break;
      case SCENE_SPLASH:
        loadSplashScene();
        break;
      case SCENE_END:
        loadEndScreen();
        break;
      case SCENE_TILED_MAP:
        loadTileMapScene();
        break;
      default:
        break;
    }
  }

  private void setScene(BaseScene scene) {
    currentScene = scene;
    currentSceneType = scene.getSceneType();
    currentScene.resourcesManager.setScreen(currentScene);
  }

  //---------------------------------------------
  // GETTERS AND SETTERS
  //---------------------------------------------

  public static SceneManager getInstance() {
    return INSTANCE;
  }

  public SceneType getCurrentSceneType() {
    return currentSceneType;
  }

  public BaseScene getCurrentScene() {
    return currentScene;
  }

  public void loadSplashScene() {
    ResourceManager.getInstance().loadSplashScreen();

    if (null == splashScene) {
      splashScene = new SplashScreen();
    }
    setScene(splashScene);
  }

  private void disposeSplashScene() {
    splashScene.dispose();
    splashScene = null;
  }

  public void loadMenuScene() {
    if (null != splashScene) {
      this.disposeSplashScene();
      splashScene = null;
    }

    ResourceManager.getInstance().loadMenuResources();
    if (null == menuScene) {
      menuScene = new MainMenuScreen();
    }
    setScene(menuScene);
  }

  public void loadGameScene() {
    Gdx.app.log("touch", "menuItemClicked Loading Game");

    ResourceManager.getInstance().loadGameResources();
    if (null == gameLevelScene) {
      gameLevelScene = new ConversationScreen();
    }
    setScene(gameLevelScene);
  }

  public void loadTileMapScene() {
    ResourceManager.getInstance().loadSchoolMapResources();

    if (null == tileMapScene) {
      tileMapScene = new TileMapScreen();
    }
    setScene(tileMapScene);
  }

  public void loadEndScreen() {
    Gdx.app.log("touch", "menuItemClicked Loading Game");

    if (null == endGameScreen) {
      endGameScreen = new EndGameScreen();
    }
    setScene(endGameScreen);
  }
}
