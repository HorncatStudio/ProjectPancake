package net.horncatstudios.gameengine;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.horncatstudios.projectpancake.ProjectPancakeGame;


public abstract class BaseScreen implements Screen, InputProcessor {

  public OrthographicCamera camera;
  protected ResourceManager resourcesManager;
  protected ProjectPancakeGame mGame;

  public BaseScreen() {
    this.resourcesManager = ResourceManager.getInstance();
    this.mGame = this.resourcesManager.mGame;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 640, 480);

    createScene();
  }

  public abstract void createScene();

  public abstract ScreenManager.ScreenType getSceneType();
}
