package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.horncatstudios.projectpancake.ProjectPancakeGame;


public abstract class BaseScreen implements IHcScreen {

  public OrthographicCamera camera;
  protected ResourceManager resourcesManager;
  protected ProjectPancakeGame mGame;
  protected ScreenViewport mScreenViewport;

  public BaseScreen() {
    this.resourcesManager = ResourceManager.getInstance();
    this.mGame = this.resourcesManager.mGame;

    camera = new OrthographicCamera();


    if (Gdx.graphics.getWidth() > 800) {
      camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    } else {
      camera.setToOrtho(false, 800, 600);
    }

    mScreenViewport = new ScreenViewport(camera);

    createScene();
  }

  public abstract void createScene();

  public abstract ScreenManager.ScreenType getSceneType();

  public void setCustom(final String event) {
  }

  ;

  @Override
  public void resize(int width, int height) {
    mScreenViewport.update(width, height);
  }

}
