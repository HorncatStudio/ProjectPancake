package net.horncatstudios.gameengine;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.horncatstudios.projectpancake.ProjectPancakeGame;


public abstract class BaseScene implements Screen, InputProcessor {

  public OrthographicCamera camera;
  protected ResourceManager resourcesManager;
  protected ProjectPancakeGame mGame;

  public BaseScene() {
    this.resourcesManager = ResourceManager.getInstance();
    this.mGame = this.resourcesManager.mGame;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 640, 480);

    createScene();
  }

  public abstract void createScene();

//    public abstract void onBackKeyPressed();
//
//    public abstract void onDPadPressed(int keyCode, KeyEvent event);
//
//    public abstract void onButtonPadPressed(int keyCode, KeyEvent event);

  public abstract SceneManager.SceneType getSceneType();
}
