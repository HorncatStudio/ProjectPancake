package net.horncatstudios.gameengine;


import com.badlogic.gdx.Gdx;
import net.horncatstudios.projectpancake.GameScreen;
import net.horncatstudios.projectpancake.MainMenuScreen;
import net.horncatstudios.projectpancake.SplashScreen;

public class SceneManager {
  private BaseScene splashScene = null;
  private BaseScene menuScene = null;
  private BaseScene gameLevelScene = null;

  private static final SceneManager INSTANCE = new SceneManager();

  private SceneType currentSceneType = SceneType.SCENE_SPLASH;
  private BaseScene currentScene;

  public enum SceneType {
    SCENE_SPLASH,
    SCENE_MENU,
    SCENE_GAME
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
      gameLevelScene = new GameScreen();
    }
    setScene(gameLevelScene);
  }
}
